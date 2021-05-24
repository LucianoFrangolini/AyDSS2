package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.servidor.Administrador;
import front.interfaces.IVista;

public class ControladorAdministrador implements ActionListener {

	private IVista vista;
	private Administrador admin;

	public ControladorAdministrador(IVista vista, Administrador admin) {
		this.vista = vista;
		this.admin = admin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
