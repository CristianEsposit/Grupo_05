package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

import excepciones.ClienteExistenteException;
import excepciones.PedidoIncoherenteException;
import excepciones.UsuarioInexistenteException;
import modelo.Cliente;
import modelo.Usuario;
import negocio.Sistema;
import ojos.OjoViaje;
import persistencia.PersistenciaXML;
import persistencia.SistemaDTO;
import persistencia.Util;
import simulacion.Simulacion;
import vistas.VentanaPedido;
import vistas.VentanaViaje;

import javax.swing.JFrame;
public class Controlador implements ActionListener{
	private JFrame vista; // IVista Futura
	private Cliente humano;
	private Sistema modelo;
	private OjoViaje ojo;

	public Controlador(JFrame vista) {
		this.vista = vista;
		modelo=Sistema.getInstance();
	}
	/**
	 * 
	 * @param user, es el usuario que se ingresó en el Login
	 * @return la contraseña correspondiente 
	 * @throws UsuarioInexistenteException si el usuario no existe en el sistema
	 */

	public String getContraseña(String user) throws UsuarioInexistenteException{
		Usuario usuario = Sistema.getInstance().consultarCliente(user);
		if (usuario == null)
			throw new UsuarioInexistenteException("Usuario no encontrado");
		else 
			return usuario.getPassword();
	}
	
	public Cliente getCliente(String user) {
		Cliente cliente = Sistema.getInstance().consultarCliente(user);
		return cliente;
	}
	
	public void setHumano(Cliente c) {
		this.humano = c;
	}
	/**
	 * 
	 * @param c Nuevo Cliente a registrar
	 * @throws ClienteExistenteException si el nombre de usuario ya existe
	 */

	public void registrarCliente(Cliente c) throws ClienteExistenteException{
		Sistema.getInstance().agregar(c);
		this.humano = c;
	}
	
	public void crearPedido(String zona,boolean pet,boolean baul,int pasajeros,int dist) {
			ojo=new OjoViaje(modelo.getSimulacion().getRecursoCompartido(),new VentanaViaje(this),humano);
			this.modelo.getSimulacion().getRecursoCompartido().realizarPedido(LocalDateTime.now(), zona, pet, pasajeros, baul, humano, dist);
	}

	public void pagar() {
			this.modelo.getSimulacion().getRecursoCompartido().pagarViaje(humano);
			this.vista.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Simulacion sim;
		String actCmd = e.getActionCommand();
		if(actCmd.equals("iniciar_simulacion")) {
			
			int CantClientes = 0;
			int CantViajesCliente = 5;
			int cantChoferContratado =5;
			int cantChoferPermanente = 5;
			int cantChoferTemporario = 5; 
			int cantMaxViajesChofer = 5;
			int cantMotos = 5;
			int cantAutos = 5;
			int cantCombis = 5;
			
			sim = new Simulacion (Sistema.getInstance(), CantClientes, CantViajesCliente, cantChoferContratado, cantChoferPermanente, cantChoferTemporario, cantMaxViajesChofer, cantMotos, cantAutos, cantCombis);
			Sistema.getInstance().setSimulacion(sim);
			sim.iniciaSimulacion();
			//this.GuardarDatos();
		}
	}
	public void setVista(VentanaPedido ventanaPedido) {
		vista=ventanaPedido;
		
	}

		private void GuardarDatos() {
		PersistenciaXML persistencia = new PersistenciaXML();
		try {
			persistencia.abrirOutput("Subi_que_te_llevo.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			persistencia.abrirOutput("Subi_que_te_llevo.xml");
			System.out.println("Crea el archivo de escritura.");
			SistemaDTO sDTO = Util.SistemaDTOFromSistema(Sistema.getInstance());
			persistencia.escribir(sDTO);
			System.out.println("Archivo grabado exitosamente.");
			persistencia.cerrarOutput();
			System.out.println("Archivo cerrado.");
		}
		catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private void RecuperarDatos() {
		PersistenciaXML persistencia = new PersistenciaXML();
		Sistema sistema = null;
		try {
			persistencia.abrirInput("Subi_que_te_llevo.xml");
			System.out.println("Archivo abierto.");
			SistemaDTO sDTO = (SistemaDTO) persistencia.leer();
			sistema = Util.SistemaFromSistemaDTO(sDTO);
			System.out.println("Archivo recuperado.");
			persistencia.cerrarInput();
			System.out.println("Archivo cerrado.");
		}
		catch (IOException e){
			System.out.println(e.getLocalizedMessage());
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
