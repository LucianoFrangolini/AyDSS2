package back.servidor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import back.servidor.excepciones.colaVaciaException;

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
		/*for (Puerto x: ListaDePuertos.puertos) {
			this.puertos.add(x);
		}*/
	}

	private Boolean validarDni(String dni) {
		return !this.colaDeEspera.contains(dni);
	}

	public Boolean agregarDni(String dni) {
		Boolean ret = false;
		if (validarDni(dni)) {
			this.colaDeEspera.add(dni);
			ret = true;
		}
		return ret;
	}

	private void agregarTurno(Integer puesto, String dni) {
		Turno turno = new Turno(puesto, dni);
		this.listaDeTurnos.agregarTurno(turno);
	}

	public void obtenerProximoCliente(Integer puesto) throws colaVaciaException {
		String dniCliente = this.colaDeEspera.poll();
		if (dniCliente == null) {
			throw new colaVaciaException("La cola de clientes se encuentra vacia.");
		} else {
			this.agregarTurno(puesto, dniCliente);
			// handle message
		}
	}


	@Override
	public void setMsj(String nuevoMensaje) {
		String oldMsj = this.msj;
		this.msj = nuevoMensaje;
		this.pcs.firePropertyChange("Mensaje", oldMsj, this.msj);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void abrirServidor() {
		
		abrirPuertoTotem(ListaDePuertos.PUERTO_TOTEM);
		/*new Thread() {
			public void run() {
				try {
					Socket skt;
					myServerSocket = new ServerSocket(puerto);
					setMsj("Esperando conexion...");
					skt = myServerSocket.accept();
					setMsj("Conexión establecida con el puerto " + skt.getPort() + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();*/
	}

	private void abrirPuertoTotem(int puerto) {
		new Thread() {
			public void run() {
				ServerSocket totemServerSocket;
				Socket skt;
				AdministradorDeTurnos admin = AdministradorDeTurnos.getInstance();
				BufferedReader myInput;
    			//PrintStream myOutput;
				PrintWriter myOutput;
    			String nuevoDni = null;
				try {
					totemServerSocket = new ServerSocket(puerto);
					while(true) {	//nuevoDni==null	//true
					
						//setMsj("Esperando conexion...");
						System.out.println("antes del accept");
						skt = totemServerSocket.accept();
						//setMsj("Conexión establecida con el puerto " + skt.getPort() + "\n");
						myInput = new BufferedReader(new InputStreamReader(skt.getInputStream()));
						//myOutput = new PrintStream(skt.getOutputStream());
						myOutput = new PrintWriter(skt.getOutputStream(), true);
						System.out.println("Sevidor0");

						System.out.println("Sevidor0.5");
	        			nuevoDni = myInput.readLine();
	        			System.out.println("Sevidor0.9");
	        			System.out.println("servidor1");
	        			System.out.println(nuevoDni);
	        			if(admin.agregarDni(nuevoDni))
	        				myOutput.println("Registro exitoso");
	        			else
	        				myOutput.println("El DNI ya se encuentra registrado.");
	        			System.out.println("servidor2");
	        			myInput.close();
	        			myOutput.close();
	        			skt.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
