package back.totem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import back.interfaces.conexiones.I_ConexionSocket;

public class ControladorDeTotem implements I_ConexionSocket {

	private String host;
	private int puerto;

	public ControladorDeTotem() {
	}

	@Override
	public void enviarMensaje(String DNI) {

		Socket socket;
		try {
			this.host = InetAddress.getLocalHost().getCanonicalHostName();
			this.puerto = 9000;

			socket = new Socket(this.host, this.puerto);

			BufferedReader myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream myOutput = new PrintStream(socket.getOutputStream());

			myOutput.print(DNI);
			myOutput.close();
			socket.close();

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el totem");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al enviar o recibir mensaje");
		}
	}

}
