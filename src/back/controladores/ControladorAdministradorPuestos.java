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

/**
 * @author Grupo12
 * <br>
 * Controlador para el Administrador de puestos.
 * <br>
 */
public class ControladorAdministradorPuestos implements ActionListener {

	private IVistaAdministradorPuestos vista;
	private AdministradorDePuestos admin;
	private ArrayList<IVistaPuesto> listaVistasPuestos;
	
	/**
	 * Constructor para el turno<br>
	 * @param vista de tipo IVistaAdministradorPuestos: es la vista que se le muestra al usuario que administra los puestos.<br>
	 */
	public ControladorAdministradorPuestos(IVistaAdministradorPuestos vista) {
		this.vista = vista;
		this.admin = AdministradorDePuestos.getInstance();
		this.listaVistasPuestos = new ArrayList<IVistaPuesto>();
	}

	/**
	 * Método encargado de realizar acciones correspondientes una vez que la vista dispara un evento <br>
	 * @param e de tipo ActionEvent: es el evento disparado por la vista.<br>
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equalsIgnoreCase(IVistaAdministradorPuestos.AGREGAR_PUESTO)) {
			agregarPuesto();
			this.vista.actualizarLista(this.listaVistasPuestos.iterator());
			
		} else {
			IVistaPuesto puestoSeleccionado = this.vista.getPuestoSeleccionado();
			if (actionCommand.equalsIgnoreCase(IVistaAdministradorPuestos.ABRIR_PUESTO)) {
				if (puestoSeleccionado != null)
					puestoSeleccionado.abrir();
				
			} else if (actionCommand.equalsIgnoreCase(IVistaAdministradorPuestos.ELIMINAR_PUESTO)) {
				if (puestoSeleccionado != null) {
					this.eliminarPuesto(puestoSeleccionado.getNumeroPuesto());
					this.vista.actualizarLista(this.listaVistasPuestos.iterator());
				}
			}
		}
	}
	
	/**
	 * Método encargado de agregar un puesto.<br>
	 * <b> Post: </b> Se crea un puesto, su vista y su controlador, y se lo añade a la lista de vistas.<br>
	 */
	private void agregarPuesto() {
		Puesto puesto = admin.abrirPuestoTrabajo();
		IVistaPuesto vistaPuesto = new VistaPuesto(puesto.getNumeroPuesto());
		ControladorPuesto controlador = new ControladorPuesto(puesto, vistaPuesto);
		vistaPuesto.setActionListener(controlador);
		this.listaVistasPuestos.add(vistaPuesto);
	}
	
	/**
	 * Método encargado de eliminar un puesto.<br>
	 * <b> Post: </b> Se elimina el puesto y cualquiera de sus apariciones ya sea en el display o en el administrador de puestos.<br>
	 */
	private void eliminarPuesto(int numeroPuesto) {
		Puesto puesto = admin.getPuesto(numeroPuesto);
		puesto.enviarMensaje(ListaDeAcciones.ELIMINAR);
		eliminarVistaPuesto(numeroPuesto);
		admin.eliminarPuestoTrabajo(numeroPuesto);
	}
	
	/**
	 * Método encargado de eliminar la vista un puesto.<br>
	 * <b> Post: </b> Se elimina la vista del puesto determinado si es que este se encontraba en la lista de puestos del Controlador de Administrador de Puestos.<br>
	 */
	private void eliminarVistaPuesto(int numeroPuesto) {
		ArrayList<IVistaPuesto> nuevaLista = new ArrayList<IVistaPuesto>();
		Iterator<IVistaPuesto> it = this.listaVistasPuestos.iterator();
		IVistaPuesto aux, elim = null;
		while (it.hasNext()) {
			aux = it.next();
			if (aux.getNumeroPuesto() != numeroPuesto)
				nuevaLista.add(aux);
			else
				elim = aux;
		}
		this.listaVistasPuestos = nuevaLista;
		if (elim != null)
			elim.dispose();
	}

}
