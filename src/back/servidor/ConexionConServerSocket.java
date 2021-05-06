package back.servidor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.ServerSocket;

public abstract class ConexionConServerSocket {
	protected static ServerSocket myServerSocket;
	protected PropertyChangeSupport pcs;
	protected String msj="";
	
	public abstract void setMsj(String nuevoMensaje);
	public abstract void addPropertyChangeListener(PropertyChangeListener listener);
	public abstract void abrirServidor(String ip, int puerto);
}
