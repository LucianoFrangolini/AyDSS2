package back.puestos;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import back.conexiones.ConexionSocket;
import back.constantes.ListaDeAcciones;
import back.constantes.ListaDeDirecciones;
import back.puestos.excepciones.PuestosAgotadosException;

/**
 * @author Grupo12 <br>
 *         Clase para un Puesto de trabajo, extiende de ConexionSocket. <br>
 */
public class Puesto extends ConexionSocket {

	private int numeroPuesto;
	private String clienteActual;

	/**
	 * Constructor para un Puesto de trabajo.<br>
	 * 
	 * @param numeroPuesto de tipo Integer: representa el numero de puesto que se va
	 *                     a crear.<br>
	 */
	public Puesto() {
		this.puerto = ListaDeDirecciones.PUERTO_PUESTOS;
		this.host = ListaDeDirecciones.HOST;
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
	public void enviarMensaje(String accion) {
		try {
			this.socket = new Socket(this.host, this.puerto);
			this.myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.myOutput = new PrintWriter(socket.getOutputStream(), true);
			myOutput.println(accion);
			if (accion.equals(ListaDeAcciones.ABRIR_PUESTO)) {
				String aux = myInput.readLine();
				
				//CAMBIAR POR VARIABLES
				
				if (aux.equalsIgnoreCase("error"))
					throw new PuestosAgotadosException("Se llegó al límite de puestos posibles");
				this.numeroPuesto = Integer.parseInt(aux);
				myInput.close();				
			}
			else if (accion.equals(ListaDeAcciones.LLAMAR_CLIENTE)) {
				myOutput.println(this.numeroPuesto);
				this.clienteActual = myInput.readLine();
				myInput.close();
			} else if (accion.equals(ListaDeAcciones.ELIMINAR_TURNO)) {
				myOutput.println(this.numeroPuesto);
			}
			myOutput.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
		} catch (PuestosAgotadosException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, e.getMessage());
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

}
