package main;

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
		
		Administrador administrador = new Administrador("Servidor2");
		IVista vista = new VistaServidor("Servidor 2");
		administrador.abrirServidor();
		vista.abrir();
		
	}
}
