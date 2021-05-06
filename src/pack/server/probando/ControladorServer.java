package pack.server.probando;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControladorServer implements ActionListener, PropertyChangeListener {

	IVistaServer vista;
	LogicaServer servidor;

	public ControladorServer(IVistaServer vista) {
		this.vista = vista;
		this.servidor = LogicaServer.getInstance();
		this.servidor.addPropertyChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(IVistaServer.ABRIR_PUERTO)) {
			this.servidor.abrirServidor(this.vista.getIP(), this.vista.getPuerto());
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName()=="Mensaje") {
			this.vista.setAreaText(evt.getNewValue().toString());
		}
		/*System.out.println("Variation of " + evt.getPropertyName());
		System.out.println("\t(" + evt.getOldValue() + " -> " + evt.getNewValue() + ")");
		System.out.println("Property in object " + evt.getSource());*/
	}

}
