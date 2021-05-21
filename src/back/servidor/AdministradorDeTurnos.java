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
 * @author Grupo12 <br>
 *         Clase del Administrador de Puestos que implementa el patrón
 *         Singleton. <br>
 */
public class AdministradorDeTurnos implements PropertyChangeListener {
	private ListaDeTurnos listaDeTurnos = new ListaDeTurnos();
	private ColaDeEspera colaDeEspera = new ColaDeEspera();
	private static AdministradorDeTurnos instance;
	private PropertyChangeSupport pcs;
	private String hostPantalla;
	private static int[] puestosDisponibles = {0,0,0};

	/**
	 * Método encargado de obtener la instancia de la clase
	 * AdministradorDeTurnos.<br>
	 * 
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
	 * Método encargado de validar si el dni ya se encuentra registrado en la
	 * cola.<br>
	 * 
	 * @param dni de tipo String: Representa el dni a validar.<br>
	 * 
	 * @return true si el dni no se encuentra en la cola de espera, false en caso
	 *         contrario.
	 */
	private Boolean validarDni(String dni) {
		return !this.colaDeEspera.contains(dni);
	}

	/**
	 * Método encargado de agregar un dni en la cola de espera.<br>
	 * 
	 * @param dni de tipo String: Representa el dni a agregar en la cola de
	 *            espera<br>
	 * 
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
	 * 
	 * <b> Post: </b> Si el dni es valido se crea un turno, se lo agrego a la lista
	 * de turnos y se dispara una accion de lista actualizada.<br>
	 * 
	 * @param puesto de tipo Integer: Representa el puesto con el que se crea un
	 *               turno.<br>
	 * @param dni    de tipo String: Representa el dni con el que se crea un
	 *               turno<br>
	 * 
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
	 * Método encargado de eliminar un turno y disparar una accion de que la lista
	 * de turnos se actualizo.<br>
	 * 
	 * <b> Post: </b> Si elimina el turno con el numero de puesto correspondiente si
	 * es que existe y se dispara una accion de lista actualizada.<br>
	 * 
	 * @param puesto de tipo Integer: Representa el numero de puesto del turno a
	 *               eliminar<br>
	 */
	private void eliminarTurno(Integer puesto) {
		this.listaDeTurnos.eliminarTurno(puesto);
		pcs.firePropertyChange("listaActualizada", null, null);
	}

	/**
	 * Método encargado de obtener el proximo dni de la cola de espera.<br>
	 * 
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo
	 *         retira de la misma, o null si estaba vacía.
	 */
	public String obtenerProximoCliente() {
		return this.colaDeEspera.poll();
	}

	/**
	 * Método encargado de disparar una accion cuando la propiedad observada sufrió
	 * cambios.<br>
	 * 
	 * @param arg0 de tipo PropertyChangeEvent: es el objeto que contiene atributos
	 *             sobre el cambio en el objeto observado.<br>
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("listaActualizada"))
			abrirPuertoDisplay(ListaDeDirecciones.PUERTO_DISPLAY);
	}

	/**
	 * Método encargado de enviar la orden de para abrir los puertos de conexión del
	 * totem y de los puestos de trabajo.<br>
	 * 
	 * <b> Post: </b> Se abren los puertos para la conexión del totem y los puestos
	 * de trabajo.<br>
	 */
	public void abrirServidor() {
		abrirPuertoTotem(ListaDeDirecciones.PUERTO_TOTEM);
		abrirPuertoPuestos(ListaDeDirecciones.PUERTO_PUESTOS);
	}

	/**
	 * Método encargado de establecer una conexión socket con el Display o
	 * pantalla.<br>
	 * 
	 * <b> Post: </b> Se envía un objeto de tipo ListaDeTurnos al Display.<br>
	 * 
	 * @param puertoDisplay de tipo int: Representa el puerto con el cual establecer
	 *                      una conexión. <br>
	 */
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

	/**
	 * Método encargado de abrir una conexion socket para que los puestos de trabajo
	 * comuniquen sus solicitudes <br>
	 * 
	 * * <b> Post: </b> Se abre un serverSocket a la espera de recibir solicitudes
	 * de los puestos de trabajo.<br>
	 * 
	 * @param puertoPuestos de tipo int: Representa el puerto a abrir para realizar
	 *                      la conexión.<br>
	 */
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
						myOutput = new PrintWriter(socket.getOutputStream(), true);
						accion = myInput.readLine();
						if (accion.equals(ListaDeAcciones.ABRIR_PUESTO)) {	
							int nuevoNumPuesto = buscaYOcupaPuesto();
							if (nuevoNumPuesto!=0)
								myOutput.println(nuevoNumPuesto);
							else
								myOutput.println("error");
						} else {
							numeroPuesto = Integer.parseInt(myInput.readLine());
							if (accion.equals(ListaDeAcciones.LLAMAR_CLIENTE)) {
								dni = admin.obtenerProximoCliente();
								if (admin.agregarTurno(numeroPuesto, dni)) {
									myOutput.println(dni);
								} else
									myOutput.println("No hay clientes en espera");
							} else if (accion.equals(ListaDeAcciones.ELIMINAR_TURNO)) {
								admin.eliminarTurno(numeroPuesto);
							} else if (accion.equals(ListaDeAcciones.CERRAR_PUESTO)) {
								
							}
						}
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
	
	private static int buscaYOcupaPuesto() {
		int i=0;
		while (i<puestosDisponibles.length && puestosDisponibles[i]!=0)
			i++;
		if (i<puestosDisponibles.length)
			puestosDisponibles[i]=1;
		else
			i=-1;
		return i+1;
	}

	/**
	 * Método encargado de abrir una conexion socket para que el totem comunique sus
	 * solicitudes <br>
	 * 
	 * * <b> Post: </b> Se abre un serverSocket a la espera de recibir solicitudes
	 * del totem.<br>
	 * 
	 * @param puertoTotem de tipo int: Representa el puerto a abrir para realizar la
	 *                    conexión.<br>
	 */
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
