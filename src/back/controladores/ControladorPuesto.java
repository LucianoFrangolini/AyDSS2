package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.constantes.ListaDeAcciones;
import back.puestos.Puesto;
import front.interfaces.IVistaPuesto;

public class ControladorPuesto implements ActionListener {

	private IVistaPuesto vista;
	private Puesto puesto;

	public ControladorPuesto(Puesto puesto, IVistaPuesto vista) {
		this.vista = vista;
		this.puesto = puesto;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(IVistaPuesto.LLAMAR)) {
			this.puesto.enviarMensaje(ListaDeAcciones.LLAMAR);
			if (this.puesto.getClienteActual() != null && this.puesto.getClienteActual().length() == 8) {
				this.vista.setDisplay("Atendiendo cliente: " + this.puesto.getClienteActual());
				this.vista.habilitarBotonEliminar();
			} else if (this.puesto.getClienteActual() != null)
				this.vista.setDisplay(this.puesto.getClienteActual());
			
		} else if (e.getActionCommand().equalsIgnoreCase(IVistaPuesto.ELIMINAR)) {
			this.puesto.enviarMensaje(ListaDeAcciones.ELIMINAR);
			this.vista.setDisplay("-");
			this.vista.deshabilitarBotonEliminar();
		}
	}
}
