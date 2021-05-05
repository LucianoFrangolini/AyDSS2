package pack.cliente.probando;

import java.io.*;
import java.net.*;

import pack.server.probando.ControladorServer;
import pack.server.probando.IVistaServer;
import pack.server.probando.VistaServer;

public class MainClient {

	public static void main(String[] args) {
		
		IVistaCliente vistaCliente = new VistaCliente();
		ControladorCliente controlador = new ControladorCliente(vistaCliente);
		vistaCliente.setListener(controlador);
		vistaCliente.setVisible();		
		
		
		/*String host="localhost";
		int port=9999;
		
		try {
			System.out.println("Soy el cliente "+host+" que intentará conectarse al servidor\n");
			
			//Socket(IP desde la que me quiero conectar, puerto al que me quiero conectar)
			Socket skt = new Socket(host,port);
			
			BufferedReader myInput = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			PrintStream myOutput = new PrintStream(skt.getOutputStream());
			
			myOutput.print("Hi\n");
			
			String buf = myInput.readLine();
			if (buf!=null) {
				System.out.println("El cliente recibio "+buf+" del servidor\n");
			}
			
			skt.close();
			System.out.println("El cliente se desconecta");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}
}
