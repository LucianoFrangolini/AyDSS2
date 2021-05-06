package front.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import front.interfaces.IVistaOperador;

public class ControladorOperador implements ActionListener{

	IVistaOperador vista;
	
	
	public ControladorOperador(IVistaOperador vista) {
		this.vista = vista;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
