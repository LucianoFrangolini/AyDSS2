package main;

import back.controladores.ControladorMonitor;
import back.monitor.Monitor;
import front.interfaces.IVistaMonitor;
import front.vistas.VistaMonitorDeSistema;

public class MonitorDeSistema {

	public static void main(String[] args) {
		IVistaMonitor vista = new VistaMonitorDeSistema();
		Monitor monitor = Monitor.getInstance();
		@SuppressWarnings("unused")
		ControladorMonitor controlador = new ControladorMonitor(vista,monitor);
		vista.abrir();
	}
}
