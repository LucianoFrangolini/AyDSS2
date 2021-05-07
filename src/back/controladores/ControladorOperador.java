package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import back.puestos.Puesto;
import front.interfaces.IVistaPuesto;

public class ControladorOperador implements ActionListener{

	private IVistaPuesto vista;
	
	public ControladorOperador(Puesto puesto, IVistaPuesto vista) {
		this.vista = vista;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
