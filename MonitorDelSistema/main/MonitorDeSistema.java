package main;

import back.monitor.Monitor;
import back.monitor.controlador.ControladorMonitor;
import front.monitor.interfaces.IVistaMonitor;
import front.monitor.vistas.VistaMonitorDeSistema;

public class MonitorDeSistema {

	public static void main(String[] args) {
		IVistaMonitor vista = new VistaMonitorDeSistema();
		Monitor monitor = Monitor.getInstance();
		@SuppressWarnings("unused")
		ControladorMonitor controlador = new ControladorMonitor(vista,monitor);
		vista.abrir();
	}
}
