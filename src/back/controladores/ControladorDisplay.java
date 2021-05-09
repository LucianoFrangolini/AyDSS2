package back.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import back.pantalla.Display;
import back.servidor.ListaDeTurnos;
import front.interfaces.IVistaDisplay;

/**
 * @author Grupo12 <br>
 *         Controlador para el Display, implementa ActionListener y
 *         PropertyChangeListener. <br>
 */
public class ControladorDisplay implements ActionListener, PropertyChangeListener {

	private IVistaDisplay vista;
	private Display display;

	/**
	 * Constructor para el controlador del display.<br>
	 * 
	 * @param vista de tipo IVistaDisplay es la vista del display.<br>
	 */
	public ControladorDisplay(IVistaDisplay vista) {
		this.vista = vista;
		this.display = new Display();
		this.display.getPcs().addPropertyChangeListener(this);
	}

	/**
	 * Método encargado de disparar una accion cuando la propiedad observada sufrió
	 * cambios.<br>
	 * 
	 * @param arg0 de tipo PropertyChangeEvent: es el objeto que contiene atributos
	 *             sobre el cambio en el objeto observado.<br>
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		ListaDeTurnos listaDeLlamados = (ListaDeTurnos) arg0.getNewValue();
		this.vista.actualizarLista(listaDeLlamados.getItTurnos());
	}

	/**
	 * Método encargado de realizar acciones correspondientes una vez que la vista
	 * dispara un evento <br>
	 * 
	 * @param e de tipo ActionEvent: es el evento disparado por la vista.<br>
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
