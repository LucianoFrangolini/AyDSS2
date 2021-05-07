package back.servidor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import back.direcciones.ListaDeDirecciones;
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
		/*
		 * for (Puerto x: ListaDePuertos.puertos) { this.puertos.add(x); }
		 */
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

	private Boolean agregarTurno(Integer puesto, String dni) {
		Boolean ret = false;
		if (dni != null) {
			Turno turno = new Turno(puesto, dni);
			this.listaDeTurnos.agregarTurno(turno);
			ret = true;
		}
		return ret;
	}

	public String obtenerProximoCliente(){
		return this.colaDeEspera.poll();

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

		abrirPuertoTotem(ListaDeDirecciones.PUERTO_TOTEM);
		abrirPuertoPuestos(ListaDeDirecciones.PUERTO_PUESTOS);

		/*
		 * new Thread() { public void run() { try { Socket skt; myServerSocket = new
		 * ServerSocket(puerto); setMsj("Esperando conexion..."); skt =
		 * myServerSocket.accept(); setMsj("Conexión establecida con el puerto " +
		 * skt.getPort() + "\n"); } catch (IOException e) { e.printStackTrace(); } }
		 * }.start();
		 */
	}

	private void abrirPuertoPuestos(int puertoPuestos) {
		new Thread() {
			public void run() {
				ServerSocket puestosServerSocket;
				Socket socket;
				AdministradorDeTurnos admin = AdministradorDeTurnos.getInstance();
				BufferedReader myInput;
				PrintWriter myOutput;
				int numeroPuesto;
				String dni;
				try {
					puestosServerSocket = new ServerSocket(puertoPuestos);
					while (true) {
						socket = puestosServerSocket.accept();
						myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						myOutput = new PrintWriter(socket.getOutputStream(), true);
						numeroPuesto = Integer.parseInt(myInput.readLine());
						dni = admin.obtenerProximoCliente();
						if (admin.agregarTurno(numeroPuesto,dni))
							myOutput.println(dni);
						else
							myOutput.println("No hay clientes en espera");
						myInput.close();
						myOutput.close();
						socket.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	private void abrirPuertoTotem(int puerto) {
		new Thread() {
			public void run() {
				ServerSocket totemServerSocket;
				Socket socket;
				AdministradorDeTurnos admin = AdministradorDeTurnos.getInstance();
				BufferedReader myInput;
				// PrintStream myOutput;
				PrintWriter myOutput;
				String nuevoDni = null;
				try {
					totemServerSocket = new ServerSocket(puerto);
					while (true) { // nuevoDni==null //true
						// setMsj("Esperando conexion...");
						socket = totemServerSocket.accept();
						// setMsj("Conexión establecida con el puerto " + skt.getPort() + "\n");
						myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						myOutput = new PrintWriter(socket.getOutputStream(), true);
						nuevoDni = myInput.readLine();
						if (admin.agregarDni(nuevoDni))
							myOutput.println("Registro exitoso");
						else
							myOutput.println("El DNI ya se encuentra registrado.");
						myInput.close();
						myOutput.close();
						socket.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
