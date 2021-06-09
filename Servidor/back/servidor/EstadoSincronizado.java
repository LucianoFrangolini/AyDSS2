package back.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import back.servidor.colaDeEspera.ColaDeEspera;
import back.servidor.interfaces.Redundancia;
import clasesCompartidas.ListaDeTurnos;

public class EstadoSincronizado implements Redundancia {
	Administrador admin;
	
	public EstadoSincronizado(Administrador admin) {
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
			admin.cambiarEstado(new EstadoNoSincronizado(admin));
		}
	}

	@Override
	public void intentarSincronizar() {
		ObjectInputStream obInput;
		Socket socket;
		try {
			socket = new Socket(admin.getIpServidor(), admin.getPuertoServidorSincronizacion());
			obInput = new ObjectInputStream(socket.getInputStream());
			admin.setListaDeTurnos((ListaDeTurnos) obInput.readObject());
			admin.setColaDeEspera((ColaDeEspera) obInput.readObject());
			admin.setPuestosDeTrabajo((Integer[]) obInput.readObject());
			obInput.close();
			socket.close();
		} catch (ClassNotFoundException e) {
		} catch (UnknownHostException e) {
		} catch (IOException e) {
			admin.cambiarEstado(new EstadoNoSincronizado(admin));
		}
	}
	
}
