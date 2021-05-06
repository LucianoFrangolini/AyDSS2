package main;

import back.controladores.ControladorTotem;
import front.interfaces.IVistaTotem;
import front.vistas.VistaTotem;

public class Totem {

	public static void main(String[] args) {
		
		IVistaTotem vista = new VistaTotem();
		ControladorTotem controlador = new ControladorTotem(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
