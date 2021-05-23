package front.vistas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import front.interfaces.IVista;
import front.interfaces.IVistaServidor;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;

/**
 * @author Grupo12.<br>
 *         Clase que representa la vista del Servidor. Implementa IVista.<br>
 */
public class VistaServidor extends JFrame implements IVistaServidor {

	private static final long serialVersionUID = -5161354435027911853L;
	private JPanel contentPane;
	private JButton btnAbrirServidor;
	private JButton btnCerrarServidor;
	private JLabel lblDisplay;

	/**
	 * Método constructor de VistaServidor.<br>
	 */
	public VistaServidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));

		lblDisplay = new JLabel("SERVIDOR EN LINEA");
		lblDisplay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDisplay);
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones);
		panelBotones.setLayout(new GridLayout(2, 2, 0, 0));
		
		btnAbrirServidor = new JButton("Abrir Servidor");
		btnAbrirServidor.setEnabled(false);
		panelBotones.add(btnAbrirServidor);
		
		btnCerrarServidor = new JButton("Cerrar Servidor");
		panelBotones.add(btnCerrarServidor);
		
		setActionCommands();
	}

	/**
	 * Método encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	@Override
	public void setActionListener(ActionListener c) {
		this.btnAbrirServidor.addActionListener(c);
		this.btnCerrarServidor.addActionListener(c);
	}

	/**
	 * Método encargado de abrir la vista.<br>
	 * <b> Post: </b> La vista es visible.<br>
	 */
	@Override
	public void abrir() {
		setBounds(800, 500, 279, 131);
		setVisible(true);
	}

	@Override
	public void setActionCommands() {
		this.btnAbrirServidor.setActionCommand(IVistaServidor.ABRIR_SERVIDOR);
		this.btnCerrarServidor.setActionCommand(IVistaServidor.CERRAR_SERVIDOR);
	}
	
	public void cambiarEstado() {
		if (this.btnAbrirServidor.isEnabled()) {
			this.btnAbrirServidor.setEnabled(false);
			this.btnCerrarServidor.setEnabled(true);
			this.lblDisplay.setText("SERVIDOR EN LINEA");
		}else {
			this.btnAbrirServidor.setEnabled(true);
			this.btnCerrarServidor.setEnabled(false);
			this.lblDisplay.setText("SERVIDOR DESCONECTADO");
		}
	}

}
