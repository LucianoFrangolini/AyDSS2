package back.servidor.componente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import back.servidor.colaDeEspera.ColaDeEspera;
import libreria.clasescompartidas.ListaDeTurnos;

/**
 * @author Grupo 12 Clase encargada de realizar backup del servidor.</br>
 *         Se recibe la lista de turnos, la cola de espera, y los puestos de
 *         trabajo del otro servidor mediante socket y se asignan al propio
 *         servidor.
 */
public class ServerBackup implements Runnable {

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
		try {
			backupServerSocket = new ServerSocket(this.puerto);
			while (true) {
				socket = backupServerSocket.accept();
				ObjectInputStream obInput = new ObjectInputStream(socket.getInputStream());
				this.admin.setListaDeTurnos((ListaDeTurnos) obInput.readObject());
				this.admin.setColaDeEspera((ColaDeEspera) obInput.readObject());
				this.admin.setPuestosDeTrabajo((Integer[]) obInput.readObject());
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
