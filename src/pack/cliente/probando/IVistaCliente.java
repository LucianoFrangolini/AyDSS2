package pack.cliente.probando;

import java.awt.event.ActionListener;

public interface IVistaCliente {

static final String ENVIAR_MENSAJE = "EnviarMensaje";
	
	void setVisible();
	void setListener(ActionListener l);
	String getIP();
	int getPuerto();
	String getText();
}
