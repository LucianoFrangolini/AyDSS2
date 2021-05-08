package back.pantalla;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import back.conexiones.ConexionSocket;
import back.constantes.ListaDeDirecciones;
import back.servidor.AdministradorDeTurnos;
import back.servidor.ListaDeTurnos;

public class Display extends ConexionSocket {

	// private String listaDeLlamados;
	private ListaDeTurnos listaDeLlamados = null;
	public PropertyChangeSupport pcs;

	public Display() {
		this.puerto = ListaDeDirecciones.PUERTO_DISPLAY;
		this.host = ListaDeDirecciones.HOST;
		this.pcs = new PropertyChangeSupport(this);
		this.establecerConexion();
	}

	
    public void setListaLlamados(ListaDeTurnos listaDeLlamados) {
    	ListaDeTurnos oldValue = this.listaDeLlamados;
        this.listaDeLlamados = listaDeLlamados;
        pcs.firePropertyChange("lista de llamados",oldValue, listaDeLlamados);
    }


	public void establecerConexion() {
		new Thread() {
			public void run() {
				ServerSocket displayServerSocket;
				Socket socket;
				try {
					displayServerSocket = new ServerSocket(puerto);
					while (true) {
						socket = displayServerSocket.accept();
						ObjectInputStream myObjectInputStream = new ObjectInputStream(socket.getInputStream());
						setListaLlamados((ListaDeTurnos) myObjectInputStream.readObject());
					}
				} catch (IOException e) {
					e.printStackTrace();
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
