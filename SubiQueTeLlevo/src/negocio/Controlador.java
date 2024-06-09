package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import excepciones.ClienteExistenteException;
import excepciones.UsuarioInexistenteException;
import modelo.Cliente;
import modelo.Usuario;
import negocio.Sistema;
import simulacion.Simulacion;

import javax.swing.JFrame;

/**
 *
 */
public class Controlador implements ActionListener{
	private JFrame vista;

	public Controlador(JFrame vista) {
		this.vista = vista;
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
}
