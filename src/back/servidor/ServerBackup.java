package back.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
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
		
		try {
			backupServerSocket = new ServerSocket(this.puerto);
			while (true) {
				socket = backupServerSocket.accept();
				obInput = new ObjectInputStream(socket.getInputStream());
				this.admin.setListaDeTurnos((ListaDeTurnos) obInput.readObject());
				this.admin.setColaDeEspera((ColaDeEspera) obInput.readObject());
				this.admin.setPuestosDeTrabajo((int[])obInput.readObject());
				System.out.println(this.admin.getPuestosDeTrabajo());
				obInput.close();
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
