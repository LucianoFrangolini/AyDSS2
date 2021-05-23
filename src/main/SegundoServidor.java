package main;

import back.constantes.ListaDeDirecciones;
import back.controladores.ControladorAdministrador;
import back.servidor.Administrador;
import front.interfaces.IVistaServidor;
import front.vistas.VistaServidor;

public class SegundoServidor {


	/**
	 * Método encargado de ejecutar el Servidor.<br>
	 * 
	 * @param args de tipo String[]: No utilizado.
	 */
	public static void main(String[] args) {

		Administrador administrador = new Administrador(ListaDeDirecciones.PUERTO_TOTEM,
				ListaDeDirecciones.PUERTO_PUESTOS, ListaDeDirecciones.PUERTO_S2);
		IVistaServidor vista = new VistaServidor();
		ControladorAdministrador controlador = new ControladorAdministrador(vista,administrador);
		vista.setActionListener(controlador);
		vista.abrir();
		administrador.abrirServidor();
	}
}
