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
		BufferedReader input;
		ObjectInputStream obInput;
		ObjectOutputStream obOutput;
		String msj;		
		
		try {
			backupServerSocket = new ServerSocket(this.puerto);
			while (true) {
				socket = backupServerSocket.accept();
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
				System.out.println("Esperando msj");
				
				msj = input.readLine();
				input.close();
				System.out.println("Voy a entrar al if");
				System.out.println(msj);
				if (msj.equalsIgnoreCase("Sincronizar")) {
					System.out.println("Entre al if");
					
					obOutput = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("Cree el output");
					obOutput.writeObject(this.admin.getListaDeTurnos());
					obOutput.writeObject(this.admin.getColaDeEspera());
					obOutput.writeObject(this.admin.getPuestosDeTrabajo());
					System.out.println("Envie las cosas");
					obOutput.close();
					
				} else if (msj.equalsIgnoreCase("Backup")) {
					obInput = new ObjectInputStream(socket.getInputStream());
					this.admin.setListaDeTurnos((ListaDeTurnos) obInput.readObject());
					this.admin.setColaDeEspera((ColaDeEspera) obInput.readObject());
					this.admin.setPuestosDeTrabajo((Integer[])obInput.readObject());
					obInput.close();
				}	
				System.out.println("Sali del if");
				socket.close();
			}
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	public void start() {
		this.server.start();
	}
	
}
