package back.puestos;

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
import back.constantes.ListaDeAcciones;
import back.constantes.ListaDeDirecciones;
import back.constantes.ListaDeMensajes;
import back.puestos.excepciones.PuestosAgotadosException;
import back.puestos.interfaces.SolicitudDeActualizacion;

/**
 * @author Grupo12 <br>
 *         Clase para un Puesto de trabajo, extiende de ConexionSocket. <br>
 */
public class Puesto extends ConexionSocket implements SolicitudDeActualizacion, Runnable {

	private int numeroPuesto;
	private String clienteActual;
	
	private ScheduledExecutorService scheduler;
	private int initialDelay;
	private int periodicDelay;

	/**
	 * Constructor para un Puesto de trabajo.<br>
	 * 
	 * @param numeroPuesto de tipo Integer: representa el numero de puesto que se va
	 *                     a crear.<br>
	 */
	public Puesto() {
		this.puerto = ListaDeDirecciones.PUERTO_PUESTOS;
		this.host = ListaDeDirecciones.HOST;
		
		scheduler = Executors.newSingleThreadScheduledExecutor();
		this.initialDelay = 10000;
		this.periodicDelay = 10000;
		scheduler.scheduleAtFixedRate(this, initialDelay, periodicDelay, TimeUnit.MILLISECONDS);
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
		if (this.puerto == ListaDeDirecciones.PUERTO_PUESTOS)
			this.puerto = ListaDeDirecciones.PUERTO_PUESTOS_S2;
		else
			this.puerto = ListaDeDirecciones.PUERTO_PUESTOS;
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
			JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
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
			JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
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
			//CORREGIR SOCKET
            Socket socket = new Socket("localhost",3000);
            PrintWriter  pr = new PrintWriter(socket.getOutputStream(), true);
            pr.println("Puesto" + Integer.toString(numeroPuesto));
            pr.close();
            socket.close();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
	}

}
