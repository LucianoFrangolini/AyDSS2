package front.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import front.interfaces.IVistaServidor;
import java.awt.event.ActionEvent;

public class VistaServidor extends JFrame implements IVistaServidor {

	private static final long serialVersionUID = -6092145973008650357L;
	private JPanel contentPane;
	private JButton btnAgregarPuesto;
	private JButton btnEliminarPuesto;
	private JButton btnAbrirPuesto;
	private JList listaPuestos;

	public VistaServidor() {
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
		
		listaPuestos = new JList();
		scrollPaneLista.setViewportView(listaPuestos);
		
		setActionCommands();
	}

	@Override
	public void setActionListener(ActionListener c) {
		this.btnAbrirPuesto.addActionListener(c);
		this.btnAgregarPuesto.addActionListener(c);
		this.btnEliminarPuesto.addActionListener(c);
	}

	@Override
	public void setActionCommands() {
		this.btnAbrirPuesto.setActionCommand(IVistaServidor.ABRIR_PUESTO);
		this.btnAgregarPuesto.setActionCommand(IVistaServidor.AGREGAR_PUESTO);
		this.btnEliminarPuesto.setActionCommand(IVistaServidor.ELIMINAR_PUESTO);
	}

	@Override
	public void abrir() {
		setBounds(100, 100, 450, 300);
		setVisible(true);
	}


}