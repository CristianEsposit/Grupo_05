package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.UsuarioInexistenteException;
import negocio.Controlador;

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
/**
 * Ventana que permite el ingreso de un usuario e iniciar la simulacion<br>
 */
public class VentanaLogin extends JFrame implements ActionListener, KeyListener {

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
	
	private Controlador controlador = new Controlador(this);
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
		btnSimular.setActionCommand("iniciar_simulacion");
		this.btnSimular.addActionListener(controlador);
		
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
	/**
	 * Ante cualquier cambio en los JTextField verifica las entradas <br>
	 */
	@SuppressWarnings("deprecation")
	public void verificaEnables() {
			this.btnIngreso.setEnabled(this.in_UserField.getText() != null && !this.in_UserField.getText().isEmpty() && this.in_passwordField.getPassword()!=null
					&& !this.in_passwordField.getText().isEmpty());
	}

	
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
			else {
				isValid = true;
				controlador.setHumano(controlador.getCliente(this.in_UserField.getText()));
			}
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
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String actCmd = ae.getActionCommand();
		if(actCmd.equals("usuario_ingresado")){
			if (validarEntrada()) {
				this.controlador.setVista(new VentanaPedido(this.controlador));
			}
		}
		else if(actCmd.equals("nuevo_usuario")) {
			try {
				VentanaRegistro frame = new VentanaRegistro(this.controlador);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	    
	
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
	
}
