package back.puesto;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import clasesCompartidas.ListaDeAcciones;
import clasesCompartidas.ListaDeMensajes;
import back.puesto.excepciones.PuestosAgotadosException;
import interfacesCompartidas.Latido;
import back.puesto.interfaces.SolicitudDeActualizacion;

/**
 * @author Grupo12 <br>
 *         Clase para un Puesto de trabajo, extiende de ConexionSocket. <br>
 */
public class Puesto implements SolicitudDeActualizacion, Latido {

	private int numeroPuesto;
	private String clienteActual;
	
	private String ipConexionActual;
	private String ipServidor1;
	private String ipServidor2;
	private String ipMonitor;
	private int puertoConexionActual;
	private int puertoServidor1;
	private int puertoServidor2;
	private int puertoMonitor;
	private int tiempoHeartbeat;

	private ScheduledExecutorService scheduler;
	
	private BufferedReader myInput;
	private PrintWriter myOutput;
	private Socket socket;

	/**
	 * Constructor para un Puesto de trabajo.<br>
	 * 
	 * @param numeroPuesto de tipo Integer: representa el numero de puesto que se va
	 *                     a crear.<br>
	 */
	public Puesto() {
		cargarPropiedades();
		this.puertoConexionActual = this.puertoServidor1;
		this.ipConexionActual = this.ipServidor1;		
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(this, this.tiempoHeartbeat, this.tiempoHeartbeat, TimeUnit.MILLISECONDS);
	}

	/**
	 * Método getter del numero de puesto.<br>
	 * 
	 * @return Devuelve el numero del puesto.<br>
	 */
	public int getNumeroPuesto() {
		return numeroPuesto;
	}

	/**
	 * Método getter del dni del cliente actual.<br>
	 * 
	 * @return Devuelve el dni del cliente.<br>
	 */
	public String getClienteActual() {
		return clienteActual;
	}

	private void cargarPropiedades() {
		try {
			Properties p = new Properties();
			p.load(new FileReader("Libreria/propiedades/puestoDeTrabajo.properties"));			
			this.ipMonitor=p.getProperty("IP_MONITOR");
			this.ipServidor1=p.getProperty("IP_SERVIDOR_1");
			this.ipServidor2=p.getProperty("IP_SERVIDOR_2");
			this.puertoServidor1=Integer.parseInt(p.getProperty("PUERTO_SERVIDOR_1"));
			this.puertoServidor2=Integer.parseInt(p.getProperty("PUERTO_SERVIDOR_2"));
			this.puertoMonitor=Integer.parseInt(p.getProperty("PUERTO_MONITOR"));
			this.tiempoHeartbeat=Integer.parseInt(p.getProperty("TIEMPO_HEARTBEAT"));
		} catch (FileNotFoundException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "No se encontró el archivo de configuración");
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Se encontró un problema leyendo el archivo de configuración");
		}
	}
	
	/**
	 * Método que establece una conexion por Socket con un host a traves de un
	 * puerto determinado y que puede realizar distintas acciones dependiente del
	 * parametro de entrada.<br>
	 * 
	 * <b> Post: </b> Se abre un socket para enviar un mensaje y dependiendo el
	 * mensaje tambien recibir uno.<br>
	 * 
	 * @param accion de tipo String: representa la accion que se quiere
	 *               realizar.<br>
	 */
	@Override
	public void enviarMensaje(String accion) {
		establecerConexion();
		intercambio(accion);
		cerrarConexion();
	}

	private void resincronizacion() {
		if (this.puertoConexionActual == this.puertoServidor1) {
			this.puertoConexionActual=this.puertoServidor2;
			this.ipConexionActual=this.ipServidor2;
		}
		else {
			this.puertoConexionActual=this.puertoServidor1;
			this.ipConexionActual=this.ipServidor1;
		}
	}

	private void establecerConexion() {
		try {
			this.socket = new Socket(this.ipConexionActual, this.puertoConexionActual);
			this.myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.myOutput = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			try {
				resincronizacion();
				this.socket = new Socket(this.ipConexionActual, this.puertoConexionActual);
				this.myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.myOutput = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e2) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, Latido.MENSAJE_SIN_CONEXION);
			}
		}
	}

	private void intercambio(String accion) {
		try {
			myOutput.println(accion);
			if (accion.equals(ListaDeAcciones.ABRIR_PUESTO)) {
				String aux = myInput.readLine();
				if (aux.equalsIgnoreCase(ListaDeMensajes.ERROR))
					throw new PuestosAgotadosException(ListaDeMensajes.LIMITE_PUESTOS);
				this.numeroPuesto = Integer.parseInt(aux);

			} else if (accion.equals(ListaDeAcciones.LLAMAR_CLIENTE)) {
				myOutput.println(this.numeroPuesto);
				this.clienteActual = myInput.readLine();
				
			} else if (accion.equals(ListaDeAcciones.ELIMINAR_TURNO)
					|| accion.equalsIgnoreCase(ListaDeAcciones.CERRAR_PUESTO)) {
				myOutput.println(this.numeroPuesto);
			}
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, Latido.MENSAJE_SIN_CONEXION);
		} catch (PuestosAgotadosException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void cerrarConexion() {
		try {
			myInput.close();
			myOutput.close();
			socket.close();
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, Latido.MENSAJE_SIN_CONEXION);
		}
	}

	/**
	 * Método toString de la clase puesto.<br>
	 * 
	 * @return Devuelve el string que representa el objeto.<br>
	 */
	@Override
	public String toString() {
		return "Puesto numero:  " + numeroPuesto;
	}

	@Override
	public void run() {
		try {
            Socket socket = new Socket(this.ipMonitor,this.puertoMonitor);
            PrintWriter  pr = new PrintWriter(socket.getOutputStream(), true);
            pr.println("Puesto" + Integer.toString(numeroPuesto));
            pr.close();
            socket.close();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
	}

}
