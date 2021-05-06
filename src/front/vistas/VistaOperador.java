package front.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import front.interfaces.IVistaOperador;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class VistaOperador extends JFrame implements IVistaOperador {

	private static final long serialVersionUID = 6637668548562303904L;
	private JPanel mainPanel;

	public VistaOperador() {
		setTitle("Operador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("[DNI cliente actual]");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblNewLabel);
		
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);
		panelBotones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Eliminar cliente actual");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotones.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Llamar siguiente cliente");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotones.add(btnNewButton_1);
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
