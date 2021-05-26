package main;

import back.controladores.ControladorMonitor;
import back.monitor.Monitor;
import front.interfaces.IVista;
import front.vistas.VistaMonitorDeSistema;

public class MonitorDeSistema {

	public static void main(String[] args) {
		IVista vista = new VistaMonitorDeSistema();
		Monitor monitor = new Monitor();
		ControladorMonitor controlador = new ControladorMonitor(vista,monitor);
		vista.abrir();
	}
}
