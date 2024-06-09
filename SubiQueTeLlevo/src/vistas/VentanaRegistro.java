package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.ClienteExistenteException;
import modelo.Cliente;
import negocio.Controlador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;

public class VentanaRegistro extends JFrame  implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel registroPane;
	private JLabel lblNombre;
	private JLabel lblUser;
	private JLabel lblPw;
	private JTextField in_nombreRealField;
	private JTextField in_userField;
	private JPanel btnpanel;
	private JButton btnAceptar;
	private JPasswordField passwordField;
	
	Controlador c = new Controlador(this);
	/**
	 * Create the frame.
	 */
	public VentanaRegistro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 377);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.registroPane = new JPanel();
		this.contentPane.add(this.registroPane);
		this.registroPane.setLayout(new GridLayout(3, 2, 2, 2));
		
		this.lblNombre = new JLabel("Nombre Real");
		this.registroPane.add(this.lblNombre);
		
		this.in_nombreRealField = new JTextField();
		this.registroPane.add(this.in_nombreRealField);
		this.in_nombreRealField.setColumns(10);
		this.in_nombreRealField.addKeyListener(this);
		
		this.lblUser = new JLabel("Usuario");
		this.registroPane.add(this.lblUser);
		
		this.in_userField = new JTextField();
		this.registroPane.add(this.in_userField);
		this.in_userField.setColumns(10);
		this.in_userField.addKeyListener(this);
		
		this.lblPw = new JLabel("Contraseña");
		this.registroPane.add(this.lblPw);
		
		this.passwordField = new JPasswordField();
		this.registroPane.add(this.passwordField);
		this.passwordField.addKeyListener(this);
		
		this.btnpanel = new JPanel();
		this.contentPane.add(this.btnpanel, BorderLayout.SOUTH);
		
		this.btnAceptar = new JButton("Aceptar");
		this.btnpanel.add(this.btnAceptar);
		this.btnAceptar.setActionCommand("registro");
		this.btnAceptar.setEnabled(false);
		this.btnAceptar.addActionListener(this);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		validarEntradas();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actCmd = e.getActionCommand();
		if(actCmd.equals("registro")) {
			try{
				Cliente cliente = new Cliente(this.in_nombreRealField.getText(),this.in_userField.getText(),this.passwordField.getText());
				this.c.registrarCliente(cliente);
				this.dispose();
			}
			catch(ClienteExistenteException ex) {
				Error error = new Error(ex.getMessage());
				error.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				error.setVisible(true);
			}
		}
	}
	/**
	 * Comprueba el ingreso correcto de los campos usuario y contraseña de la vista <br>
	 */
	public void validarEntradas() {
		String in_nombre = this.in_nombreRealField.getText();
		char[] in_contrasena = this.passwordField.getPassword();
		String in_usuario = this.in_userField.getText();
		
		this.btnAceptar.setEnabled(in_nombre!=null && !in_nombre.isBlank() && in_contrasena!=null && in_contrasena.length!=0 &&  in_usuario != null && !in_usuario.isBlank());
	}
	

}
