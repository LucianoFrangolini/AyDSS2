package back.controladores;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import back.constantes.ListaDeDirecciones;
import back.monitor.Monitor;
import front.interfaces.IVista;
import front.interfaces.IVistaMonitor;

public class ControladorMonitor implements PropertyChangeListener{

	private IVistaMonitor vista;
	private Monitor monitor;
	
	public ControladorMonitor(IVistaMonitor vista, Monitor monitor) {
		this.vista = vista;
		this.monitor = monitor;
		this.monitor.getPcs().addPropertyChangeListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		System.out.println(arg0.getPropertyName());
		if (arg0.getPropertyName().equalsIgnoreCase("TotemEnLinea")) {
			this.vista.setLabelTotem("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("TotemDesconectado")) {
			this.vista.setLabelTotem("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Servidor1EnLinea")) {
			this.vista.setLabelServidor1("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Servidor1Desconectado")) {
			this.vista.setLabelServidor1("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Servidor2EnLinea")) {
			this.vista.setLabelServidor2("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Servidor2Desconectado")) {
			this.vista.setLabelServidor2("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("DisplayEnLinea")) {
			this.vista.setLabelDisplay("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("DisplayDesconectado")) {
			this.vista.setLabelDisplay("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto1EnLinea")) {
			this.vista.setLabelPuesto1("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto1Desconectado")) {
			this.vista.setLabelPuesto1("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto2EnLinea")) {
			this.vista.setLabelPuesto2("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto2Desconectado")) {
			this.vista.setLabelPuesto2("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto3EnLinea")) {
			this.vista.setLabelPuesto3("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto3Desconectado")) {
			this.vista.setLabelPuesto3("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto4EnLinea")) {
			this.vista.setLabelPuesto4("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto4Desconectado")) {
			this.vista.setLabelPuesto4("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto5EnLinea")) {
			this.vista.setLabelPuesto5("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto5Desconectado")) {
			this.vista.setLabelPuesto5("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto6EnLinea")) {
			this.vista.setLabelPuesto6("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto6Desconectado")) {
			this.vista.setLabelPuesto6("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto7EnLinea")) {
			this.vista.setLabelPuesto7("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto7Desconectado")) {
			this.vista.setLabelPuesto7("DESCONECTADO");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto8EnLinea")) {
			this.vista.setLabelPuesto8("EN LINEA");
		}
		else if(arg0.getPropertyName().equalsIgnoreCase("Puesto8Desconectado")) {
			this.vista.setLabelPuesto8("DESCONECTADO");
		}
	}
	
}
