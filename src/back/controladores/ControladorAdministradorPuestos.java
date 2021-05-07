package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import back.puestos.AdministradorDePuestos;
import back.puestos.Puesto;
import front.interfaces.IVistaAdministradorPuestos;
import front.interfaces.IVistaPuesto;
import front.vistas.VistaPuesto;

public class ControladorAdministradorPuestos implements ActionListener{

	private IVistaAdministradorPuestos vista;
	private AdministradorDePuestos admin;
	private ArrayList<IVistaPuesto> listaPuestos;
	
	public ControladorAdministradorPuestos(IVistaAdministradorPuestos vista) {
		this.vista = vista;
		this.admin = AdministradorDePuestos.getInstance();
		this.listaPuestos = new ArrayList<IVistaPuesto>();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.AGREGAR_PUESTO)) {
			agregarPuesto();
			this.vista.actualizarLista(this.listaPuestos.iterator());
		}
		else if(e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.ABRIR_PUESTO)) {
			this.vista.getPuestoSeleccionado().abrir();
		}
		else if(e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.ELIMINAR_PUESTO)) {
			//Me da el nombre del puesto de la lista
			//Se lo envío al adm que le diga a la listaDePuestos uqe lo elimine
			//Le pido la nueva lista
			//Actualizo la lista
		}
	}
	
	private void agregarPuesto() {
		Puesto puesto = admin.abrirPuestoTrabajo();
		IVistaPuesto vistaPuesto = new VistaPuesto(puesto.getNumeroPuesto());
		ControladorPuesto controlador = new ControladorPuesto(puesto,vistaPuesto);
		vistaPuesto.setActionListener(controlador);
		this.listaPuestos.add(vistaPuesto);
	}
	



}
