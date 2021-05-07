package back.totem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import back.interfaces.conexiones.I_ConexionSocket;

public class ControladorDeTotem implements I_ConexionSocket {

	private String host;
	private int puerto;
	private String estado = "";
	BufferedReader myInput = null;
	PrintWriter myOutput = null;
	Socket socket;

	public ControladorDeTotem() {
		this.host = "localhost"; //InetAddress.getLocalHost().getCanonicalHostName();
		this.puerto = 9000;
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

	@Override
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
			e.printStackTrace();
		}
	}

}
