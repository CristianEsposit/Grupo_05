package negocio;

import java.time.LocalDateTime;
import java.util.ArrayList;

import excepciones.CantidadDePasajerosException;
import excepciones.PedidoIncoherenteException;
import excepciones.ZonaInvalidaException;
import modelo.Chofer;
import modelo.Cliente;
import modelo.IVehiculo;
import modelo.Pedido;
import modelo.Viaje;

public class Sistema {
	private static Sistema instance = null;
	private ArrayList<IVehiculo> flota = new ArrayList<IVehiculo>();
	private ArrayList<Chofer> choferes = new ArrayList<Chofer>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
	
	private Sistema() {
		
	}

	public static Sistema getInstance() {
		if (Sistema.instance == null)
			Sistema.instance = new Sistema();
		return instance;
	}
	
	public void agregar(Cliente cliente) {
		assert cliente != null : "cliente invalido";
		if (clientes.contains(cliente))
			System.out.println("El cliente no puede agregarse porque ya existe.");
		else {
			this.clientes.add(cliente);
		}
	}
	
	public void modificar(Cliente cliente) {  //que quiero modificar
		assert cliente != null : "cliente no valido";
		if (clientes.contains(cliente)) {
			
		}
	}
	
	public Cliente consulta(Cliente cliente) {
		if (clientes.contains(cliente))
			return cliente;
		else
			return null;
	}
	
	public Pedido realizarPedido(LocalDateTime fechaYHora, String zona, boolean mascota, int cantPasajeros, boolean equipajeBaul, Cliente cliente) {
		assert fechaYHora != null : "fecha invalida";
		assert zona != null : "zona no puede ser null";
		assert cantPasajeros > 0 : "cantidad de pasajeros > a 0";
		assert cliente != null : "cliente no valido";
		Pedido pedido = null;
		try {
			pedido = new Pedido(fechaYHora, zona, mascota, cantPasajeros, equipajeBaul, cliente);
			System.out.println("El pedido fue realizado con exito.");
		}
		catch (CantidadDePasajerosException e) {
			System.out.println(e.getMessage());
		}
		catch (ZonaInvalidaException e) {
			System.out.println(e.getMessage());
		}
		catch (PedidoIncoherenteException e) {
			System.out.println(e.getMessage());
		}
		return pedido;
	}
	
	public void pagarViaje(Cliente cliente, Viaje viaje) {
		viaje.pagar();
	}
	
	public void calificarChofer(Chofer chofer, int puntaje) {
		int puntajeAct = chofer.getPuntaje();
		chofer.setPuntaje(puntajeAct + puntaje);
	}
	
	public void verHistorialDeViaje(Cliente cliente) {  //historial de un cliente especifico
		ArrayList<Viaje> viajesCliente = cliente.getViajes();
		for (int i = 0; i < viajesCliente.size(); i++) {
			System.out.println(viajesCliente.get(i));
		}
	}
	
	public ArrayList<Cliente> listadoClientes() {
		return clientes;
	}
	
	public ArrayList<Chofer> listadoChoferes() {
		return choferes;
	}
	
	public ArrayList<IVehiculo> listadoVehiculos() {
		return flota;
	}
	
	public double salarioChofer(Chofer chofer) {  //devuelve el salario mensual de un chofer
		return chofer.getSueldo();
	}
	
	public double totalDineroNecesario() { //devuelve el total necesario para pagar los salarios
		double total = 0;
		for (int i=0; i < this.choferes.size(); i++) {
			total += salarioChofer(this.choferes.get(i));
		}
		return total;
	}
	
}
