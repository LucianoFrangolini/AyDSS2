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

/**
 * @author Grupo12
 * <br>
 * Clase del Administrador de Puestos que implementa el patrón Singleton.
 * <br>
 */
public class AdministradorDeTurnos implements PropertyChangeListener {
	private ListaDeTurnos listaDeTurnos = new ListaDeTurnos();
	private ColaDeEspera colaDeEspera = new ColaDeEspera();
	private static AdministradorDeTurnos instance;
	private PropertyChangeSupport pcs;
	private String hostPantalla;
	
	/**
	 * Método encargado de obtener la instancia de la clase AdministradorDeTurnos.<br>
	 * @return una instancia de AdministradorDeTurnos.
	 */
	public static AdministradorDeTurnos getInstance() {
		if (instance == null) {
			instance = new AdministradorDeTurnos();
		}
		return instance;
	}
	
	/**
	 * Constructor para el administrador de turnos.<br>
	 */
	private AdministradorDeTurnos() {
		this.pcs = new PropertyChangeSupport(this);
		this.pcs.addPropertyChangeListener(this);
		this.hostPantalla = ListaDeDirecciones.HOST;
	}
	
	/**
	 * Método encargado de validar si el dni ya se encuentra registrado en la cola.<br>
	 * @return true si el dni no se encuentra en la cola de espera, false en caso contrario.
	 */
	private Boolean validarDni(String dni) {
		return !this.colaDeEspera.contains(dni);
	}
	
	/**
	 * Método encargado de agregar un dni en la cola de espera.<br>
	 * @return true si pudo agregar el dni en la cola, falso en caso contrario.
	 */
	public Boolean agregarDni(String dni) {
		Boolean ret = false;
		if (validarDni(dni)) {
			this.colaDeEspera.add(dni);
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Método encargado de agregar un turno en la lista de turnos.<br>
	 * <b> Post: </b> Si el dni es valido se crea un turno, se lo agrego a la lista de turnos y se dispara una accion de lista actualizada.<br>
	 * @return true si pudo agregar el dni en la cola, falso en caso contrario.
	 */
	private Boolean agregarTurno(Integer puesto, String dni) {
		Boolean ret = false;
		if (dni != null) {
			Turno turno = new Turno(puesto, dni);
			this.listaDeTurnos.agregarTurno(turno);
			pcs.firePropertyChange("listaActualizada", null, turno);
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Método encargado de eliminar un turno y disparar una accion de que la lista de turnos se actualizo.<br>
	 * <b> Post: </b> Si elimina el turno con el numero de puesto correspondiente si es que existe y se dispara una accion de lista actualizada.<br>
	 */
	private void eliminarTurno(Integer puesto) {
		this.listaDeTurnos.eliminarTurno(puesto);
		pcs.firePropertyChange("listaActualizada", null, null);
	}
	
	/**
	 * Método encargado de obtener el proximo dni de la cola de espera.<br>
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo retira de la misma, o null si estaba vacía.
	 */
	public String obtenerProximoCliente() {
		return this.colaDeEspera.poll();
	}
	
	/**
	 * Método encargado de disparar una accion cuando la propiedad observada sufrió cambios.<br>
	 * @param arg0vista de tipo PropertyChangeEvent: es el objeto que contiene atributos sobre el cambio en el objeto observado.<br>
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("listaActualizada"))
			abrirPuertoDisplay(ListaDeDirecciones.PUERTO_DISPLAY);
	}
	
	/**
	 * Método encargado de eliminar un turno y disparar una accion de que la lista de turnos se actualizo.<br>
	 * <b> Post: </b> Si elimina el turno con el numero de puesto correspondiente si es que existe y se dispara una accion de lista actualizada.<br>
	 */
	public void abrirServidor() {
		abrirPuertoTotem(ListaDeDirecciones.PUERTO_TOTEM);
		abrirPuertoPuestos(ListaDeDirecciones.PUERTO_PUESTOS);
	}

	public void abrirPuertoDisplay(int puertoDisplay) {
		try {
			Socket socket = new Socket(this.hostPantalla, puertoDisplay);
			ObjectOutputStream myObjectOutput = new ObjectOutputStream(socket.getOutputStream());
			myObjectOutput.writeObject(listaDeTurnos);
			myObjectOutput.close();
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
						if (accion.equals(ListaDeAcciones.LLAMAR)) {
							dni = admin.obtenerProximoCliente();
							myOutput = new PrintWriter(socket.getOutputStream(), true);
							if (admin.agregarTurno(numeroPuesto, dni)) {
								myOutput.println(dni);
							} else
								myOutput.println("No hay clientes en espera");
							myOutput.close();
						} else if (accion.equals(ListaDeAcciones.ELIMINAR)) {
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
				PrintWriter myOutput;
				String nuevoDni = null;
				try {
					totemServerSocket = new ServerSocket(puertoTotem);
					while (true) {
						socket = totemServerSocket.accept();
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
