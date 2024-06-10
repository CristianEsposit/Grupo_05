package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import excepciones.ClienteExistenteException;
import excepciones.PedidoIncoherenteException;
import excepciones.UsuarioInexistenteException;
import modelo.Cliente;
import modelo.Usuario;
import negocio.Sistema;
import ojos.OjoViaje;
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
	/**
	 * 
	 * @param c Nuevo Cliente a registrar
	 * @throws ClienteExistenteException si el nombre de usuario ya existe
	 */

	public void registrarCliente(Cliente c) throws ClienteExistenteException{
		Sistema.getInstance().agregar(c);
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
			
			int CantClientes =5;
			int CantViajesCliente = 5;
			int cantChoferContratado =5;
			int cantChoferPermanente = 5;
			int cantChoferTemporario = 5; 
			int cantMaxViajesChofer = 5;
			int cantMotos = 5;
			int cantAutos = 5;
			int cantCombis = 5;
			
			sim = new Simulacion (Sistema.getInstance(), CantClientes, CantViajesCliente, cantChoferContratado, cantChoferPermanente, cantChoferTemporario, cantMaxViajesChofer, cantMotos, cantAutos, cantCombis);
			sim.iniciaSimulacion();
		}
	}
	public void setVista(VentanaPedido ventanaPedido) {
		vista=ventanaPedido;
		
	}
}
