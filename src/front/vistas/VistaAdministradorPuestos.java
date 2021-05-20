package front.vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import front.interfaces.IVistaAdministradorPuestos;
import front.interfaces.IVistaPuesto;

/**
 * @author Grupo12.<br>
 *         Clase que representa la vista del Administrador De Puestos.
 *         Implementa IVistaAdministradorPuestos.<br>
 */
public class VistaAdministradorPuestos extends JFrame implements IVistaAdministradorPuestos {

	private static final long serialVersionUID = -6092145973008650357L;
	private JPanel contentPane;
	private JButton btnAgregarPuesto;
	private JButton btnEliminarPuesto;
	private JButton btnAbrirPuesto;
	private JList<IVistaPuesto> listaPuestos;
	private DefaultListModel<IVistaPuesto> listModelPuestos;

	/**
	 * Método constructor de VistaAdministradorPuestos.<br>
	 */
	public VistaAdministradorPuestos() {
		setTitle("Gestor de Puestos de Trabajo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones);
		panelBotones.setLayout(new GridLayout(4, 1, 0, 20));

		this.btnAgregarPuesto = new JButton("Agregar Puesto");
		panelBotones.add(btnAgregarPuesto);

		this.btnEliminarPuesto = new JButton("Eliminar Puesto");
		panelBotones.add(btnEliminarPuesto);

		this.btnAbrirPuesto = new JButton("Abrir Puesto");
		panelBotones.add(btnAbrirPuesto);

		JPanel panelLista = new JPanel();
		panelLista.setBorder(
				new TitledBorder(null, "Lista de puestos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelLista);
		panelLista.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneLista = new JScrollPane();
		panelLista.add(scrollPaneLista);

		listaPuestos = new JList<IVistaPuesto>();
		this.listModelPuestos = new DefaultListModel<IVistaPuesto>();
		listaPuestos.setModel(listModelPuestos);
		scrollPaneLista.setViewportView(listaPuestos);

		setActionCommands();
	}

	/**
	 * Método encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	@Override
	public void setActionListener(ActionListener c) {
		this.btnAbrirPuesto.addActionListener(c);
		this.btnAgregarPuesto.addActionListener(c);
		this.btnEliminarPuesto.addActionListener(c);
	}

	/**
	 * Método encargado de setear los action commands.<br>
	 */
	private void setActionCommands() {
		this.btnAbrirPuesto.setActionCommand(IVistaAdministradorPuestos.ABRIR_PUESTO);
		this.btnAgregarPuesto.setActionCommand(IVistaAdministradorPuestos.AGREGAR_PUESTO);
		this.btnEliminarPuesto.setActionCommand(IVistaAdministradorPuestos.ELIMINAR_PUESTO);
	}

	/**
	 * Método encargado de abrir la vista.<br>
	 * <b> Post: </b> La vista es visible.<br>
	 */
	@Override
	public void abrir() {
		setBounds(700, 100, 450, 300);
		setVisible(true);
	}

	/**
	 * Método encargado de actualizar la lista de puestos.<br>
	 * <b> Post: </b> Se actualiza la lista de puestos de la vista.<br>
	 * 
	 * @param it de tipo Iterator<IVistaPuesto>: Representa el iterador de la lista
	 *           de puestos.<br>
	 */
	@Override
	public void actualizarLista(Iterator<IVistaPuesto> it) {
		this.listModelPuestos.clear();
		while (it.hasNext()) {
			this.listModelPuestos.addElement(it.next());
		}
		this.repaint();
	}

	/**
	 * Método encargado de obtener la vista del puesto seleccionado de una
	 * lista.<br>
	 * 
	 * @return la vista del puesto seleccionado.<br>
	 */
	@Override
	public IVistaPuesto getPuestoSeleccionado() {
		return this.listaPuestos.getSelectedValue();
	}

}
