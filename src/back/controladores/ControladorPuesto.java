package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.constantes.ListaDeAcciones;
import back.puestos.Puesto;
import front.interfaces.IVistaPuesto;

/**
 * @author Grupo12 <br>
 *         Controlador para un puesto, implementa ActionListener. <br>
 */
public class ControladorPuesto implements ActionListener {

	private IVistaPuesto vista;
	private Puesto puesto;

	/**
	 * Constructor para el controlador de un puesto.<br>
	 * 
	 * @param puesto de tipo Puesto: es el puesto que se le otorga al
	 *               controlador.<br>
	 * @param vista  de tipo IVistaPuesto: es la vista del puesto que se le muestra
	 *               al operador.<br>
	 */
	public ControladorPuesto(Puesto puesto, IVistaPuesto vista) {
		this.vista = vista;
		this.puesto = puesto;
	}

	/**
	 * Método encargado de realizar acciones correspondientes una vez que la vista
	 * dispara un evento <br>
	 * 
	 * @param e de tipo ActionEvent: es el evento disparado por la vista.<br>
	 */
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
