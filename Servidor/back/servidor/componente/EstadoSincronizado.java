package back.servidor.componente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import back.servidor.coladeespera.ColaDeEspera;
import back.servidor.interfaces.Redundancia;
import libreria.clasescompartidas.ListaDeTurnos;

/**
 * @author Grupo 12 <br>
 *         La Clase EstadoSincronizado es utilizada para implementar el patron
 *         State, en este estado se puede realizar backup y sincronizar.
 */
public class EstadoSincronizado implements Redundancia {
	Administrador admin;

	/**
	 * Contructor de EstadoNoSincronizado.</br>
	 * 
	 * @param admin referencia al mismo administrador que lo contiene.
	 */
	public EstadoSincronizado(Administrador admin) {
		this.admin = admin;
	}

	/**
	 * Metodo encargado de enviar los datos de la lista de turnos, cola de espera, y
	 * puestos de trabajo al servidor que sea backup.
	 */
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

	/**
	 * Metodo encargado de cargar la lista de turnos, cola de espera, y puestos de
	 * trabajo desde el otro servidor, en caso de no ser posible se pasara a estado
	 * no sincronizado.
	 *
	 */
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
