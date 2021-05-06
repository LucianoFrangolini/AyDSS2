package front.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import front.interfaces.IVistaTotem;

public class ControladorTotem implements ActionListener {

	IVistaTotem vista;

	public ControladorTotem(IVistaTotem vista) {
		this.vista = vista;
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
			// Rellenar cuando conectemos back y front
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
