package main;

import back.constantes.ListaDeDirecciones;
import back.controladores.ControladorAdministrador;
import back.servidor.Administrador;
import front.interfaces.IVista;
import front.vistas.VistaServidor;

public class PrimerServidor {

	/**
	 * Método encargado de ejecutar el Servidor.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {

		Administrador administrador = new Administrador("Servidor1",ListaDeDirecciones.PUERTO_TOTEM,
				ListaDeDirecciones.PUERTO_PUESTOS, ListaDeDirecciones.PUERTO_S2,ListaDeDirecciones.PUERTO_S1);
		IVista vista = new VistaServidor("Servidor 1");
		ControladorAdministrador controlador = new ControladorAdministrador(vista,administrador);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
