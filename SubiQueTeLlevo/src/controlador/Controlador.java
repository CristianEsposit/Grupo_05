package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import excepciones.ClienteExistenteException;
import excepciones.PedidoIncoherenteException;
import excepciones.UsuarioInexistenteException;
import negocio.Sistema;
import vistas.*;
import modelo.*;

public class Controlador implements ActionListener{
	private Sistema modelo;
	private IVista vista;
	private Cliente humano;
	
	public Controlador(Sistema modelo, IVista vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}
	/*
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String actCmd = ae.getActionCommand();
		if(actCmd.equals("usuario_ingresado")){
			if (validarEntrada()) {
				//desplegar Ventana Cliente;
			}
		}
		else if(actCmd.equals("nuevo_usuario")) {
			//ventana registro
		}
	   */

	public void getContraseña(String user){
		Cliente usuario = Sistema.getInstance().consultarCliente(user);
		if (usuario == null)
			vista.crearMensaje("Usuario inexistente");
		else
			if(usuario.getPassword().equalsIgnoreCase(vista.getPasswordUsuario())) {
				humano=usuario;
				vista.cerrarse();
				vista=vista.pasarRealizarPedido();
			}else
				vista.crearMensaje("Contraseña incorrecta");
	}
	
	public void registrarCliente(Cliente c) throws ClienteExistenteException{ // utilizar metodo para registro
		Sistema.getInstance().agregar(c);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/* Capaz que lo borro
		if(e.getActionCommand().equalsIgnoreCase("BOTON_ACEPTAR_LOGIN")) { //Para ventana de login
			password=vista.getPasswordUsuario();
			usuario=vista.getNombreUsuario();
			if(password!=null && !password.equalsIgnoreCase("") && usuario!=null && !usuario.equalsIgnoreCase("")) {
				if(modelo.consultarCliente(usuario)!=null && modelo.consultarCliente(usuario).getPassword().equalsIgnoreCase(password)) {
					vista.cerrarse();
					vista=new VentanaUsuario(); //Posiblemente crear nuevo atributo vistaUsuario
				}
				else vista.crearMensaje("Usuario o contrasena incorrectos");
			}else vista.crearMensaje("No se ingreso ni nombre de usuario o contrasena");
		}else //Para ventana de registro
			if(e.getActionCommand().equalsIgnoreCase("REGISTRARSE")) {
				password=vista.getPasswordUsuario();
				usuario=vista.getNombreUsuario();
				nombre=vista.getNombreReal();
				if(password!=null && !password.equalsIgnoreCase("") && usuario!=null && !usuario.equalsIgnoreCase("") && nombre!=null && !nombre.equalsIgnoreCase("")) {
						try {
							modelo.agregar(new Cliente(nombre,usuario,password));
							vista.cerrarse();
							vista=new VentanaLogin(); //Posiblemente crear nuevo atributo vistaUsuario !!Cambiar a ventanaUsuario
						} catch (ClienteExistenteException e1) {
							// TODO Auto-generated catch block
							this.vista.crearMensaje(e1.getMessage());
						}
				}else vista.crearMensaje("No se ingreso ni nombre de usuario o contrasena o nombre real");
			}else
			*/
		
			String zona,mascota,equipaje,cantPas,dist,usuario,nombre,password;
			boolean Bmascota,Bequipaje;
			int distancia, cantidadPas;
			
			if(e.getActionCommand().equalsIgnoreCase("usuario_ingresado")) {
				usuario=vista.getNombreUsuario();
				if(usuario!=null && !usuario.equalsIgnoreCase("")) {
					this.getContraseña(usuario);
				}else
					vista.crearMensaje("Usuario invalido");
				
			}else if(e.getActionCommand().equalsIgnoreCase("nuevo_usuario")) { // paso a ventana para registrame
				vista=vista.pasarRegistro();
			}
			else if(e.getActionCommand().equalsIgnoreCase("Registrarse")) {
				vista=vista.pasarRealizarPedido();
				
			}
			else if(e.getActionCommand().equalsIgnoreCase("Realizo_Pedido")) {
				zona=vista.getZona();
				mascota=vista.getMascota();
				equipaje=vista.getEquipaje();
				cantPas=vista.getPasajeros();
				dist=vista.getDistancia();
				if(zona!=null && !zona.equalsIgnoreCase("") && mascota!=null && !mascota.equalsIgnoreCase("") && equipaje!=null && !equipaje.equalsIgnoreCase("") && cantPas!=null && !cantPas.equalsIgnoreCase("") && dist!=null && !dist.equalsIgnoreCase("")) {
					try {
					cantidadPas=Integer.parseInt(cantPas);
					distancia=Integer.parseInt(dist);
					}catch(Exception except) {
						this.vista.crearMensaje("Se ingreso caracterers incorrectos");
					}
					if(Integer.parseInt(cantPas)<11 && Integer.parseInt(dist)>0) {
							if(mascota.equalsIgnoreCase("Si"))
								Bmascota=true;
							else
								Bmascota=false;
							if(equipaje.equalsIgnoreCase("Si"))
								Bequipaje=true;
							else
								Bequipaje=false;
							
							try {
								modelo.realizarPedido(LocalDateTime.now(), zona, Bmascota, Integer.parseInt(cantPas), Bequipaje, humano, Integer.parseInt(dist));
								vista=this.vista.pasarViaje();
							} catch (PedidoIncoherenteException e1) {
								// TODO Auto-generated catch block
								this.vista.crearMensaje(e1.getMessage());
							}
						}else
							this.vista.crearMensaje("Pedido rechazado por ser incorrecto");
					}else
						this.vista.crearMensaje("Faltan espacios por seleccionar o rellenar");
				
			}
			
	}

}
