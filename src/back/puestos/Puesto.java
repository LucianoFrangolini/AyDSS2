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

public class Puesto extends ConexionSocket {

	private int numeroPuesto;
	private String clienteActual;

	public Puesto(int numeroPuesto) {
		this.numeroPuesto = numeroPuesto;
		this.puerto = ListaDeDirecciones.PUERTO_PUESTOS;
		this.host = ListaDeDirecciones.HOST;
	}

	public int getNumeroPuesto() {
		return numeroPuesto;
	}
	
	public void enviarMensaje(String accion) {
		try {
			this.socket = new Socket(this.host,this.puerto);
			this.myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.myOutput = new PrintWriter(socket.getOutputStream(), true);
			
			myOutput.println(accion);
			if(accion.equals(ListaDeAcciones.LLAMAR)) {
				myOutput.println(this.numeroPuesto);
				this.clienteActual = myInput.readLine();
				myInput.close();
			}
			else if(accion.equals(ListaDeAcciones.ELIMINAR)) {
				myOutput.println(this.numeroPuesto);
			}
			myOutput.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);
		}
	}
	
	public String getClienteActual() {
		return clienteActual;
	}

	@Override
	public String toString() {
		return "Puesto numero:  " + numeroPuesto;
	}
	
	

}
