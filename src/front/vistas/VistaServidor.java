package front.vistas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import front.interfaces.IVista;

/**
 * @author Grupo12.<br>
 *         Clase que representa la vista del Servidor. Implementa IVista.<br>
 */
public class VistaServidor extends JFrame implements IVista {

	private static final long serialVersionUID = -5161354435027911853L;
	private JPanel contentPane;
	private JLabel lblDisplay;

	/**
	 * Método constructor de VistaServidor.<br>
	 */
	public VistaServidor(String titulo) {
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblDisplay = new JLabel("SERVIDOR EN LINEA");
		lblDisplay.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDisplay);

	}

	/**
	 * Método encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	@Override
	public void setActionListener(ActionListener c) {
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


}
