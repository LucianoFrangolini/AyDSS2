package back.puestoDeTrabajo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import back.interfaces.conexiones.I_ConexionSocket;
import back.servidor.AdministradorDeTurnos;

public class Puesto implements I_ConexionSocket {
	AdministradorDeTurnos admin;
	
	public Puesto(AdministradorDeTurnos admin) {
		this.admin = admin;
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
