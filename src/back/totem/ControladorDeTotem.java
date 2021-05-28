package back.totem;

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

import back.conexiones.Latido;
import back.constantes.ListaDeMensajes;
import back.totem.interfaces.EnvioDNI;

/**
 * @author Grupo12 <br>
 *         Clase ControladorDeTotem con la lógica del Tótem. Se extiende de
 *         ConexiónSocket. <br>
 */
public class ControladorDeTotem implements EnvioDNI, Latido {
	
	private String estado;
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
	 * Constructor del ControladorDeTotem.<br>
	 */
	public ControladorDeTotem() {
		cargarPropiedades();
		this.estado = "";
		this.puertoConexionActual = this.puertoServidor1;
		this.ipConexionActual = this.ipServidor1;
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(this, this.tiempoHeartbeat, this.tiempoHeartbeat, TimeUnit.SECONDS);
	}

	/**
	 * Método getter del estado del display del Totem<br>
	 * 
	 * @return estado del display del totem.<br>
	 */
	public String getEstado() {
		return estado;
	}
	
	private void cargarPropiedades() {
		try {
			Properties p = new Properties();
			p.load(new FileReader("src/propiedades/totem.properties"));			
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
	 * Método encargado de decidir si el estado del tótem es aceptable o no para
	 * enviar un nuevo mensaje por socket.<br>
	 * 
	 * @return true si el estado es aceptable, false si el estado no es
	 *         aceptable.<br>
	 */
	private Boolean estadoAceptable() throws NullPointerException {
		Boolean ret = false;
		if (estado.equals(ListaDeMensajes.REGISTRO_EXITOSO) || estado.equals(ListaDeMensajes.DNI_EXISTENTE))
			ret = true;
		return ret;
	}

	/**
	 * Método encargado de enviar un mensaje por socket.<br>
	 * 
	 * <b> Post: </b> Se envía un mensaje al servidor por socket y se recibe una
	 * respuesta.<br>
	 * 
	 * @param DNI de tipo String: Representa el DNI a enviar por socket.<br>
	 */
	@Override
	public void enviarMensaje(String DNI) {
		this.estado = "";
		establecerConexion();
		intercambio(DNI);
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

	private void intercambio(String DNI) {
		myOutput.println(DNI);
		while (!estadoAceptable()) {
			try {
				this.estado = myInput.readLine();
			} catch (IOException e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, Latido.MENSAJE_SIN_CONEXION);
			} catch (NullPointerException e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, Latido.MENSAJE_SIN_CONEXION);
			}
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

	@Override
	public void run() {
		try {
            Socket socket = new Socket(this.ipMonitor,this.puertoMonitor);
            PrintWriter  pr = new PrintWriter(socket.getOutputStream(), true);
            pr.println("Totem");
            pr.close();
            socket.close();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
	}

}
