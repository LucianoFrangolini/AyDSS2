package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import back.pantalla.Display;
import back.servidor.ListaDeTurnos;
import front.interfaces.IVistaDisplay;

public class ControladorDisplay implements ActionListener, PropertyChangeListener {

	private IVistaDisplay vista;
	private Display display;

	public ControladorDisplay(IVistaDisplay vista) {
		this.vista = vista;
		this.display = new Display();
		this.display.getPcs().addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		ListaDeTurnos listaDeLlamados = (ListaDeTurnos) arg0.getNewValue();
		this.vista.actualizarLista(listaDeLlamados.getItTurnos());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
