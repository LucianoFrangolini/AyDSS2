package front.totem.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import front.totem.interfaces.Inscripcion;

/**
 * @author Grupo12.<br>
 *         Clase que representa la vista del Totem. Implementa IVistaTotem.<br>
 */
public class VistaTotem extends JFrame implements Inscripcion {

	private static final long serialVersionUID = 1072509465610926668L;
	private JPanel panelPrincipal;
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnAceptar;
	private JButton btnEliminar;
	private JLabel lblDisplay;

	/**
	 * Método constructor de VistaTotem.<br>
	 */
	public VistaTotem() {
		setTitle("T\u00F3tem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(6, 0, 0, 0));

		JPanel panelTitulo = new JPanel();
		FlowLayout fl_panelTitulo = (FlowLayout) panelTitulo.getLayout();
		fl_panelTitulo.setVgap(0);
		fl_panelTitulo.setHgap(0);
		panelTitulo.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ingrese su DNI", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPrincipal.add(panelTitulo);

		lblDisplay = new JLabel("");
		panelTitulo.add(lblDisplay);
		lblDisplay.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panelN1 = new JPanel();
		panelPrincipal.add(panelN1);
		panelN1.setLayout(new GridLayout(1, 3, 0, 0));

		btn1 = new JButton("1");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN1.add(btn1);

		btn2 = new JButton("2");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN1.add(btn2);

		btn3 = new JButton("3");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN1.add(btn3);

		JPanel panelN2 = new JPanel();
		panelPrincipal.add(panelN2);
		panelN2.setLayout(new GridLayout(1, 3, 0, 0));

		btn4 = new JButton("4");
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN2.add(btn4);

		btn5 = new JButton("5");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN2.add(btn5);

		btn6 = new JButton("6");
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN2.add(btn6);

		JPanel panelN3 = new JPanel();
		panelPrincipal.add(panelN3);
		panelN3.setLayout(new GridLayout(1, 3, 0, 0));

		btn7 = new JButton("7");
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN3.add(btn7);

		btn8 = new JButton("8");
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN3.add(btn8);

		btn9 = new JButton("9");
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN3.add(btn9);

		JPanel panelN4 = new JPanel();
		panelPrincipal.add(panelN4);
		panelN4.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblVacia = new JLabel("");
		panelN4.add(lblVacia);

		btn0 = new JButton("0");
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN4.add(btn0);

		JPanel panelInferior = new JPanel();
		panelPrincipal.add(panelInferior);
		panelInferior.setLayout(new GridLayout(1, 2, 0, 0));

		btnEliminar = new JButton("<-");
		panelInferior.add(btnEliminar);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelInferior.add(btnAceptar);

		setActionCommands();
	}

	/**
	 * Método encargado de setear un actionListener. <br>
	 * 
	 * @param c de tipo ActionListener: Representa el ActionListener a setear.<br>
	 */
	@Override
	public void setActionListener(ActionListener c) {
		this.btn0.addActionListener(c);
		this.btn1.addActionListener(c);
		this.btn2.addActionListener(c);
		this.btn3.addActionListener(c);
		this.btn4.addActionListener(c);
		this.btn5.addActionListener(c);
		this.btn6.addActionListener(c);
		this.btn7.addActionListener(c);
		this.btn8.addActionListener(c);
		this.btn9.addActionListener(c);
		this.btnAceptar.addActionListener(c);
		this.btnEliminar.addActionListener(c);
	}

	/**
	 * Método encargado de setear los action commands.<br>
	 */
	@Override
	public void setActionCommands() {
		this.btn0.setActionCommand(Inscripcion.CERO);
		this.btn1.setActionCommand(Inscripcion.UNO);
		this.btn2.setActionCommand(Inscripcion.DOS);
		this.btn3.setActionCommand(Inscripcion.TRES);
		this.btn4.setActionCommand(Inscripcion.CUATRO);
		this.btn5.setActionCommand(Inscripcion.CINCO);
		this.btn6.setActionCommand(Inscripcion.SEIS);
		this.btn7.setActionCommand(Inscripcion.SIETE);
		this.btn8.setActionCommand(Inscripcion.OCHO);
		this.btn9.setActionCommand(Inscripcion.NUEVE);
		this.btnAceptar.setActionCommand(Inscripcion.ENVIAR);
		this.btnEliminar.setActionCommand(Inscripcion.BACKSPACE);
	}

	/**
	 * Método encargado de abrir la vista.<br>
	 * <b> Post: </b> La vista es visible.<br>
	 */
	@Override
	public void abrir() {
		setBounds(100, 500, 463, 450);
		setVisible(true);
	}

	/**
	 * Método getter del contenido del label del display.<br>
	 * 
	 * @return contenido del label del display del totem.<br>
	 */
	@Override
	public String getLabelDisplay() {
		return this.lblDisplay.getText();
	}

	/**
	 * Método setter del display del totem.<br>
	 * 
	 * @param texto de tipo String: Representa el texto a mostrar en el label.<br>
	 */
	@Override
	public void setLabelDisplay(String texto) {
		this.lblDisplay.setText(texto);
	}

	/**
	 * Método encargado de habilitar el botón para enviar información.<br>
	 */
	@Override
	public void habilitarEnvio() {
		this.btnAceptar.setEnabled(true);
	}

	/**
	 * Método encargado de deshabilitar el botón para enviar información.<br>
	 */
	@Override
	public void deshabilitarEnvio() {
		this.btnAceptar.setEnabled(false);
	}

}
