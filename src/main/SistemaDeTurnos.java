package main;

import back.controladores.ControladorServidor;
import front.interfaces.IVistaServidor;
import front.vistas.VistaServidor;

public class SistemaDeTurnos {

	public static void main(String[] args) {
		
		IVistaServidor vista = new VistaServidor();
		ControladorServidor controlador = new ControladorServidor(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
