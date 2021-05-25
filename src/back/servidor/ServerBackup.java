package back.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBackup implements Runnable{

	private Administrador admin;
	private int puerto;
	
	private Thread server;

	public ServerBackup(Administrador admin, int puerto) {
		this.admin = admin;
		this.puerto = puerto;
		this.server = new Thread(this, String.valueOf(puerto));
	}

	@Override
	public void run() {
		
		ServerSocket backupServerSocket;
		Socket socket;
		ObjectInputStream obInput;
		ObjectOutputStream obOutput;
		String msj;		
		Boolean sincronizar = true;
		
		try {
			backupServerSocket = new ServerSocket(this.puerto);
			while (true) {
				socket = backupServerSocket.accept();
				
				if (sincronizar) {
					obOutput = new ObjectOutputStream(socket.getOutputStream());
					obOutput.writeObject(this.admin.getListaDeTurnos());
					obOutput.writeObject(this.admin.getColaDeEspera());
					obOutput.writeObject(this.admin.getPuestosDeTrabajo());
					obOutput.close();
					sincronizar=false;
				} else{
					obInput = new ObjectInputStream(socket.getInputStream());
					this.admin.setListaDeTurnos((ListaDeTurnos) obInput.readObject());
					this.admin.setColaDeEspera((ColaDeEspera) obInput.readObject());
					this.admin.setPuestosDeTrabajo((Integer[])obInput.readObject());
					obInput.close();
				}								
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		}
	}

	public void start() {
		this.server.start();
	}
	
}
