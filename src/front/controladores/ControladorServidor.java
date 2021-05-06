package front.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import front.interfaces.IVistaServidor;

public class ControladorServidor implements ActionListener{

	IVistaServidor vista;
	
	public ControladorServidor(IVistaServidor vista) {
		this.vista=vista;
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

	
}
