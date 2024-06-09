package vistas;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import negocio.Sistema;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

public class VentanaUsuario extends JFrame implements IVista{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelViaje;
	private JLabel lblNewLabel;
	private JPanel panelBotonesZonas;
	private JRadioButton botonEstandar;
	private JRadioButton botonCalleSinAsfaltar;
	private JRadioButton botonPeligroso;
	private JPanel panelMascota_Y_Equipaje;
	private JPanel panelMascota;
	private JLabel lblNewLabel_1;
	private JPanel panel_4;
	private JRadioButton botonMascotaSi;
	private JRadioButton botonMascotaNo;
	private JPanel panelEquipaje;
	private JLabel lblNewLabel_2;
	private ButtonGroup grupoZona;
	private ButtonGroup grupoMascota;
	private ButtonGroup grupoEquipaje;
	private JPanel panel_6;
	private JRadioButton botonManual;
	private JRadioButton botonBaul;
	private JPanel panelPasajero_Y_Aceptar;
	private JPanel panelPasajeros_Y_Distancia;
	private JLabel lblNewLabel_3;
	private JTextField textFieldPasajeros;
	private JPanel panel_9;
	private JPanel panel_10;
	private JButton botonAceptar;
	private JLabel lblNewLabel_4;
	private JTextField textFieldDistancia;
	private JPanel panel;
	private JPanel panel_1;
	/**
	 * Create the frame.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuario frame = new VentanaUsuario();
					Sistema sistema=Sistema.getInstance();
					Controlador c=new Controlador(sistema,frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaUsuario() {
		super("Realizar pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		this.panelViaje = new JPanel();
		this.contentPane.add(this.panelViaje);
		this.panelViaje.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("Zona del viaje");
		this.panelViaje.add(this.lblNewLabel, BorderLayout.NORTH);
		
		this.panelBotonesZonas = new JPanel();
		this.panelViaje.add(this.panelBotonesZonas, BorderLayout.CENTER);
		this.panelBotonesZonas.setLayout(new GridLayout(3, 1, 0, 0));
		
		this.botonEstandar = new JRadioButton("Estandar");
		this.panelBotonesZonas.add(this.botonEstandar);
		
		this.botonCalleSinAsfaltar = new JRadioButton("Calle sin asfaltar");
		this.panelBotonesZonas.add(this.botonCalleSinAsfaltar);
		
		this.botonPeligroso = new JRadioButton("Peligrosa");
		this.panelBotonesZonas.add(this.botonPeligroso);
		
		this.panelMascota_Y_Equipaje = new JPanel();
		this.contentPane.add(this.panelMascota_Y_Equipaje);
		this.panelMascota_Y_Equipaje.setLayout(new GridLayout(2, 1, 0, 0));
		
		this.panelMascota = new JPanel();
		this.panelMascota_Y_Equipaje.add(this.panelMascota);
		this.panelMascota.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel_1 = new JLabel("Mascota");
		this.panelMascota.add(this.lblNewLabel_1, BorderLayout.NORTH);
		
		this.panel_4 = new JPanel();
		this.panelMascota.add(this.panel_4, BorderLayout.CENTER);
		this.panel_4.setLayout(new GridLayout(2, 0, 0, 0));
		
		this.botonMascotaSi = new JRadioButton("Si");
		this.panel_4.add(this.botonMascotaSi);
		
		this.botonMascotaNo = new JRadioButton("No");
		this.panel_4.add(this.botonMascotaNo);
		
		this.panelEquipaje = new JPanel();
		this.panelMascota_Y_Equipaje.add(this.panelEquipaje);
		this.panelEquipaje.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel_2 = new JLabel("Equipaje");
		this.panelEquipaje.add(this.lblNewLabel_2, BorderLayout.NORTH);
		
		this.panel_6 = new JPanel();
		this.panelEquipaje.add(this.panel_6, BorderLayout.CENTER);
		this.panel_6.setLayout(new GridLayout(2, 0, 0, 0));
		
		this.botonManual = new JRadioButton("Manual");
		this.panel_6.add(this.botonManual);
		
		this.botonBaul = new JRadioButton("Baul");
		this.panel_6.add(this.botonBaul);
		
		this.grupoZona=new ButtonGroup();
		this.grupoZona.add(botonCalleSinAsfaltar);
		this.grupoZona.add(botonEstandar);
		this.grupoZona.add(botonPeligroso);
		
		this.panelPasajero_Y_Aceptar = new JPanel();
		this.contentPane.add(this.panelPasajero_Y_Aceptar);
		this.panelPasajero_Y_Aceptar.setLayout(new BorderLayout(0, 0));
		
		this.panelPasajeros_Y_Distancia = new JPanel();
		this.panelPasajero_Y_Aceptar.add(this.panelPasajeros_Y_Distancia, BorderLayout.CENTER);
		this.panelPasajeros_Y_Distancia.setLayout(new GridLayout(4, 0, 0, 0));
		
		this.panel_10 = new JPanel();
		this.panelPasajeros_Y_Distancia.add(this.panel_10);
		
		this.lblNewLabel_3 = new JLabel("Cantidad de pasajeros");
		this.panel_10.add(this.lblNewLabel_3);
		
		this.panel_9 = new JPanel();
		this.panelPasajeros_Y_Distancia.add(this.panel_9);
		
		this.botonAceptar=new JButton("Realizar Pedido");
		this.panelPasajero_Y_Aceptar.add(this.botonAceptar, BorderLayout.SOUTH);
		
		this.textFieldPasajeros = new JTextField();
		this.panel_9.add(this.textFieldPasajeros);
		this.textFieldPasajeros.setColumns(10);
		
		this.panel_1 = new JPanel();
		this.panelPasajeros_Y_Distancia.add(this.panel_1);
		
		this.lblNewLabel_4 = new JLabel("Distancia del viaje:");
		this.panel_1.add(this.lblNewLabel_4);
		
		this.panel = new JPanel();
		this.panelPasajeros_Y_Distancia.add(this.panel);
		
		this.textFieldDistancia = new JTextField();
		this.panel.add(this.textFieldDistancia);
		this.textFieldDistancia.setColumns(10);
		
		this.botonAceptar.setActionCommand("Realizo_Pedido");
		
		this.grupoMascota= new ButtonGroup();
		this.grupoMascota.add(botonMascotaSi);
		this.grupoMascota.add(botonMascotaNo);
		
		this.grupoEquipaje= new ButtonGroup();
		this.grupoEquipaje.add(botonManual);
		this.grupoEquipaje.add(botonBaul);
		
		
		this.setVisible(true);
	}

	@Override
	public void cerrarse() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

	@Override
	public void setControlador(Controlador c) {
		this.botonAceptar.addActionListener(c);
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
			JDialog modalDialog = new JDialog(this,"Error", true);
			modalDialog.getContentPane().add(new JLabel(arg));
			modalDialog.setSize(300, 200);
			modalDialog.setVisible(true);
	}

	@Override
	public String getNombreReal() {
		return null;
	}

	@Override
	public String getZona() {
		if(this.botonEstandar.isSelected())
			return "Estandar";
		else if(this.botonPeligroso.isSelected())
			return "Peligrosa";
		else if(this.botonCalleSinAsfaltar.isSelected())
			return "Calle sin asfaltar";
		return null;
	}

	@Override
	public String getMascota() {
		if(this.botonMascotaSi.isSelected())
			return "Si";
		else if(this.botonMascotaNo.isSelected())
			return "No";
		return null;
	}

	@Override
	public String getEquipaje() {
		if(this.botonManual.isSelected())
			return "No";
		else if(this.botonBaul.isSelected())
			return "Si";
		return null;
	}

	@Override
	public String getPasajeros() {
		return this.textFieldPasajeros.getText();
	}

	@Override
	public String getDistancia() {
		return this.textFieldDistancia.getText();
	}

	@Override
	public IVista pasarRegistro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IVista pasarRealizarPedido() {
		return this;
	}

	@Override
	public IVista pasarViaje() {
		this.setVisible(false); 
		return new VentanaViaje();
	}
	
	
}
