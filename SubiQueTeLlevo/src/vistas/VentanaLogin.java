package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.UsuarioInexistenteException;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JLayeredPane;

public class VentanaLogin extends JFrame implements ActionListener, KeyListener, IVista{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel simulacionPanel;
	private JButton btnSimular;
	private JPanel InfoPanel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField in_UserField;
	private JPanel panelA;
	private JButton btnIngreso;
	private JPanel panelB;
	private JButton btnRegistrar;
	private JPasswordField in_passwordField;
	private JLayeredPane layeredPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
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
	public VentanaLogin() {
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 273);
		this.contentPane = new JPanel();
		this.contentPane.setForeground(new Color(0, 0, 0));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.simulacionPanel = new JPanel();
		this.contentPane.add(this.simulacionPanel, BorderLayout.NORTH);
		this.simulacionPanel.setLayout(new BorderLayout(0, 0));
		
		this.btnSimular = new JButton("Iniciar Simulacion");
		
		this.simulacionPanel.add(this.btnSimular, BorderLayout.CENTER);
		btnSimular.setActionCommand("iniciarSimulacion");
		this.btnSimular.addActionListener(this);
		
		this.InfoPanel = new JPanel();
		this.contentPane.add(this.InfoPanel, BorderLayout.CENTER);
		this.InfoPanel.setLayout(new GridLayout(3, 2, 3, 3));
		
		this.lblNewLabel = new JLabel("Usuario:");
		this.InfoPanel.add(this.lblNewLabel);
		
		this.in_UserField = new JTextField();
		this.InfoPanel.add(this.in_UserField);
		this.in_UserField.setColumns(10);
		this.in_UserField.addKeyListener(this);
		
		this.lblNewLabel_1 = new JLabel("Contraseña:");
		this.InfoPanel.add(this.lblNewLabel_1);
		
		this.in_passwordField = new JPasswordField();
		this.InfoPanel.add(this.in_passwordField);
		this.in_passwordField.addKeyListener(this);
		
		this.panelA = new JPanel();
		this.InfoPanel.add(this.panelA);
		this.panelA.setLayout(new BorderLayout(0, 0));
		
		this.btnIngreso = new JButton("Ingresar");
		this.panelA.add(this.btnIngreso, BorderLayout.CENTER);
		this.btnIngreso.setActionCommand("usuario_ingresado");
		this.btnIngreso.setEnabled(false);
		this.btnIngreso.addActionListener(this);
		
		this.panelB = new JPanel();
		this.InfoPanel.add(this.panelB);
		this.panelB.setLayout(new BorderLayout(0, 0));
		
		this.btnRegistrar = new JButton("Registrar");
		this.panelB.add(this.btnRegistrar, BorderLayout.CENTER);
		this.btnRegistrar.setActionCommand("nuevo_usuario");
		
		this.layeredPane = new JLayeredPane();
		this.contentPane.add(this.layeredPane, BorderLayout.SOUTH);
		this.btnRegistrar.addActionListener(this);
		
	}
	
	@SuppressWarnings("deprecation")
	public void verificaEnables() {
			this.btnIngreso.setEnabled(this.in_UserField.getText() != null && !this.in_UserField.getText().isEmpty() && this.in_passwordField.getPassword()!=null
					&& !this.in_passwordField.getText().isEmpty());
	}

	/*
	@SuppressWarnings("unlikely-arg-type")
	public boolean validarEntrada() {
		boolean isValid = false;
		try{
			String pW = controlador.getContraseña(this.in_UserField.getText());
			if(!pW.equals( this.in_passwordField.getPassword() ) ) {
				 try {
			            Error dialog = new Error("Contraseña Invalida");
			            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			            dialog.setVisible(true);
			            isValid = false;
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			}
			else
				isValid = true;
		}
		catch(UsuarioInexistenteException u) {
			 try {
		            Error dialog = new Error("Usuario Invalido");
		            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		            dialog.setVisible(true);
		            isValid = false;
		        } catch (Exception e) {
		            e.printStackTrace();
		       }
		}
		return isValid;
	}*/
	public void keyReleased(KeyEvent e)
	{
		verificaEnables();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cerrarse() {
		this.setVisible(false);
		
	}

	@Override
	public void setControlador(controlador.Controlador c) {
		this.btnIngreso.addActionListener(c);
		this.btnRegistrar.addActionListener(c);
		this.btnSimular.addActionListener(c);
	}

	@Override
	public String getNombreReal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombreUsuario() {
		return this.in_UserField.getText();
	}

	@Override
	public String getPasswordUsuario() {
		char[] aux=this.in_passwordField.getPassword();
		String respuesta="";
		for(int i=0;i<aux.length;i++) {
			respuesta=respuesta+aux[i];
		}
		return respuesta;
	}
	
	@Override
	public void crearMensaje(String arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getZona() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMascota() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEquipaje() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasajeros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDistancia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IVista pasarRegistro() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		return null; // falta ventana registro
	}

	@Override
	public IVista pasarRealizarPedido() {
		this.setVisible(false);
		return new VentanaUsuario();
	}

	@Override
	public IVista pasarViaje() {
		this.setVisible(false);
		return new VentanaViaje();
	}
	
}
