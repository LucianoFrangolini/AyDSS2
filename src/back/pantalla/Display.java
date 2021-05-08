package back.pantalla;

import java.awt.Toolkit;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import back.conexiones.ConexionSocket;
import back.constantes.ListaDeDirecciones;
import back.servidor.ListaDeTurnos;

public class Display extends ConexionSocket {

	private ListaDeTurnos listaDeLlamados = null;
	public PropertyChangeSupport pcs;

	public Display() {
		this.puerto = ListaDeDirecciones.PUERTO_DISPLAY;
		this.pcs = new PropertyChangeSupport(this);
		this.establecerConexion();
	}

	public void setListaLlamados(ListaDeTurnos listaDeLlamados) {
		ListaDeTurnos oldValue = this.listaDeLlamados;
		this.listaDeLlamados = listaDeLlamados;
		pcs.firePropertyChange("lista de llamados", oldValue, listaDeLlamados);
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
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, ConexionSocket.MENSAJE_SIN_CONEXION);

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
