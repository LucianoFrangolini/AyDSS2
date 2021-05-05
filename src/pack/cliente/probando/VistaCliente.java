package pack.cliente.probando;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;

public class VistaCliente extends JFrame implements IVistaCliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1541396483724109147L;
	private JPanel contentPane;
	private JTextField textPuerto;
	private JTextField textHost;
	private JButton btnEnviar;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public VistaCliente() {
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPuerto);

		textPuerto = new JTextField();
		textPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		textPuerto.setText("9999");
		panel.add(textPuerto);
		textPuerto.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblIP;
		lblIP = new JLabel("Host");
		lblIP.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblIP);

		textHost = new JTextField();
		textHost.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textHost);
		textHost.setColumns(10);
		try {
			textHost.setText(InetAddress.getLocalHost().getCanonicalHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		btnEnviar = new JButton("Enviar Mensaje");
		btnEnviar.setActionCommand(ENVIAR_MENSAJE);
		panel_3.add(btnEnviar);
	}

	@Override
	public void setListener(ActionListener l) {
		this.btnEnviar.addActionListener(l);
	}

	@Override
	public void setVisible() {
		this.setVisible(true);
	}

	@Override
	public String getIP() {
		return this.textHost.getText();
	}

	@Override
	public int getPuerto() {
		return Integer.parseInt(this.textPuerto.getText());
	}

	@Override
	public String getText() {
		return this.textArea.getText();
	}
	
	
}
