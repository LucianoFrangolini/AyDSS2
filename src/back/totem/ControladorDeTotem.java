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

public class ControladorDeTotem extends ConexionSocket {

	private String estado = "";

	public ControladorDeTotem() {
		this.host = ListaDeDirecciones.HOST; //"localhost" //InetAddress.getLocalHost().getCanonicalHostName();
		this.puerto = ListaDeDirecciones.PUERTO_TOTEM;
	}

	public String getEstado() {
		return estado;
	}
	private Boolean estadoAceptable() {
		Boolean ret = false;
		if(estado.equals("Registro exitoso") || estado.equals("El DNI ya se encuentra registrado."))
				ret = true;
		return ret;
	}

	public void enviarMensaje(String DNI) {
		//myOutput.print(DNI);
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
			JOptionPane.showMessageDialog(null, "No se pudo establecer una conexion con el servidor");
		}
	}

}
