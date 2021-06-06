package main;

import back.display.controlador.ControladorDisplay;
import front.display.interfaces.IVistaDisplay;
import front.display.vistas.VistaDisplay;

public class Display {

	/**
	 * Método encargado de ejecutar el Display.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {
		
		IVistaDisplay vista = new VistaDisplay();
		@SuppressWarnings("unused")
		ControladorDisplay controlador = new ControladorDisplay(vista);
		vista.abrir();

	}
}
