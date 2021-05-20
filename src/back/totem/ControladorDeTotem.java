package back.totem;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import back.conexiones.ConexionSocket;
import back.constantes.ListaDeDirecciones;

/**
 * @author Grupo12 <br>
 *         Clase ControladorDeTotem con la lógica del Tótem. Se extiende de
 *         ConexiónSocket. <br>
 */
public class ControladorDeTotem extends ConexionSocket {

	private String estado;

	/**
	 * Constructor del ControladorDeTotem.<br>
	 */
	public ControladorDeTotem() {
		this.host = ListaDeDirecciones.HOST;
		this.puerto = ListaDeDirecciones.PUERTO_TOTEM;
		this.estado = "";
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
	private Boolean estadoAceptable() {
		Boolean ret = false;
		if (estado.equals("Registro exitoso") || estado.equals("El DNI ya se encuentra registrado."))
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
	public void enviarMensaje(String DNI) {
		this.estado = "";
		try {
			this.socket = new Socket(this.host, this.puerto);
			this.myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.myOutput = new PrintWriter(socket.getOutputStream(), true);
			myOutput.println(DNI);
			while (!estadoAceptable()) {
				this.estado = myInput.readLine();
			}
			myInput.close();
			myOutput.close();
			socket.close();
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
		}
	}

}
