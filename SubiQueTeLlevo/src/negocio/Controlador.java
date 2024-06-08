package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import excepciones.ChoferExistenteException;
import excepciones.ClienteExistenteException;
import excepciones.UsuarioInexistenteException;
import modelo.Cliente;
import modelo.Usuario;
import vistas.VentanaLogin;
import negocio.Sistema;

public class Controlador{
	private VentanaLogin vista; // IVista Futura
		
	public Controlador(VentanaLogin vista) {
		this.vista = vista;
	}
	
	public String getContraseña(String user) throws UsuarioInexistenteException{
		Usuario usuario = Sistema.getInstance().consultarCliente(user);
		if (usuario == null)
			throw new UsuarioInexistenteException("Usuario no encontrado");
		else 
			return usuario.getPassword();
	}
	
	public void registrarCliente(Cliente c) throws ClienteExistenteException{
		Sistema.getInstance().agregar(c);
	}
}
