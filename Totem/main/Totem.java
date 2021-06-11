package main;

import back.totem.controlador.ControladorTotem;
import front.totem.interfaces.Inscripcion;
import front.totem.vista.VistaTotem;

public class Totem {

	/**
	 * Método encargado de ejecutar el Totem.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {

		Inscripcion vista = new VistaTotem();
		ControladorTotem controlador = new ControladorTotem(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
