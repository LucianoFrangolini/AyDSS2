package back.interfaces.conexiones;

public interface I_ConexionSocket {
	public void enviarMensaje(String host, int puerto, String texto);
}
