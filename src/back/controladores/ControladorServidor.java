package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import back.servidor.AdministradorDeTurnos;
import front.interfaces.IVistaAdministradorPuestos;

public class ControladorServidor implements ActionListener{

	private IVistaAdministradorPuestos vista;
	private AdministradorDeTurnos administradorDeTurnos;
	
	public ControladorServidor(IVistaAdministradorPuestos vista) {
		this.vista=vista;
		administradorDeTurnos = AdministradorDeTurnos.getInstance();
		administradorDeTurnos.abrirServidor();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.ABRIR_PUESTO)) {
			//Agregar mas adelante
		}
		else if (e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.AGREGAR_PUESTO)) {
			//Agregar mas adelante
		}
		else if (e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.ELIMINAR_PUESTO)) {
			//Agregar mas adelante
		}
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName()=="Totem") {
		}
	}
	
}
