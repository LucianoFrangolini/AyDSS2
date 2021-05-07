package back.puestos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import back.interfaces.conexiones.I_ConexionSocket;

public class Puesto implements I_ConexionSocket {
	int numeroPuesto, puerto;

	public Puesto(int numeroPuesto, int puerto) {
		this.numeroPuesto = numeroPuesto;
		this.puerto = puerto;
	}

	public void enviarMensaje(String host, int puerto, String texto) {
		Socket skt;
		try {
			skt = new Socket(host,puerto);
			//myInput necesario?
			BufferedReader myInput = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			PrintStream myOutput = new PrintStream(skt.getOutputStream());
			myOutput.print(texto);
			myOutput.close();
			skt.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enviarMensaje(String texto) {
	}

}
