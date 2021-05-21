package main;

import back.controladores.ControladorAdministradorPuestos;
import back.controladores.ControladorPuesto;
import front.interfaces.IVistaAdministradorPuestos;
import front.interfaces.IVistaPuesto;
import front.vistas.VistaAdministradorPuestos;
import front.vistas.VistaPuesto;

public class PuestoDeTrabajo {

	/**
	 * Método encargado de ejecutar el Administrador de Puestos.<br>
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {
		IVistaPuesto vista = new VistaPuesto();
		ControladorPuesto controlador = new ControladorPuesto(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}