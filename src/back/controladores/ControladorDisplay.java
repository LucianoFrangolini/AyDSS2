package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import front.interfaces.IVistaDisplay;

public class ControladorDisplay implements ActionListener{
	
	private IVistaDisplay vista;

	public ControladorDisplay(IVistaDisplay vista) {
		this.vista = vista;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
