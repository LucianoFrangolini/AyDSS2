package pack.cliente.probando;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import pack.server.probando.LogicaServer;

public class LogicaCliente {

	private static LogicaCliente instance;
	String host="localhost";
	int port=9999;
	
	public static LogicaCliente getInstance() {
		if (instance == null)
			instance = new LogicaCliente();
		return instance;
	}
	
	public void enviarMensaje(String host,int puerto,String texto) {
		
		Socket skt;
		
		try {
			skt = new Socket(host,puerto);
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
}
