package pack.server.probando;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class LogicaServer {

	private static LogicaServer instance;
	private static ServerSocket myServerSocket;
	private Socket skt;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private String msj="";

	public static LogicaServer getInstance() {
		if (instance == null)
			instance = new LogicaServer();
		return instance;
	}

	private void setMsj(String nuevoMensaje) {
		String oldMsj = this.msj;
		this.msj = nuevoMensaje;
		this.pcs.firePropertyChange("Mensaje",oldMsj,this.msj);
		
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void abrirServidor(String ip, int puerto) {
		
		try {
			myServerSocket = new ServerSocket(puerto);
			/*"Servidor\nIP:" + InetAddress.getLocalHost().getCanonicalHostName() + "\nPuerto:"
			+ myServerSocket.getLocalPort() + "\n\nEsperando conexión...\n\n"*/
			
			setMsj("Esperando conexion...");
			
			this.skt = myServerSocket.accept();
			

			/*System.out.println("Conexión establecida con el puerto " + skt.getPort() + "\n\n");

			BufferedReader myInput = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			PrintStream myOutput = new PrintStream(skt.getOutputStream());

			String buf = myInput.readLine();

			if (buf != null) {
				System.out.println("El servidor leyo: " + buf);
				myOutput.print("oka");
			}

			skt.close();
			
			System.out.println("Saliendo del servidor");*/

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
