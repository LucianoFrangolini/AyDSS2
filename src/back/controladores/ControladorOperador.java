package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.puestos.Puesto;
import front.interfaces.IVistaOperador;

public class ControladorOperador implements ActionListener{

	IVistaOperador vista;
	
	public ControladorOperador(Puesto puesto, IVistaOperador vista) {
		this.vista = vista;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
