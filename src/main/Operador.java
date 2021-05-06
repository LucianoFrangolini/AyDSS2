package main;

import back.controladores.ControladorOperador;
import front.interfaces.IVistaOperador;
import front.vistas.VistaOperador;

public class Operador {

	public static void main(String[] args) {
		
		IVistaOperador vista = new VistaOperador();
		ControladorOperador controlador = new ControladorOperador(vista);
		vista.setActionListener(controlador);
		vista.abrir();
	}
}
