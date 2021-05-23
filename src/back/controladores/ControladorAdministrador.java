package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.constantes.ListaDeAcciones;
import back.servidor.Administrador;
import front.interfaces.IVistaServidor;

public class ControladorAdministrador implements ActionListener {

	private IVistaServidor vista;
	private Administrador admin;

	public ControladorAdministrador(IVistaServidor vista, Administrador admin) {
		this.vista = vista;
		this.admin = admin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		vista.cambiarEstado();
		if (e.getActionCommand().equalsIgnoreCase(IVistaServidor.ABRIR_SERVIDOR)) {
			this.admin.abrirServidor();
		} else if (e.getActionCommand().equalsIgnoreCase(IVistaServidor.CERRAR_SERVIDOR)) {
			this.admin.cerrarServidor();
		}
	}

}
