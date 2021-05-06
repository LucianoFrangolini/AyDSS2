package main;

import front.controladores.ControladorDisplay;
import front.interfaces.IVistaDisplay;
import front.vistas.VistaDisplay;

public class Display {

	public static void main(String[] args) {
		
		IVistaDisplay vista = new VistaDisplay();
		ControladorDisplay controlador = new ControladorDisplay(vista);
		vista.setActionListener(controlador);
		vista.abrir();
		
	}
}
