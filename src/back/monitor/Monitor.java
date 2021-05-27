package back.monitor;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Monitor implements Runnable {
	private static Monitor instance;
	private Thread servidor;
	private int puerto;
	private PropertyChangeSupport pcs;

	private HiloTimeOut totem;
	private HiloTimeOut servidor1;
	private HiloTimeOut servidor2;
	private HiloTimeOut display;
	private HiloTimeOut puesto1;
	private HiloTimeOut puesto2;
	private HiloTimeOut puesto3;
	private HiloTimeOut puesto4;
	private HiloTimeOut puesto5;
	private HiloTimeOut puesto6;
	private HiloTimeOut puesto7;
	private HiloTimeOut puesto8;

	// Pasar el puerto y el host
	private Monitor() {
		this.puerto = 3000;
		servidor = new Thread(this, "Monitor");
		this.pcs = new PropertyChangeSupport(this);
		servidor.start();
	}

	public static Monitor getInstance() {
		if (instance == null)
			instance = new Monitor();
		return instance;
	}

	public PropertyChangeSupport getPcs() {
		return this.pcs;
	}

	public void abrirTimers() {
		totem = new HiloTimeOut("Totem");
		servidor1 = new HiloTimeOut("Servidor1");
		servidor2 = new HiloTimeOut("Servidor2");
		display = new HiloTimeOut("Display");
		puesto1 = new HiloTimeOut("Puesto1");
		puesto2 = new HiloTimeOut("Puesto2");
		puesto3 = new HiloTimeOut("Puesto3");
		puesto4 = new HiloTimeOut("Puesto4");
		puesto5 = new HiloTimeOut("Puesto5");
		puesto6 = new HiloTimeOut("Puesto6");
		puesto7 = new HiloTimeOut("Puesto7");
		puesto8 = new HiloTimeOut("Puesto8");
	}

	@Override
	public void run() {
		ServerSocket monitorServerSocket;
		Socket socket;
		BufferedReader myInput;
		String aux;
		// REVISAR
		abrirTimers();
		try {
			monitorServerSocket = new ServerSocket(this.puerto);
			while (true) {
				socket = monitorServerSocket.accept();
				myInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				aux = myInput.readLine();
				switch (aux) {
					case "Totem": {
						totem.setLatido("TotemEnLinea");
						pcs.firePropertyChange("TotemEnLinea", null, null);
						break;
					}
					case "Servidor1": {
						servidor1.setLatido("Servidor1EnLinea");
						pcs.firePropertyChange("Servidor1EnLinea", null, null);
						break;
					}
					case "Servidor2": {
						servidor2.setLatido("Servidor2EnLinea");
						pcs.firePropertyChange("Servidor2EnLinea", null, null);
						break;
					}
					case "Display": {
						display.setLatido("DisplayEnLinea");
						pcs.firePropertyChange("DisplayEnLinea", null, null);
						break;
					}
					case "Puesto1": {
						puesto1.setLatido("Puesto1EnLinea");
						pcs.firePropertyChange("Puesto1EnLinea", null, null);
						break;
					}
					case "Puesto2": {
						puesto2.setLatido("Puesto2EnLinea");
						pcs.firePropertyChange("Puesto2EnLinea", null, null);
						break;
					}
					case "Puesto3": {
						puesto3.setLatido("Puesto3EnLinea");
						pcs.firePropertyChange("Puesto3EnLinea", null, null);
						break;
					}
					case "Puesto4": {
						puesto4.setLatido("Puesto4EnLinea");
						pcs.firePropertyChange("Puesto4EnLinea", null, null);
						break;
					}
					case "Puesto5": {
						puesto5.setLatido("Puesto5EnLinea");
						pcs.firePropertyChange("Puesto5EnLinea", null, null);
						break;
					}
					case "Puesto6": {
						puesto6.setLatido("Puesto6EnLinea");
						pcs.firePropertyChange("Puesto6EnLinea", null, null);
						break;
					}
					case "Puesto7": {
						puesto7.setLatido("Puesto7EnLinea");
						pcs.firePropertyChange("Puesto7EnLinea", null, null);
						break;
					}
					case "Puesto8": {
						puesto8.setLatido("Puesto8EnLinea");
						pcs.firePropertyChange("Puesto8EnLinea", null, null);
						break;
					}
				}
				socket.close();
			}
		} catch (IOException e) {
		}
	}

}
