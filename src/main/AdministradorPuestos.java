package main;

import back.controladores.ControladorAdministradorPuestos;
import front.interfaces.IVistaAdministradorPuestos;
import front.vistas.VistaAdministradorPuestos;

public class AdministradorPuestos {

	/**
	 * Método encargado de ejecutar el Administrador de Puestos.<br>
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {
		IVistaAdministradorPuestos vista = new VistaAdministradorPuestos();
		ControladorAdministradorPuestos controlador = new ControladorAdministradorPuestos(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
