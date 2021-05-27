package back.pantalla;

import java.awt.Toolkit;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import back.conexiones.ConexionSocket;
import back.constantes.ListaDeDirecciones;
import back.pantalla.interfaces.Visualizacion;
import back.servidor.ListaDeTurnos;

/**
 * @author Grupo12 <br>
 *         Clase para el Display que extiende de ConexionSocket. <br>
 */
public class Display extends ConexionSocket implements Visualizacion, Runnable {

	private ListaDeTurnos listaDeLlamados = null;
	private PropertyChangeSupport pcs;
	
	private ScheduledExecutorService scheduler;
	private int initialDelay;
	private int periodicDelay;

	/**
	 * Constructor para el display<br>
	 */
	public Display() {
		this.puerto = ListaDeDirecciones.PUERTO_DISPLAY;
		this.pcs = new PropertyChangeSupport(this);
		this.abrirPuertoDeConexion();
		
		scheduler = Executors.newSingleThreadScheduledExecutor();
		this.initialDelay = 10000;
		this.periodicDelay = 10000;
		scheduler.scheduleAtFixedRate(this, initialDelay, periodicDelay, TimeUnit.MILLISECONDS);
	}

	/**
	 * Método encargado de actualizar la lista de llamados y disparar el evento de
	 * su cambio.<br>
	 * 
	 * <b> Post: </b> El atributo listaDeLlamados es seteado y se dispara un evento
	 * con el cambio del mismo.<br>
	 * 
	 * @param listaDeLlamados de tipo ListaDeTurnos: es el objeto que va a recibir
	 *                        cambios.<br>
	 * 
	 */
	@Override
	public void setListaLlamados(ListaDeTurnos listaDeLlamados) {
		ListaDeTurnos oldValue = this.listaDeLlamados;
		this.listaDeLlamados = listaDeLlamados;
		pcs.firePropertyChange("lista de llamados", oldValue, listaDeLlamados);
	}

	/**
	 * Método getter del PropertyChangeSupport de la clase.<br>
	 * 
	 * @return Devuelve el PropertyChangeSupport de la clase.<br>
	 */
	public PropertyChangeSupport getPcs() {
		return this.pcs;
	}

	/**
	 * Método encargado de abrir una conexion socket para que se le puedan enviar
	 * objetos y actualizar su propio atributo listaDeLlamados.<br>
	 * 
	 * * <b> Post: </b> Se abre un serverSocket a la espera de recibir objetos de
	 * tipo ListaDeTurnos.<br>
	 */
	public void abrirPuertoDeConexion() {
		new Thread() {
			public void run() {
				ServerSocket displayServerSocket;
				Socket socket;
				try {
					displayServerSocket = new ServerSocket(puerto);
					while (true) {
						socket = displayServerSocket.accept();
						ObjectInputStream myObjectInputStream = new ObjectInputStream(socket.getInputStream());
						setListaLlamados((ListaDeTurnos) myObjectInputStream.readObject());
					}
				} catch (IOException e) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	public void run() {
		try {
			//CORREGIR SOCKET
            Socket socket = new Socket("localhost",3000);
            PrintWriter  pr = new PrintWriter(socket.getOutputStream(), true);
            pr.println("Display");
            pr.close();
            socket.close();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
	}
}
