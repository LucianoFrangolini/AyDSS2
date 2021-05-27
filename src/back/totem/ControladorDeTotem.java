package back.totem;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import back.conexiones.ConexionSocket;
import back.constantes.ListaDeDirecciones;
import back.constantes.ListaDeMensajes;
import back.totem.interfaces.EnvioDNI;

/**
 * @author Grupo12 <br>
 *         Clase ControladorDeTotem con la lógica del Tótem. Se extiende de
 *         ConexiónSocket. <br>
 */
public class ControladorDeTotem extends ConexionSocket implements EnvioDNI, Runnable {
	
	private String estado;
	
	private ScheduledExecutorService scheduler;
	private int initialDelay;
	private int periodicDelay;

	/**
	 * Constructor del ControladorDeTotem.<br>
	 */
	public ControladorDeTotem() {
		this.host = ListaDeDirecciones.HOST;
		this.puerto = ListaDeDirecciones.PUERTO_TOTEM;
		this.estado = "";
		
		scheduler = Executors.newSingleThreadScheduledExecutor();
		this.initialDelay = 10000;
		this.periodicDelay = 10000;
		scheduler.scheduleAtFixedRate(this, initialDelay, periodicDelay, TimeUnit.MILLISECONDS);
	}

	/**
	 * Método getter del estado del display del Totem<br>
	 * 
	 * @return estado del display del totem.<br>
	 */
	public String getEstado() {
		return estado;
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
		if (this.puerto == ListaDeDirecciones.PUERTO_TOTEM)
			this.puerto = ListaDeDirecciones.PUERTO_TOTEM_S2;
		else
			this.puerto = ListaDeDirecciones.PUERTO_TOTEM;
	}

	private void establecerConexion() {
		try {
			this.socket = new Socket(this.host, this.puerto);
			this.myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.myOutput = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			try {
				resincronizacion();
				this.socket = new Socket(this.host, this.puerto);				
				this.myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.myOutput = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e2) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
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
				JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
			} catch (NullPointerException e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
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
			JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
		}
	}

	@Override
	public void run() {
		try {
			//CORREGIR SOCKET
            Socket socket = new Socket("localhost",3000);
            PrintWriter  pr = new PrintWriter(socket.getOutputStream(), true);
            pr.println("Totem");
            pr.close();
            socket.close();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
	}

}
