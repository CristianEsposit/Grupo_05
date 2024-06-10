package vistas;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class VentanaViaje extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botonPagar;
	private JTextArea textAreaEstado;

	/**
	 * Create the frame.
	 */
	public VentanaViaje(ActionListener c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.botonPagar = new JButton("Pagar");
		this.contentPane.add(this.botonPagar, BorderLayout.SOUTH);
		
		this.textAreaEstado = new JTextArea();
		this.contentPane.add(this.textAreaEstado, BorderLayout.CENTER);
		
		this.botonPagar.setVisible(false);
		this.botonPagar.setActionCommand("Pagar");
		this.botonPagar.addActionListener(c);
	}
	

	public void actualizaTextArea(String cartel) {
		this.textAreaEstado.append(cartel + "\n");
		
	}

	public void habilitarPagar() {
			this.botonPagar.setVisible(true);
		
	}
	
	

}
