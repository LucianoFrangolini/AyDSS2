package pack.cliente.probando;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import pack.server.probando.IVistaServer;
import pack.server.probando.LogicaServer;

public class ControladorCliente implements ActionListener {

	IVistaCliente vista;
	LogicaCliente cliente;
	
	public ControladorCliente(IVistaCliente vista) {
		this.vista = vista;
		this.cliente = LogicaCliente.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(IVistaCliente.ENVIAR_MENSAJE)) {
			this.cliente.enviarMensaje(this.vista.getIP(),this.vista.getPuerto(),this.vista.getText());
		}
	}

}
