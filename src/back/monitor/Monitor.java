package back.monitor;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Monitor implements Runnable {

	private Thread servidor;
	private int puerto;
	private PropertyChangeSupport pcs;

	// Pasar el puerto y el host
	public Monitor() {
		servidor = new Thread(this, "Monitor");
		servidor.start();
	}

	public PropertyChangeSupport getPcs() {
		return this.pcs;
	}

	@Override
	public void run() {
		ServerSocket monitorServerSocket;
		Socket socket;
		BufferedReader myInput;
		String aux;
		try {
			monitorServerSocket = new ServerSocket(this.puerto);
			while (true) {
				socket = monitorServerSocket.accept();
				myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				aux = myInput.readLine();
				switch (aux) {
				case "Totem": {
					pcs.firePropertyChange("TotemEnLinea", null, null);
				}
				case "Servidor1": {
					pcs.firePropertyChange("Servidor1EnLinea", null, null);
				}
				case "Servidor2": {
					pcs.firePropertyChange("Servidor2EnLinea", null, null);
				}
				case "Display": {
					pcs.firePropertyChange("DisplayEnLinea", null, null);
				}
				case "Puesto1": {
					pcs.firePropertyChange("Puesto1EnLinea", null, null);
				}
				case "Puesto2": {
					pcs.firePropertyChange("Puesto2EnLinea", null, null);
				}
				case "Puesto3": {
					pcs.firePropertyChange("Puesto3EnLinea", null, null);
				}
				case "Puesto4": {
					pcs.firePropertyChange("Puesto4EnLinea", null, null);
				}
				case "Puesto5": {
					pcs.firePropertyChange("Puesto5EnLinea", null, null);
				}
				case "Puesto6": {
					pcs.firePropertyChange("Puesto6EnLinea", null, null);
				}
				case "Puesto7": {
					pcs.firePropertyChange("Puesto7EnLinea", null, null);
				}
				case "Puesto8": {
					pcs.firePropertyChange("Puesto8EnLinea", null, null);
				}
				}
				socket.close();
			}
		} catch (IOException e) {
		}
	}

}
