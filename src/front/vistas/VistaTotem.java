package front.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class VistaTotem extends JFrame {

	private JPanel panelPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaTotem frame = new VistaTotem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaTotem() {
		setTitle("T\u00F3tem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 450);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(6, 0, 0, 0));
		
		JPanel panelTitulo = new JPanel();
		FlowLayout fl_panelTitulo = (FlowLayout) panelTitulo.getLayout();
		fl_panelTitulo.setVgap(0);
		fl_panelTitulo.setHgap(0);
		panelTitulo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ingrese su DNI", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPrincipal.add(panelTitulo);
		
		JLabel lblDisplay = new JLabel("[Display]");
		panelTitulo.add(lblDisplay);
		lblDisplay.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelN1 = new JPanel();
		panelPrincipal.add(panelN1);
		panelN1.setLayout(new GridLayout(1, 3, 0, 0));
		
		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN1.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN1.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN1.add(btn3);
		
		JPanel panelN2 = new JPanel();
		panelPrincipal.add(panelN2);
		panelN2.setLayout(new GridLayout(1, 3, 0, 0));
		
		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN2.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN2.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN2.add(btn6);
		
		JPanel panelN3 = new JPanel();
		panelPrincipal.add(panelN3);
		panelN3.setLayout(new GridLayout(1, 3, 0, 0));
		
		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN3.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN3.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN3.add(btn9);
		
		JPanel panelN4 = new JPanel();
		panelPrincipal.add(panelN4);
		panelN4.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblVacia = new JLabel("");
		panelN4.add(lblVacia);
		
		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelN4.add(btn0);
		
		JPanel panelInferior = new JPanel();
		panelPrincipal.add(panelInferior);
		panelInferior.setLayout(new GridLayout(1, 2, 0, 0));
		
		JButton btnEliminar = new JButton("<-");
		panelInferior.add(btnEliminar);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelInferior.add(btnAceptar);
	}

}
