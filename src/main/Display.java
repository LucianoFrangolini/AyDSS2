package main;

import back.controladores.ControladorDisplay;
import front.interfaces.IVistaDisplay;
import front.vistas.VistaDisplay;

public class Display {

	/**
	 * Método encargado de ejecutar el Display.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {

		IVistaDisplay vista = new VistaDisplay();
		ControladorDisplay controlador = new ControladorDisplay(vista);
		vista.setActionListener(controlador);
		vista.abrir();

	}
}
