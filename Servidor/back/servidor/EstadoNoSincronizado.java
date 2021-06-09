package back.servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import back.servidor.interfaces.Redundancia;


public class EstadoNoSincronizado implements Redundancia {
Administrador admin;
	
	public EstadoNoSincronizado(Administrador admin) {
		this.admin = admin;
	}
	
	@Override
	public void backup() {
		try {
			Socket socket = new Socket(admin.getIpServidor(), admin.getPuertoServidorBackup());
			ObjectOutputStream myObjectOutput = new ObjectOutputStream(socket.getOutputStream());
			myObjectOutput.writeObject(admin.getListaDeTurnos());
			myObjectOutput.writeObject(admin.getColaDeEspera());
			myObjectOutput.writeObject(admin.getPuestosDeTrabajo());
			myObjectOutput.close();
			socket.close();
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
	}
	
	/**
	 * Método para cubrir la implementacion de la interfaz en un estado que no requiere sincronizar<br>
	 */
	@Override
	public void intentarSincronizar() {
		//Este estado no sincroniza
	}
}
