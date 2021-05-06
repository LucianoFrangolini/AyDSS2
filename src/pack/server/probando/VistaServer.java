package pack.server.probando;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class VistaServer extends JFrame implements IVistaServer {

	private JPanel contentPane;
	private JTextField textIP;
	private JTextField textPuerto;
	private JButton btnAbrir;
	private JTextArea textArea;
	

	/**
	 * Create the frame.
	 */
	public VistaServer() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblIP = new JLabel("IP del servidor:");
		lblIP.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblIP);
		
		textIP = new JTextField();
		try {
			textIP.setText(InetAddress.getLocalHost().getCanonicalHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("No se pudo obtener la direccion IP local");
		}
		textIP.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textIP);
		textIP.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto que se abre:");
		lblPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPuerto);
		
		textPuerto = new JTextField();
		textPuerto.setText("9999");
		textPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textPuerto);
		textPuerto.setColumns(10);
		
		btnAbrir = new JButton("Abrir puerto");
		contentPane.add(btnAbrir);
		btnAbrir.setActionCommand(ABRIR_PUERTO);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	
	}

	@Override
	public void setVisible() {
		setBounds(100, 100, 726, 478);
		setVisible(true);
	}
	
	@Override
	public void setListener(ActionListener l) {
		this.btnAbrir.addActionListener(l);
	}

	@Override
	public String getIP() {
		return this.textIP.getText();
	}

	@Override
	public int getPuerto() {
		return Integer.parseInt(this.textPuerto.getText());
	}

	@Override
	public void setAreaText(String texto) {
		this.textArea.setText(texto+"\n");
	}

}
