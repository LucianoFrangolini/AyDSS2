package main;

import back.controladores.ControladorAdministradorPuestos;
import front.interfaces.IVistaAdministradorPuestos;
import front.vistas.VistaAdministradorPuestos;

public class AdministradorPuestos {

	public static void main(String[] args) {
		IVistaAdministradorPuestos vista = new VistaAdministradorPuestos();
		ControladorAdministradorPuestos controlador = new ControladorAdministradorPuestos(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
