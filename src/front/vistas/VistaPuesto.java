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

/**
 * @author Grupo12.<br>
 *         Clase que representa la vista de un puesto de trabajo. Implementa
 *         IVistaPuesto.<br>
 */
public class VistaPuesto extends JFrame implements IVistaPuesto {

	private static final long serialVersionUID = 6637668548562303904L;
	private JPanel mainPanel;
	private int numeroPuesto;
	private JLabel lblDisplay;
	private JButton btnEliminar;
	private JButton btnLlamar;

	/**
	 * Método constructor de VistaPuesto.<br>
	 * 
	 * @param numeroPuesto de tipo Int: Representa el número del puesto.<br>
	 */
	public VistaPuesto(int numeroPuesto) {
		setTitle("Puesto " + numeroPuesto);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new GridLayout(2, 0, 0, 0));

		lblDisplay = new JLabel("-");
		lblDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblDisplay);

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);
		panelBotones.setLayout(new GridLayout(0, 2, 0, 0));

		btnEliminar = new JButton("Eliminar cliente atendido");
		btnEliminar.setEnabled(false);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotones.add(btnEliminar);

		btnLlamar = new JButton("Llamar siguiente cliente");
		btnLlamar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotones.add(btnLlamar);

		this.numeroPuesto = numeroPuesto;

		setActionCommands();
	}

	/**
	 * Método encargado de devolver el número del puesto<br>
	 * 
	 * @return número del puesto.<br>
	 */
	@Override
	public int getNumeroPuesto() {
		return this.numeroPuesto;
	}

	/**
	 * Método encargado de setear los action commands.<br>
	 */
	private void setActionCommands() {
		this.btnEliminar.setActionCommand(IVistaPuesto.ELIMINAR);
		this.btnLlamar.setActionCommand(IVistaPuesto.LLAMAR);
	}

	/**
	 * Método encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	@Override
	public void setActionListener(ActionListener c) {
		this.btnLlamar.addActionListener(c);
		this.btnEliminar.addActionListener(c);
	}

	/**
	 * Método encargado de abrir la vista.<br>
	 * <b> Post: </b> La vista es visible.<br>
	 */
	@Override
	public void abrir() {
		setBounds(1200, 100, 450, 300);
		setVisible(true);
	}

	@Override
	public String toString() {
		return "Puesto : " + this.numeroPuesto;
	}

	/**
	 * Método encargado de mostrar el cliente al que el puesto se encuentra
	 * atendiendo en el label correspondiente.<br>
	 * 
	 * <b> Post: </b> Se muestra el DNI del cliente en el label correspondiente.<br>
	 * 
	 * @param clienteActual de tipo String: Representa el DNI del cliente al cual se
	 *                      atiende.<br>
	 */
	@Override
	public void setDisplay(String clienteActual) {
		this.lblDisplay.setText(clienteActual);
	}

	/**
	 * Método encargado de eliminar la vista del puesto<br>
	 */
	@Override
	public void dispose() {
		super.dispose();
	}

	/**
	 * Método encargado de habilitar el botón para eliminar un cliente del
	 * puesto.<br>
	 */
	@Override
	public void habilitarBotonEliminar() {
		this.btnEliminar.setEnabled(true);
	}

	/**
	 * Método encargado de deshabilitar el botón para eliminar un cliente del
	 * puesto<br>
	 */
	@Override
	public void deshabilitarBotonEliminar() {
		this.btnEliminar.setEnabled(false);
	}
}
