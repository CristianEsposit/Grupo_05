package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class VentanaViaje extends JFrame implements IVista{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextArea textAreaEstadoDelViaje;
	private JButton botonPagar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaViaje frame = new VentanaViaje();
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
	public VentanaViaje() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("Estado del viaje");
		this.contentPane.add(this.lblNewLabel, BorderLayout.NORTH);
		
		this.textAreaEstadoDelViaje = new JTextArea();
		this.contentPane.add(this.textAreaEstadoDelViaje, BorderLayout.CENTER);
		
		this.botonPagar = new JButton("Pagar");
		this.contentPane.add(this.botonPagar, BorderLayout.SOUTH);
		this.botonPagar.setVisible(false);
	}

	@Override
	public void cerrarse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setControlador(Controlador c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNombreReal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNombreUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordUsuario() {
		// TODO Auto-generated method stub
		return null;
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
	public IVista pasarRegistro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVista pasarRealizarPedido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVista pasarViaje() {
		// TODO Auto-generated method stub
		return null;
	}

}
