package back.controladores;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import back.constantes.ListaDeDirecciones;
import back.monitor.Monitor;
import front.interfaces.IVista;

public class ControladorMonitor implements PropertyChangeListener{

	private IVista vista;
	private Monitor monitor;
	
	public ControladorMonitor(IVista vista, Monitor monitor) {
		this.vista = vista;
		this.monitor = monitor;
		this.monitor.getPcs().addPropertyChangeListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if (arg0.getPropertyName().equals("")) {
			
		}
	}
	
}
