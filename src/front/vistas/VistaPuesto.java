package front.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import front.interfaces.IVistaPuesto;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class VistaPuesto extends JFrame implements IVistaPuesto {

	private static final long serialVersionUID = 6637668548562303904L;
	private JPanel mainPanel;
	private int numeroPuesto;
	private JLabel lblDisplay;
	private JButton btnEliminar;
	private JButton btnLlamar;

	public VistaPuesto(int numeroPuesto) {
		setTitle("Puesto "+numeroPuesto);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblDisplay = new JLabel("[DNI cliente actual]");
		lblDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblDisplay);
		
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);
		panelBotones.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnEliminar = new JButton("Eliminar cliente actual");
		btnEliminar.setEnabled(false);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotones.add(btnEliminar);
		
		btnLlamar = new JButton("Llamar siguiente cliente");
		btnLlamar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotones.add(btnLlamar);
		
		this.numeroPuesto = numeroPuesto;
		
		setActionCommands();
	}
	
	public int getNumeroPuesto() {
		return this.numeroPuesto;
	}

	private void setActionCommands() {
		this.btnEliminar.setActionCommand(IVistaPuesto.ELIMINAR);
		this.btnLlamar.setActionCommand(IVistaPuesto.LLAMAR);
	}
	
	@Override
	public void setActionListener(ActionListener c) {
		this.btnLlamar.addActionListener(c);
		this.btnEliminar.addActionListener(c);
	}

	@Override
	public void abrir() {
		setBounds(100, 100, 450, 300);
		setVisible(true);
	}
	
	@Override
	public String toString() {
		return "Puesto : " + this.numeroPuesto;
	}

	@Override
	public void setDisplay(String clienteActual) {
		this.lblDisplay.setText(clienteActual);
	}
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void habilitarBotonEliminar() {
		this.btnEliminar.setEnabled(true);
	}

	@Override
	public void deshabilitarBotonEliminar() {
		this.btnEliminar.setEnabled(false);
	}
}
