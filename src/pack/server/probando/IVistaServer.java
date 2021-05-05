package pack.server.probando;

import java.awt.event.ActionListener;

public interface IVistaServer {

	static final String ABRIR_PUERTO = "AbrirPuerto";
	
	void setVisible();
	void setListener(ActionListener l);
	String getIP();
	int getPuerto();
	void setAreaText(String texto);
	
}
