package back.servidor;

import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import back.conexiones.Latido;
import back.servidor.interfaces.ActualizacionDisplay;
import back.servidor.interfaces.ActualizacionPuesto;
import back.servidor.interfaces.AdministracionDeCola;
import back.servidor.interfaces.AdministracionDeLista;
import back.servidor.interfaces.ValidacionDNI;

/**
 * @author Grupo12 <br>
 *         Clase del Administrador de Puestos que implementa el patrón
 *         Singleton. <br>
 */
public class Administrador implements PropertyChangeListener, ValidacionDNI, AdministracionDeCola,
		AdministracionDeLista, ActualizacionDisplay, ActualizacionPuesto, Latido {

	private ListaDeTurnos listaDeTurnos = new ListaDeTurnos();
	private ColaDeEspera colaDeEspera = new ColaDeEspera();
	private PropertyChangeSupport pcs;

	private String identificador;

	private String ipDisplay;
	private String ipServidor;
	private String ipMonitor;
	private int puertoTotem;
	private int puertoPuestos;
	private int puertoEntradaBackup;
	private int puertoEntradaSincronizacion;
	private int puertoDisplay;
	private int puertoMonitor;
	private int puertoServidorBackup;
	private int puertoServidorSincronizacion;

	private Boolean realizarBackup;

	private ServerTotem servidorTotem;
	private ServerPuestos servidorPuestos;
	private ServerBackup servidorBackup;
	private ServerSincronizacion servidorSincronizacion;

	private Integer[] puestosDeTrabajo = { 0, 0, 0, 0, 0, 0, 0, 0 };

	private ScheduledExecutorService scheduler;
	private int tiempoHeartbeat;

	/**
	 * Constructor para el administrador de turnos.<br>
	 */
	public Administrador(String identificador) {

		this.identificador = identificador;

		this.pcs = new PropertyChangeSupport(this);
		this.pcs.addPropertyChangeListener(this);
		
		cargarPropiedades(identificador);
		
		this.realizarBackup = true;

	}

	protected void setRealizarBackup(Boolean backup) {
		this.realizarBackup = backup;
	}

	protected void setListaDeTurnos(ListaDeTurnos listaDeTurnos) {
		this.listaDeTurnos = listaDeTurnos;
	}

	protected void setColaDeEspera(ColaDeEspera colaDeEspera) {
		this.colaDeEspera = colaDeEspera;
	}

	protected void setPuestosDeTrabajo(Integer[] puestosDeTrabajo) {
		this.puestosDeTrabajo = puestosDeTrabajo;
	}

	protected ListaDeTurnos getListaDeTurnos() {
		return listaDeTurnos;
	}

	protected ColaDeEspera getColaDeEspera() {
		return colaDeEspera;
	}

	protected Integer[] getPuestosDeTrabajo() {
		return puestosDeTrabajo;
	}
	
	private void cargarPropiedades(String identificador) {
		try {
			Properties p = new Properties();
			if (identificador.equalsIgnoreCase("Servidor1")) {
				p.load(new FileReader("src/propiedades/primerServidor.properties"));
				this.ipDisplay = p.getProperty("IP_DISPLAY");
				this.ipServidor = p.getProperty("IP_SERVIDOR_2");
				this.ipMonitor = p.getProperty("IP_MONITOR");
				this.puertoTotem = Integer.parseInt(p.getProperty("PUERTO_ENTRADA_TOTEM"));
				this.puertoPuestos = Integer.parseInt(p.getProperty("PUERTO_ENTRADA_PUESTOS"));
				this.puertoEntradaBackup = Integer.parseInt(p.getProperty("PUERTO_ENTRADA_BACKUP"));
				this.puertoEntradaSincronizacion = Integer.parseInt(p.getProperty("PUERTO_ENTRADA_SINCRONIZACION"));
				this.puertoDisplay = Integer.parseInt(p.getProperty("PUERTO_CONEXION_DISPLAY"));
				this.puertoMonitor = Integer.parseInt(p.getProperty("PUERTO_CONEXION_MONITOR"));
				this.puertoServidorBackup = Integer.parseInt(p.getProperty("PUERTO_SERVIDOR_2_BACKUP"));
				this.puertoServidorSincronizacion = Integer.parseInt(p.getProperty("PUERTO_SERVIDOR_2_SINCRONIZACION"));
				this.tiempoHeartbeat = Integer.parseInt(p.getProperty("TIEMPO_HEARTBEAT"));
				
			} else if (identificador.equalsIgnoreCase("Servidor2")) {
				p.load(new FileReader("src/propiedades/segundoServidor.properties"));
				this.ipDisplay = p.getProperty("IP_DISPLAY");
				this.ipServidor = p.getProperty("IP_SERVIDOR_1");
				this.ipMonitor = p.getProperty("IP_MONITOR");
				this.puertoTotem = Integer.parseInt(p.getProperty("PUERTO_ENTRADA_TOTEM"));
				this.puertoPuestos = Integer.parseInt(p.getProperty("PUERTO_ENTRADA_PUESTOS"));
				this.puertoEntradaBackup = Integer.parseInt(p.getProperty("PUERTO_ENTRADA_BACKUP"));
				this.puertoEntradaSincronizacion = Integer.parseInt(p.getProperty("PUERTO_ENTRADA_SINCRONIZACION"));
				this.puertoDisplay = Integer.parseInt(p.getProperty("PUERTO_CONEXION_DISPLAY"));
				this.puertoMonitor = Integer.parseInt(p.getProperty("PUERTO_CONEXION_MONITOR"));
				this.puertoServidorBackup = Integer.parseInt(p.getProperty("PUERTO_SERVIDOR_1_BACKUP"));
				this.puertoServidorSincronizacion = Integer.parseInt(p.getProperty("PUERTO_SERVIDOR_1_SINCRONIZACION"));
				this.tiempoHeartbeat = Integer.parseInt(p.getProperty("TIEMPO_HEARTBEAT"));
			}
		} catch (FileNotFoundException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "No se encontró el archivo de configuración");
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Se encontró un problema leyendo el archivo de configuración");
		}
	}

	/**
	 * Método encargado de enviar la orden de para abrir los puertos de conexión del
	 * totem y de los puestos de trabajo.<br>
	 * 
	 * <b> Post: </b> Se abren los puertos para la conexión del totem y los puestos
	 * de trabajo.<br>
	 */
	public void abrirServidor() {
		this.servidorTotem = new ServerTotem(this, this.puertoTotem);
		this.servidorTotem.start();

		this.servidorPuestos = new ServerPuestos(this, this.puertoPuestos);
		this.servidorPuestos.start();

		this.servidorBackup = new ServerBackup(this, this.puertoEntradaBackup);
		this.servidorBackup.start();
		
		this.servidorSincronizacion = new ServerSincronizacion(this,this.puertoEntradaSincronizacion);
		this.servidorSincronizacion.start();

		intentarSincronizar();
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(this, this.tiempoHeartbeat, this.tiempoHeartbeat, TimeUnit.SECONDS);
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
	@Override
	public Boolean validarDni(String dni) {
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
	@Override
	public Boolean agregarDni(String dni) {
		Boolean ret = false;
		if (validarDni(dni)) {
			this.colaDeEspera.add(dni);
			backup();
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
	@Override
	public Boolean agregarTurno(Integer puesto, String dni) {
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
	@Override
	public void eliminarTurno(Integer puesto) {
		this.listaDeTurnos.eliminarTurno(puesto);
		pcs.firePropertyChange("listaActualizada", null, null);
	}

	/**
	 * Método encargado de obtener el proximo dni de la cola de espera.<br>
	 * 
	 * @return devuelve un String con el dni del proximo cliente en la cola y lo
	 *         retira de la misma, o null si estaba vacía.
	 */
	@Override
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
		if (arg0.getPropertyName().equals("listaActualizada")) {
			actualizacionDisplay(this.puertoDisplay);
			backup();
		}
	}

	public void backup() {
		if (realizarBackup) {
			try {
				Socket socket = new Socket(this.ipServidor, this.puertoServidorBackup);
				ObjectOutputStream myObjectOutput = new ObjectOutputStream(socket.getOutputStream());
				myObjectOutput.writeObject(this.listaDeTurnos);
				myObjectOutput.writeObject(this.colaDeEspera);
				myObjectOutput.writeObject(this.puestosDeTrabajo);
				myObjectOutput.close();
				socket.close();
			} catch (UnknownHostException e) {
			} catch (IOException e) {
				this.realizarBackup = false;
			}
		}
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
	@Override
	public void actualizacionDisplay(int puertoDisplay) {
		try {
			Socket socket = new Socket(this.ipDisplay, puertoDisplay);
			ObjectOutputStream myObjectOutput = new ObjectOutputStream(socket.getOutputStream());
			myObjectOutput.writeObject(listaDeTurnos);
			myObjectOutput.close();
			socket.close();
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
	}

	public void intentarSincronizar() {
		ObjectInputStream obInput;
		Socket socket;
		try {
			socket = new Socket(this.ipServidor, this.puertoServidorSincronizacion);
			obInput = new ObjectInputStream(socket.getInputStream());
			this.setListaDeTurnos((ListaDeTurnos) obInput.readObject());
			this.setColaDeEspera((ColaDeEspera) obInput.readObject());
			this.setPuestosDeTrabajo((Integer[]) obInput.readObject());
			obInput.close();
			socket.close();
		} catch (ClassNotFoundException e) {
		} catch (UnknownHostException e) {
		} catch (IOException e) {
			this.realizarBackup = false;
		}
	}

	@Override
	public void run() {
		try {
			Socket socket = new Socket(this.ipMonitor, this.puertoMonitor);
			PrintWriter pr = new PrintWriter(socket.getOutputStream(), true);
			pr.println(identificador);
			pr.close();
			socket.close();
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}
	}

}
