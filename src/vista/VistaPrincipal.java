package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setTitle("Gestor de Puestos de Trabajo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones);
		panelBotones.setLayout(new GridLayout(4, 1, 0, 20));
		
		JButton btnAgregarPuesto = new JButton("Agregar Puesto");
		panelBotones.add(btnAgregarPuesto);
		
		JButton btnEliminarPuesto = new JButton("Eliminar Puesto");
		panelBotones.add(btnEliminarPuesto);
		
		JButton btnAbrirPuesto = new JButton("Abrir Puesto");
		panelBotones.add(btnAbrirPuesto);
		
		JScrollPane scrollPaneLista = new JScrollPane();
		contentPane.add(scrollPaneLista);
		
		JList listaPuestos = new JList();
		scrollPaneLista.setViewportView(listaPuestos);
	}

}
