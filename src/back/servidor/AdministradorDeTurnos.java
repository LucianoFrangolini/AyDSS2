package back.servidor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import back.constantes.ListaDeAcciones;
import back.constantes.ListaDeDirecciones;

//REVISAR IMPLEMENTS

public class AdministradorDeTurnos extends ConexionConServerSocket implements PropertyChangeListener  {
	private ListaDeTurnos listaDeTurnos = new ListaDeTurnos();
	private ColaDeEspera colaDeEspera = new ColaDeEspera();
	private static AdministradorDeTurnos instance;
	private PropertyChangeSupport pcs;
	
	//REVISAR
	private String hostPantalla = "localhost";

	public static AdministradorDeTurnos getInstance() {
		if (instance == null) {
			instance = new AdministradorDeTurnos();
		}
		return instance;
	}

	private AdministradorDeTurnos() {
		this.pcs = new PropertyChangeSupport(this);
		this.pcs.addPropertyChangeListener(this);
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
			//this.setTurno(turno);	//Es necesario para el patron PropertyChangeSupport/Listener
			pcs.firePropertyChange("turnoNuevo", null, turno);
			ret = true;
		}
		return ret;
	}
	
	private void eliminarTurno(Integer puesto) {
		//Boolean ret = false;
		this.listaDeTurnos.eliminarTurno(puesto);
		pcs.firePropertyChange("eliminarTurno", null, null);
		//return ret;
	}
	
	public String obtenerProximoCliente() {
		return this.colaDeEspera.poll();
	}
	
	public void setTurno(Turno turno) {
		Turno oldValue = null;
		pcs.firePropertyChange("turnoNuevo", null, turno);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if(arg0.getPropertyName().equals("turnoNuevo")) {
			abrirPuertoDisplay(ListaDeDirecciones.PUERTO_DISPLAY);
		} else if(arg0.getPropertyName().equals("eliminarTurno")) {
			abrirPuertoDisplay(ListaDeDirecciones.PUERTO_DISPLAY);
		}
	}
/*
	@Override
	public void setMsj(String nuevoMensaje) {
		String oldMsj = this.msj;
		this.msj = nuevoMensaje;
		this.pcs.firePropertyChange("Mensaje", oldMsj, this.msj);
	}*/
	/*
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}*/

	@Override
	public void abrirServidor() {

		abrirPuertoTotem(ListaDeDirecciones.PUERTO_TOTEM);
		abrirPuertoPuestos(ListaDeDirecciones.PUERTO_PUESTOS);
		//abrirPuertoDisplay(ListaDeDirecciones.PUERTO_DISPLAY);
		/*
		 * new Thread() { public void run() { try { Socket skt; myServerSocket = new
		 * ServerSocket(puerto); setMsj("Esperando conexion..."); skt =
		 * myServerSocket.accept(); setMsj("Conexión establecida con el puerto " +
		 * skt.getPort() + "\n"); } catch (IOException e) { e.printStackTrace(); } }
		 * }.start();
		 */
	}

	/*
	private void abrirPuertoDisplay(int puertoDisplay) {
		new Thread() {
			public void run() {
				ServerSocket puestosServerSocket;
				Socket socket;
				AdministradorDeTurnos admin = AdministradorDeTurnos.getInstance();
				BufferedReader myInput;
				PrintWriter myOutput;
				int numeroPuesto;
				String dni;
				
				
				ObjectOutputStream myObjectOutput;
				int tamañoLista = -1;
				try {
					puestosServerSocket = new ServerSocket(puertoDisplay);
					socket = puestosServerSocket.accept();
					while(admin.listaDeTurnos.size()!=tamañoLista) {
						tamañoLista = admin.listaDeTurnos.size();
						myObjectOutput = new ObjectOutputStream(socket.getOutputStream());
						//myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						//myOutput = new PrintWriter(socket.getOutputStream(), true);
						while(admin.listaDeTurnos.size()==tamañoLista) {
							
						}
						myObjectOutput.writeObject(listaDeTurnos);
						//myInput.close();
						myObjectOutput.close();
						//socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}*/
	
	public void abrirPuertoDisplay(int puertoDisplay) {
		try {
			Socket socket = new Socket(this.hostPantalla, puertoDisplay);
			// this.myInput = new BufferedReader(new
			// InputStreamReader(socket.getInputStream()));
			//ObjectInputStream myObjectInputStream = new ObjectInputStream(socket.getInputStream());
			// this.myOutput = new PrintWriter(socket.getOutputStream(), true);
			ObjectOutputStream myObjectOutput = new ObjectOutputStream(socket.getOutputStream());
			myObjectOutput.writeObject(listaDeTurnos);
			myObjectOutput.close();
			// myOutput.close();
			// myInput.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				String dni, accion;
				try {
					puestosServerSocket = new ServerSocket(puertoPuestos);
					while (true) {
						socket = puestosServerSocket.accept();
						myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						accion = myInput.readLine();
						numeroPuesto = Integer.parseInt(myInput.readLine());
						if(accion.equals(ListaDeAcciones.LLAMAR)) {
							dni = admin.obtenerProximoCliente();
							myOutput = new PrintWriter(socket.getOutputStream(), true);
							if (admin.agregarTurno(numeroPuesto, dni)) {
								myOutput.println(dni);
							} else
								myOutput.println("No hay clientes en espera");
							myOutput.close();
						}
						else if(accion.equals(ListaDeAcciones.ELIMINAR)) {
							admin.eliminarTurno(numeroPuesto);
						}
						myInput.close();
						socket.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	private void abrirPuertoTotem(int puertoTotem) {
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
					totemServerSocket = new ServerSocket(puertoTotem);
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
