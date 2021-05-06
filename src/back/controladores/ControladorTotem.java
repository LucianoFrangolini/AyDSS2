package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.totem.ControladorDeTotem;
import front.interfaces.IVistaTotem;

public class ControladorTotem implements ActionListener {

	IVistaTotem vista;
	ControladorDeTotem totem;

	public ControladorTotem(IVistaTotem vista) {
		this.vista = vista;
		this.totem = new ControladorDeTotem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.CERO))
			actualizarDisplay(IVistaTotem.CERO);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.UNO))
			actualizarDisplay(IVistaTotem.UNO);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.DOS))
			actualizarDisplay(IVistaTotem.DOS);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.TRES))
			actualizarDisplay(IVistaTotem.TRES);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.CUATRO))
			actualizarDisplay(IVistaTotem.CUATRO);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.CINCO))
			actualizarDisplay(IVistaTotem.CINCO);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.SEIS))
			actualizarDisplay(IVistaTotem.SEIS);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.SIETE))
			actualizarDisplay(IVistaTotem.SIETE);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.OCHO))
			actualizarDisplay(IVistaTotem.OCHO);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.NUEVE))
			actualizarDisplay(IVistaTotem.NUEVE);

		else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.BACKSPACE)) {
			actualizarDisplay(IVistaTotem.BACKSPACE);
			
		} else if (e.getActionCommand().equalsIgnoreCase(IVistaTotem.ENVIAR)) {
			this.totem.enviarMensaje(this.vista.getLabelDisplay());
			this.vista.setLabelDisplay(this.totem.getEstado());
		}
	}

	private void actualizarDisplay(String digito) {
		String aux = this.vista.getLabelDisplay();
		if (digito != IVistaTotem.BACKSPACE) {
			if (aux.length() < 8) {
				aux += digito;
				this.vista.setLabelDisplay(aux);
				if (aux.length() == 8)
					vista.habilitarEnvio();
			}
		}
		else if (aux.length() != 0) {
			this.vista.setLabelDisplay(aux.substring(0, aux.length() - 1));
			if (aux.length() == 8)
				vista.deshabilitarEnvio();
		}
	}
}
