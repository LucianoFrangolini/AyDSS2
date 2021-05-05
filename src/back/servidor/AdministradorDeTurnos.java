package back.servidor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import back.servidor.excepciones.colaVaciaException;
import back.servidor.excepciones.dniExistenteException;


public class AdministradorDeTurnos extends ConexionConServerSocket {
	private ListaDeTurnos listaDeTurnos = new ListaDeTurnos();
	private ColaDeEspera colaDeEspera = new ColaDeEspera();
	private static AdministradorDeTurnos instance;

	public static AdministradorDeTurnos getInstance() {
		if (instance == null) {
			instance = new AdministradorDeTurnos();
		}
		return instance;
	}
	private AdministradorDeTurnos() {
		this.pcs = new PropertyChangeSupport(this);
	}
	
	
	private Boolean validarDni(String dni) {
		return !this.colaDeEspera.contains(dni);
	}
	public void añadirDni(String dni) throws dniExistenteException {
		if(validarDni(dni)) {
			this.colaDeEspera.add(dni);
		}
		else {
			throw new dniExistenteException("El DNI ingresado ya se encuentra registrado.");
		}
	}
	private void añadirTurno(Integer puesto, String dni) {
		Turno turno = new Turno(puesto,dni);
		this.listaDeTurnos.añadirTurno(turno);
	}
	public void obtenerProximoCliente(Integer puesto) throws colaVaciaException {
		String dniCliente = this.colaDeEspera.poll();
		if(dniCliente==null) {
			throw new colaVaciaException("La cola de clientes se encuentra vacia.");
		} else {
			this.añadirTurno(puesto, dniCliente);
			//handle message
		}
	}

	@Override
	public void setMsj(String nuevoMensaje) {
		String oldMsj = this.msj;
		this.msj = nuevoMensaje;
		this.pcs.firePropertyChange("Mensaje",oldMsj,this.msj);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void abrirServidor(String ip, int puerto) {
		new Thread() {
            public void run() {
            	try {
            		Socket skt;
        			myServerSocket = new ServerSocket(puerto);
        			setMsj("Esperando conexion...");
        			skt = myServerSocket.accept();
        			setMsj("Conexión establecida con el puerto "+skt.getPort()+"\n");
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            }
        }.start();
	}
}
