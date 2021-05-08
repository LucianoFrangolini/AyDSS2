package front.vistas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import front.interfaces.IVista;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

public class VistaServidor extends JFrame implements IVista{

	private static final long serialVersionUID = -5161354435027911853L;
	private JPanel contentPane;
	
	public VistaServidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDisplay = new JLabel("SERVIDOR EN LINEA");
		lblDisplay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDisplay);
	}

	@Override
	public void setActionListener(ActionListener c) {
	}

	@Override
	public void abrir() {
		setBounds(800, 800, 279, 131);
		setVisible(true);
	}

}
