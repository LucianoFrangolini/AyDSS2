package pack.server.probando;

public class MainServer{

	public static void main(String[] args) {
		
		IVistaServer vistaServidor = new VistaServer();
		ControladorServer controlador = new ControladorServer(vistaServidor);
		vistaServidor.setListener(controlador);
		vistaServidor.setVisible();
	}
}
