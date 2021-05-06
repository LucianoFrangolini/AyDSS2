package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import back.servidor.AdministradorDeTurnos;
import front.interfaces.IVistaServidor;

public class ControladorServidor implements ActionListener{

	IVistaServidor vista;
	AdministradorDeTurnos administradorDeTurnos;
	
	public ControladorServidor(IVistaServidor vista) {
		this.vista=vista;
		administradorDeTurnos = AdministradorDeTurnos.getInstance();
		administradorDeTurnos.abrirServidor();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(IVistaServidor.ABRIR_PUESTO)) {
			//Agregar mas adelante
		}
		else if (e.getActionCommand().equalsIgnoreCase(IVistaServidor.AGREGAR_PUESTO)) {
			//Agregar mas adelante
		}
		else if (e.getActionCommand().equalsIgnoreCase(IVistaServidor.ELIMINAR_PUESTO)) {
			//Agregar mas adelante
		}
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName()=="Totem") {
		}
	}
	
}
