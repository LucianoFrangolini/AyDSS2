package back.controladores;

import back.monitor.Monitor;
import front.interfaces.IVista;

public class ControladorMonitor {

	private IVista vista;
	private Monitor monitor;
	
	public ControladorMonitor(IVista vista, Monitor monitor) {
		this.vista = vista;
		this.monitor = monitor;
	}
	
}
