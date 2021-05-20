package main;

import back.controladores.ControladorTotem;
import front.interfaces.IVistaTotem;
import front.vistas.VistaTotem;

public class Totem {

	/**
	 * Método encargado de ejecutar el Totem.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {

		IVistaTotem vista = new VistaTotem();
		ControladorTotem controlador = new ControladorTotem(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
