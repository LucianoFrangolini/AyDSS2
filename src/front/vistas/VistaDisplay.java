package front.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import front.interfaces.IVistaDisplay;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class VistaDisplay extends JFrame implements IVistaDisplay {

	private static final long serialVersionUID = -638127726284784506L;
	private JPanel contentPane;

	public VistaDisplay() {
		setTitle("Display");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lbl_Instruccion = new JLabel("Dirigirse al puesto indicado");
		panel_1.add(lbl_Instruccion);
		lbl_Instruccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_Instruccion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lbl_Dni = new JLabel("DNI");
		lbl_Dni.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbl_Dni);
		
		JLabel lbl_Puesto = new JLabel("Puesto");
		lbl_Puesto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbl_Puesto);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
	}

	@Override
	public void setActionListener(ActionListener c) {
	}

	@Override
	public void abrir() {
		setBounds(100, 100, 450, 300);
		setVisible(true);
	}

}
