package main;

import back.controladores.ControladorServidor;
import front.interfaces.IVistaAdministradorPuestos;
import front.vistas.VistaAdministradorPuestos;

public class SistemaDeTurnos {

	public static void main(String[] args) {
		
		IVistaAdministradorPuestos vista = new VistaAdministradorPuestos();
		ControladorServidor controlador = new ControladorServidor(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
