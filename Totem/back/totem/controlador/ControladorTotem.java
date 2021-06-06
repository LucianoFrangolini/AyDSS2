package back.totem.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.totem.ControladorDeTotem;
import back.totem.interfaces.ActualizacionTotem;
import front.totem.interfaces.Inscripcion;

/**
 * @author Grupo12 <br>
 *         Controlador para el Totem, implementa ActionListener. <br>
 */
public class ControladorTotem implements ActionListener, ActualizacionTotem {

	private Inscripcion vista;
	private ControladorDeTotem totem;

	/**
	 * Constructor para el controlador de un puesto.<br>
	 * 
	 * @param vista de tipo IVistaTotem: es la vista que se le presenta a los
	 *              usuarios que solicitan un turno en el sistema.<br>
	 */
	public ControladorTotem(Inscripcion vista) {
		this.vista = vista;
		this.totem = new ControladorDeTotem();
	}

	/**
	 * Método encargado de realizar acciones correspondientes una vez que la vista
	 * dispara un evento <br>
	 * 
	 * @param e de tipo ActionEvent: es el evento disparado por la vista.<br>
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.vista.getLabelDisplay().length() > 8)
			this.vista.setLabelDisplay("");

		if (e.getActionCommand().equalsIgnoreCase(Inscripcion.CERO))
			actualizarDisplay(Inscripcion.CERO);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.UNO))
			actualizarDisplay(Inscripcion.UNO);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.DOS))
			actualizarDisplay(Inscripcion.DOS);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.TRES))
			actualizarDisplay(Inscripcion.TRES);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.CUATRO))
			actualizarDisplay(Inscripcion.CUATRO);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.CINCO))
			actualizarDisplay(Inscripcion.CINCO);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.SEIS))
			actualizarDisplay(Inscripcion.SEIS);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.SIETE))
			actualizarDisplay(Inscripcion.SIETE);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.OCHO))
			actualizarDisplay(Inscripcion.OCHO);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.NUEVE))
			actualizarDisplay(Inscripcion.NUEVE);

		else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.BACKSPACE)) {
			actualizarDisplay(Inscripcion.BACKSPACE);

		} else if (e.getActionCommand().equalsIgnoreCase(Inscripcion.ENVIAR)) {
			this.vista.deshabilitarEnvio();
			this.totem.enviarMensaje(this.vista.getLabelDisplay());
			if (this.totem.getEstado()!=null)
				this.vista.setLabelDisplay(this.totem.getEstado());
			else
				this.vista.setLabelDisplay("");
		}
	}

	/**
	 * Método encargado de actualizar el display del totem.<br>
	 * <b> Post: </b> Se actualiza el display según que boton se haya tocado,
	 * tambien habilita o deshabilita el boton de envio según corresponda.<br>
	 * 
	 * @param digito de tipo String: Representa el dígito a añadir en el display del tótem.
	 */
	@Override
	public void actualizarDisplay(String digito) {
		String aux = this.vista.getLabelDisplay();
		if (digito != Inscripcion.BACKSPACE) {
			if (aux.length() < 8) {
				aux += digito;
				this.vista.setLabelDisplay(aux);
				if (aux.length() == 8)
					vista.habilitarEnvio();
			}
		} else if (aux.length() != 0) {
			this.vista.setLabelDisplay(aux.substring(0, aux.length() - 1));
			if (aux.length() == 8)
				vista.deshabilitarEnvio();
		}
	}
}
