package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import back.constantes.ListaDeAcciones;
import back.puestos.AdministradorDePuestos;
import back.puestos.Puesto;
import front.interfaces.IVistaAdministradorPuestos;
import front.interfaces.IVistaPuesto;
import front.vistas.VistaPuesto;

public class ControladorAdministradorPuestos implements ActionListener{

	private IVistaAdministradorPuestos vista;
	private AdministradorDePuestos admin;
	private ArrayList<IVistaPuesto> listaVistasPuestos;
	
	public ControladorAdministradorPuestos(IVistaAdministradorPuestos vista) {
		this.vista = vista;
		this.admin = AdministradorDePuestos.getInstance();
		this.listaVistasPuestos = new ArrayList<IVistaPuesto>();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.AGREGAR_PUESTO)) {
			agregarPuesto();
			this.vista.actualizarLista(this.listaVistasPuestos.iterator());
		}
		else if(e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.ABRIR_PUESTO)) {
			this.vista.getPuestoSeleccionado().abrir();
		}
		else if(e.getActionCommand().equalsIgnoreCase(IVistaAdministradorPuestos.ELIMINAR_PUESTO)) {
			this.eliminarPuesto(this.vista.getPuestoSeleccionado().getNumeroPuesto());
			this.vista.actualizarLista(this.listaVistasPuestos.iterator());
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
		this.listaVistasPuestos.add(vistaPuesto);
	}
	
	private void eliminarPuesto(int numeroPuesto) {
		Puesto puesto = admin.getPuesto(numeroPuesto);
		puesto.enviarMensaje(ListaDeAcciones.ELIMINAR);
		//enviar mensaje de eliminacion de posibles turnos pendientes
		
		eliminarVistaPuesto(numeroPuesto);
		admin.eliminarPuestoTrabajo(numeroPuesto);
	}
	
	private void eliminarVistaPuesto(int numeroPuesto) {
		ArrayList<IVistaPuesto> nuevaLista = new ArrayList<IVistaPuesto>();
		Iterator<IVistaPuesto> it = this.listaVistasPuestos.iterator();
		IVistaPuesto aux, elim = null;
		while(it.hasNext()) {
			aux = it.next();
			if(aux.getNumeroPuesto()!=numeroPuesto)
				nuevaLista.add(aux);
			else
				elim = aux;
		}
		this.listaVistasPuestos = nuevaLista;
		if(elim!=null)
			elim.dispose();
	}



}
