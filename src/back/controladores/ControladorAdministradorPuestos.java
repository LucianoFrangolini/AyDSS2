package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.puestos.AdministradorDePuestos;
import back.puestos.Puesto;
import front.interfaces.IVistaAdministradorPuestos;
import front.interfaces.IVistaOperador;
import front.vistas.VistaOperador;

public class ControladorAdministradorPuestos implements ActionListener{

	IVistaAdministradorPuestos vista;
	AdministradorDePuestos admin;
	
	public ControladorAdministradorPuestos(IVistaAdministradorPuestos vista) {
		this.vista = vista;
		this.admin = AdministradorDePuestos.getInstance();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//if(tocar el boton de agregar)
		Puesto puesto = admin.abrirPuestoTrabajo();
		IVistaOperador vistaOperador = new VistaOperador();
		ControladorOperador controlador = new ControladorOperador(puesto,vistaOperador);
		
	}
	



}
