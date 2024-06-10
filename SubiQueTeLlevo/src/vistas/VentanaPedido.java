package vistas;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.Controlador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

public class VentanaPedido extends JFrame implements IVista,ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelZona;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JRadioButton botonEstandar;
	private JRadioButton botonCalleSinAsfaltar;
	private JRadioButton botonPeligroso;
	private JPanel panelMascota_Equipaje;
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
	private JPanel panelPasajeros_Aceptar;
	private JPanel panelPasajero_Distancia;
	private JLabel lblNewLabel_3;
	private JTextField textFieldPasajeros;
	private JPanel panel_9;
	private JPanel panel_10;
	private JButton botonAceptar;
	private JLabel lblNewLabel_4;
	private JPanel panel;
	private JTextField textFieldDistancia;
	private JPanel panel_2;
	
	private Controlador controlador;
	/**
	 * Create the frame.
	 */
	public VentanaPedido() {
		super("Realizar pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		this.panelZona = new JPanel();
		this.contentPane.add(this.panelZona);
		this.panelZona.setLayout(new BorderLayout(0, 0));
		
		this.lblNewLabel = new JLabel("Zona del viaje");
		this.panelZona.add(this.lblNewLabel, BorderLayout.NORTH);
		
		this.panel_1 = new JPanel();
		this.panelZona.add(this.panel_1, BorderLayout.CENTER);
		this.panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		this.botonEstandar = new JRadioButton("Estandar");
		this.panel_1.add(this.botonEstandar);
		
		this.botonCalleSinAsfaltar = new JRadioButton("Calle sin asfaltar");
		this.panel_1.add(this.botonCalleSinAsfaltar);
		
		this.botonPeligroso = new JRadioButton("Peligrosa");
		this.panel_1.add(this.botonPeligroso);
		
		this.panelMascota_Equipaje = new JPanel();
		this.contentPane.add(this.panelMascota_Equipaje);
		this.panelMascota_Equipaje.setLayout(new GridLayout(2, 1, 0, 0));
		
		this.panelMascota = new JPanel();
		this.panelMascota_Equipaje.add(this.panelMascota);
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
		this.panelMascota_Equipaje.add(this.panelEquipaje);
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
		
		this.panelPasajeros_Aceptar = new JPanel();
		this.contentPane.add(this.panelPasajeros_Aceptar);
		this.panelPasajeros_Aceptar.setLayout(new BorderLayout(0, 0));
		
		this.panelPasajero_Distancia = new JPanel();
		this.panelPasajeros_Aceptar.add(this.panelPasajero_Distancia, BorderLayout.CENTER);
		this.panelPasajero_Distancia.setLayout(new GridLayout(4, 0, 0, 0));
		
		this.panel_10 = new JPanel();
		this.panelPasajero_Distancia.add(this.panel_10);
		
		this.lblNewLabel_3 = new JLabel("Cantidad de pasajeros");
		this.panel_10.add(this.lblNewLabel_3);
		
		this.panel_9 = new JPanel();
		this.panelPasajero_Distancia.add(this.panel_9);
		
		this.botonAceptar=new JButton("Realizar Pedido");
		this.panelPasajeros_Aceptar.add(this.botonAceptar, BorderLayout.SOUTH);
		
		this.textFieldPasajeros = new JTextField();
		this.panel_9.add(this.textFieldPasajeros);
		this.textFieldPasajeros.setColumns(10);
		
		this.panel = new JPanel();
		this.panelPasajero_Distancia.add(this.panel);
		
		this.lblNewLabel_4 = new JLabel("Distancia");
		this.panel.add(this.lblNewLabel_4);
		
		this.panel_2 = new JPanel();
		this.panelPasajero_Distancia.add(this.panel_2);
		
		this.textFieldDistancia = new JTextField();
		this.panel_2.add(this.textFieldDistancia);
		this.textFieldDistancia.setColumns(10);
		
		this.botonAceptar.setActionCommand("Realizar_Pedido");
		
		this.grupoMascota=new ButtonGroup();
		this.grupoEquipaje=new ButtonGroup();
		
		this.grupoMascota.add(botonMascotaSi);
		this.grupoMascota.add(botonMascotaNo);
		
		this.grupoEquipaje.add(botonBaul);
		this.grupoEquipaje.add(botonManual);
		
		setActionListener(this);
		
		this.setVisible(true);
	}

	
	public void setActionListener(ActionListener c) {
		this.botonAceptar.addActionListener(c);
	}


	public void crearMensaje(String arg) {
			JDialog modalDialog = new JDialog(this,"Error", true);
			modalDialog.getContentPane().add(new JLabel(arg));
			modalDialog.setSize(300, 200);
			modalDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Realizar_Pedido")) {
			String strPasajeros =this.textFieldPasajeros.getText();
			String strDist =this.textFieldPasajeros.getText();
			boolean pet,baul;
			int pasajeros,dist;
			if(zona()!=null && mascota()!=null && equipaje()!=null && strPasajeros!=null && strPasajeros.equalsIgnoreCase("") && strDist!=null &&strDist.equalsIgnoreCase("")) {
				try {
					
				pasajeros=Integer.parseInt(strPasajeros);
				dist=Integer.parseInt(strDist);
				
				if(mascota().equalsIgnoreCase("Si"))
					pet=true;
				else
					pet=false;
				if(equipaje().equalsIgnoreCase("Si"))
					baul=true;
				else
					baul=false;
				
				controlador.crearPedido(zona(),pet,baul,pasajeros,dist);
				setVisible(false);
				}catch(Exception exp) {
					new Error("Datos Invalidos");
				}
				
			}else {
				new Error("Datos Invalidos");
			}
				
		}
		
	}


	private String zona() {
		if(this.botonEstandar.isSelected())
			return "Estandar";
		else
			if(this.botonCalleSinAsfaltar.isSelected())
				return "Calle sin asfaltar";
			else
				if(this.botonPeligroso.isSelected())
					return "Peligrosa";
		return null;
	}
	
	private String mascota() {
		if(this.botonMascotaSi.isSelected())
			return "Si";
		else
			if(this.botonMascotaNo.isSelected())
				return "No";
			else
		return null;
	}
	
	private String equipaje() {
		if(this.botonManual.isSelected())
			return "No";
		else
			if(this.botonBaul.isSelected())
				return "Si";
			else
		return null;
	}


}
