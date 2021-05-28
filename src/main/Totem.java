package main;

import back.controladores.ControladorTotem;
import front.interfaces.Inscripcion;
import front.vistas.VistaTotem;

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
