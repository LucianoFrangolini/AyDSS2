package front.vistas;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import front.interfaces.IVistaPuesto;

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
	private PropertyChangeSupport pcs;

	/**
	 * M�todo constructor de VistaPuesto.<br>
	 * 
	 * @param numeroPuesto de tipo Int: Representa el n�mero del puesto.<br>
	 */
	public VistaPuesto() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

		setActionCommands();
		
		this.pcs = new PropertyChangeSupport(this);
		

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			
				VistaPuesto vista = (VistaPuesto) e.getSource();

				int res = JOptionPane.showConfirmDialog(vista, "�Esta seguro que quiere cerrar el puesto de trabajo?",
						"Cerrar Puesto", JOptionPane.YES_NO_OPTION);

				if (res == JOptionPane.YES_OPTION) {
					vista.getPropertyChangeSupport().firePropertyChange("Cerrar Puesto", null, null);
					vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
	}

	/**
	 * M�todo encargado de devolver el n�mero del puesto<br>
	 * 
	 * @return n�mero del puesto.<br>
	 */
	@Override
	public int getNumeroPuesto() {
		return this.numeroPuesto;
	}

	/**
	 * M�todo encargado de setear los action commands.<br>
	 */
	private void setActionCommands() {
		this.btnEliminar.setActionCommand(IVistaPuesto.ELIMINAR);
		this.btnLlamar.setActionCommand(IVistaPuesto.LLAMAR);
	}

	
	
	/**
	 * M�todo encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	@Override
	public void setActionListener(ActionListener c) {
		this.btnLlamar.addActionListener(c);
		this.btnEliminar.addActionListener(c);
	}

	public void setPropertyChangeListener(PropertyChangeListener c) {
		this.pcs.addPropertyChangeListener(c);
	}
	
	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return this.pcs;
	}
	
	/**
	 * M�todo encargado de abrir la vista.<br>
	 * <b> Post: </b> La vista es visible.<br>
	 */
	@Override
	public void abrir() {
		if (this.numeroPuesto != 0) {
			setBounds(1200, 100, 450, 300);
			setVisible(true);
		} else
			this.dispose();
	}

	@Override
	public String toString() {
		return "Puesto : " + this.numeroPuesto;
	}

	/**
	 * M�todo encargado de mostrar el cliente al que el puesto se encuentra
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
	 * M�todo encargado de eliminar la vista del puesto<br>
	 */
	@Override
	public void dispose() {
		super.dispose();
	}

	/**
	 * M�todo encargado de habilitar el bot�n para eliminar un cliente del
	 * puesto.<br>
	 */
	@Override
	public void habilitarBotonEliminar() {
		this.btnEliminar.setEnabled(true);
	}

	/**
	 * M�todo encargado de deshabilitar el bot�n para eliminar un cliente del
	 * puesto<br>
	 */
	@Override
	public void deshabilitarBotonEliminar() {
		this.btnEliminar.setEnabled(false);
	}

	@Override
	public void setNumeroPuesto(int numero) {
		this.numeroPuesto = numero;
		this.setTitle("Puesto de trabajo numero " + String.valueOf(this.numeroPuesto));
	}
}
