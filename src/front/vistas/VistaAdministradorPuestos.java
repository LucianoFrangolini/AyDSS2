package front.vistas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import back.puestos.Puesto;
import front.interfaces.IVistaAdministradorPuestos;
import front.interfaces.IVistaPuesto;

public class VistaAdministradorPuestos extends JFrame implements IVistaAdministradorPuestos {

	private static final long serialVersionUID = -6092145973008650357L;
	private JPanel contentPane;
	private JButton btnAgregarPuesto;
	private JButton btnEliminarPuesto;
	private JButton btnAbrirPuesto;
	private JList<IVistaPuesto> listaPuestos;
	private DefaultListModel<IVistaPuesto> listModelPuestos;

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
		panelLista.setBorder(new TitledBorder(null, "Lista de puestos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

	@Override
	public void setActionListener(ActionListener c) {
		this.btnAbrirPuesto.addActionListener(c);
		this.btnAgregarPuesto.addActionListener(c);
		this.btnEliminarPuesto.addActionListener(c);
	}

	private void setActionCommands() {
		this.btnAbrirPuesto.setActionCommand(IVistaAdministradorPuestos.ABRIR_PUESTO);
		this.btnAgregarPuesto.setActionCommand(IVistaAdministradorPuestos.AGREGAR_PUESTO);
		this.btnEliminarPuesto.setActionCommand(IVistaAdministradorPuestos.ELIMINAR_PUESTO);
	}

	@Override
	public void abrir() {
		setBounds(700, 100, 450, 300);
		setVisible(true);
	}
	
	@Override
	public void actualizarLista(Iterator<IVistaPuesto> it) {
		this.listModelPuestos.clear();
		while (it.hasNext()) {
			this.listModelPuestos.addElement(it.next());
		}		
		this.repaint();
	}

	@Override
	public IVistaPuesto getPuestoSeleccionado() {
		return this.listaPuestos.getSelectedValue();
	}	
	

}
