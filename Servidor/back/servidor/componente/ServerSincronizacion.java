package back.servidor.componente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Grupo 12 <br>
 *         Clase encargada de sincronizar el servidor.<br>
 *         Se envia la lista de turnos, la cola de espera, y los puestos de
 *         trabajo al otro servidor mediante socket.
 */
public class ServerSincronizacion implements Runnable {

	private Administrador admin;
	private int puerto;

	private Thread server;

	public ServerSincronizacion(Administrador admin, int puerto) {
		this.admin = admin;
		this.puerto = puerto;
		this.server = new Thread(this, String.valueOf(puerto));
	}

	@Override
	public void run() {

		ServerSocket sincronizacionServerSocket;
		Socket socket;
		try {
			sincronizacionServerSocket = new ServerSocket(this.puerto);
			while (true) {
				socket = sincronizacionServerSocket.accept();
				this.admin.cambiarEstado(new EstadoSincronizado(admin));
				ObjectOutputStream obOutput = new ObjectOutputStream(socket.getOutputStream());
				obOutput.writeObject(this.admin.getListaDeTurnos());
				obOutput.writeObject(this.admin.getColaDeEspera());
				obOutput.writeObject(this.admin.getPuestosDeTrabajo());
				obOutput.close();
				socket.close();
			}
		} catch (IOException e) {
		}
	}

	public void start() {
		this.server.start();
	}

}