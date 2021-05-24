package main;

import back.constantes.ListaDeDirecciones;
import back.controladores.ControladorAdministrador;
import back.servidor.Administrador;
import front.interfaces.IVista;
import front.vistas.VistaServidor;

public class SegundoServidor {


	/**
	 * Método encargado de ejecutar el Servidor.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {

		Administrador administrador = new Administrador(ListaDeDirecciones.PUERTO_TOTEM_S2,
				ListaDeDirecciones.PUERTO_PUESTOS_S2, ListaDeDirecciones.PUERTO_S1);
		IVista vista = new VistaServidor("Servidor 2");
		ControladorAdministrador controlador = new ControladorAdministrador(vista,administrador);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
