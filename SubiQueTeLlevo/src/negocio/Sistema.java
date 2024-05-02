package negocio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import excepciones.CantidadDePasajerosException;
import excepciones.ClienteExistenteException;
import excepciones.FaltaChoferException;
import excepciones.FaltaVehiculoException;
import excepciones.PedidoIncoherenteException;
import excepciones.ZonaInvalidaException;
import modelo.Chofer;
import modelo.Cliente;
import modelo.IVehiculo;
import modelo.IViaje;
import modelo.Pedido;
import modelo.Vehiculo;
import modelo.Viaje;
import modelo.ViajeDecorar;

public class Sistema {
	private static Sistema instance = null;
	private ArrayList<Vehiculo> flota = new ArrayList<Vehiculo>();
	protected ArrayList<Chofer> choferes = new ArrayList<Chofer>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<IViaje> viajes = new ArrayList<IViaje>();

	private Sistema() {

	}

	public static Sistema getInstance() {
		if (Sistema.instance == null)
			Sistema.instance = new Sistema();
		return instance;
	}

	// ABM para administrador

	public void agregar(Cliente cliente) throws ClienteExistenteException {
		assert cliente != null : "cliente invalido";
		if (clientes.contains(cliente)) {
			throw new ClienteExistenteException("El cliente no puede agregarse porque ya existe.");
		} else {
			this.clientes.add(cliente);
		}
	}

	public void modificar(Cliente cliente, String nombre, String usuario, String password)
			throws ClienteExistenteException { // que quiero modificar
		assert cliente != null : "cliente no valido";
		if (clientes.contains(cliente)) {
			Cliente c = clientes.get(clientes.indexOf(cliente));
			if (nombre != null)
				c.setNombreReal(nombre);
			if (usuario != null) {
				int i = 0;
				while (i < clientes.size() && clientes.get(i).getNombreUsuario() != usuario) {
					i++;
				}
				if (!(i < clientes.size())) // no encontro el cliente
					c.setNombreUsuario(usuario);
				else {
					throw new ClienteExistenteException("El nombre de usuario ya existe");
				}
			}
			if (password != null)
				c.setPassword(password);
		}
	}

	public Cliente consultarCliente(String usuario) {
		int i = 0;
		while (i < clientes.size() && clientes.get(i).getNombreUsuario() != usuario) {
			i++;
		}
		if (i < clientes.size()) // no encontro el cliente
			return clientes.get(i);
		else
			return null;
	}

	public void agregar(Vehiculo vehiculo) {
		assert vehiculo != null : "vehiculo invalido";
		flota.add(vehiculo);
	}

	public void modificar(Vehiculo vehiculo, String patente) {
		assert vehiculo != null : "vehiculo no valido";
		Vehiculo v = flota.get(patente);
		if (v != null) {
			v.setNroPatente(patente);
		}
	}

	public Vehiculo consultarVehiculo(String patente) {
		int i = 0;
		while (i < flota.size() && !flota.get(i).getNroPatente().equals(patente)) {
			i++;
		}
		if (i < flota.size()) // no encontro el cliente
			return flota.get(i);
		else
			return null;
	}

	public void agregar(Chofer chofer) {
		if (!this.choferes.contains(chofer)) {
			this.choferes.add(chofer);
		}
	}

	/*
	 * public void modificarChofer(ChoferTemprario chofer, String nombre, String
	 * dni) {
	 * 
	 * }
	 */

	public Chofer consultarChofer(String dni) {
		int i = 0;
		while (i < choferes.size() && choferes.get(i).getDni() != dni) {
			i++;
		}
		if (i < choferes.size()) // no encontro el cliente
			return choferes.get(i);
		else
			return null;
	}

	public ArrayList<Cliente> listadoClientes() {
		return clientes;
	}

	public ArrayList<Chofer> listadoChoferes() {
		return choferes;
	}

	public ArrayList<Vehiculo> listadoVehiculos() {
		return flota;
	}

	public double salarioChofer(Chofer chofer) { // devuelve el salario mensual de un chofer
		return chofer.getSueldo();
	}

	public double totalDineroNecesario() { // devuelve el total necesario para pagar los salarios
		double total = 0;
		for (int i = 0; i < this.choferes.size(); i++) {
			total += salarioChofer(this.choferes.get(i));
		}
		return total;
	}

	// Logica de viajes

	public void realizarPedido(LocalDateTime fechaYHora, String zona, boolean mascota, int cantPasajeros,
			boolean equipajeBaul, Cliente cliente, int distancia) {
		assert fechaYHora != null : "fecha invalida";
		assert zona != null : "zona no puede ser null";
		assert cantPasajeros > 0 : "cantidad de pasajeros > a 0";
		assert cliente != null : "cliente no valido";
		Pedido pedido = null;
		try {
			pedido = new Pedido(fechaYHora, zona, mascota, cantPasajeros, equipajeBaul, cliente);
			IViaje viaje = solicitarViaje(pedido, distancia);
			this.asignarVehiculo(this.flota, viaje);
			this.asignarChofer(this.choferes, viaje);
			this.viajes.add(viaje);
			cliente.agregaViaje(viaje);
		} catch(FaltaChoferException e){
			System.out.println(e.getMessage());
		} catch (FaltaVehiculoException e) {
			System.out.println(e.getMessage());
		} catch (CantidadDePasajerosException e) {
			System.out.println(e.getMessage());
		} catch (ZonaInvalidaException e) {
			System.out.println(e.getMessage());
		} catch (PedidoIncoherenteException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Asigna el chofer al viaje<br>
	 * <b>Pre: </b> El ArrayList de Chofer debe ser distinto de null <br>
	 * <b>Post: </b> El atributo chofer obtendra la referencia del chofer asignado y
	 * el estado pasara a "Iniciado"<br>
	 * 
	 * @param choferes ArrayList de Chofer para buscar el chofer a asignarle a viaje
	 * @throws FaltaChoferException Si no se encontro un chofer disponible, se
	 *                              dispara un excepción indicando que no hay chofer
	 *                              disponible
	 */

	public void asignarChofer(ArrayList<Chofer> choferes, IViaje viaje) throws FaltaChoferException {
		int n = choferes.size();
		Chofer chofer;
		assert choferes != null : "El ArrayList debe ser distinto de null";
		if (n > 0) {
			chofer = choferes.get(0);
			viaje.setChofer(chofer);
			chofer.agregarViaje(viaje);
			choferes.remove(0);
			viaje.setEstado("Iniciado");
			
			assert choferes.get(0) != null : "Chofer debio obtener la referencia del chofer asignado";
			assert viaje.getEstado().equalsIgnoreCase("Iniciado") : "El estado del viaje debe ser iniciado";
		} else
			throw new FaltaChoferException("No hay chofer disponible");
	}

	/**
	 * Asigna el vehiculo al viaje<br>
	 * <b>Pre: </b> El ArrayList de Vehiculo deberá ser distinto de null<br>
	 * <b>Post: </b> El atributo vehiculo cambiará al mejor vehículo que cumpla con
	 * las condiciones del pedido<br>
	 * 
	 * @param vehiculos ArrayList de Vehiculo para buscar el vehiculo a asignar al
	 *                  viaje
	 * @throws FaltaVehiculoException Si no hay un vehículo disponible que cumpla
	 *                                con los requerimientos del pedido, se dispara
	 *                                un excepcion informando de que no hay ningún
	 *                                vehículo disponible que pueda cumplir con el
	 *                                pedido
	 */

	public void asignarVehiculo(ArrayList<Vehiculo> vehiculos, IViaje viaje) throws FaltaVehiculoException {
		assert vehiculos != null : "El HashMap de Vehiculo debe ser distinto de null";
		Integer prioridadMax = null;
		Vehiculo prioritario = null;
		Integer prioridad;
		int i = 0;
		while(i<flota.size()) {
            if(flota.get(i).getPrioridad(viaje.getPedido()) != null && flota.get(i).getPrioridad(viaje.getPedido()) > prioridadMax) {
                prioridadMax = flota.get(i).getPrioridad(viaje.getPedido());
                prioritario = flota.get(i);
            }
            i++;
        }

		if (prioritario == null)
			throw new FaltaVehiculoException("No hay ningún vehículo disponible que pueda cumplir con el pedido");
		else {
			viaje.setVehiculo(prioritario);
			viaje.setEstado("Con vehiculo");
			vehiculos.remove(vehiculos.indexOf(prioritario));
			assert viaje.getVehiculo() != null
					: "El atributo vehiculo debe guardar la referencia del vehiculo asignado al viaje";
		}
	}

	/**
	 * Cambia el estado del viaje a pagado<br>
	 * <b>Post: </b> El atributo estado cambiara a "Pagado"
	 */
	public void pagar(IViaje viaje) {
		viaje.setEstado("Pagado");
	}
	
	public void finalizar(IViaje viaje) {
		viaje.setEstado("Finalizado");
		this.choferes.add(viaje.getChofer());
		this.flota.add(viaje.getVehiculo());
	}

	public void calificarChofer(Chofer chofer, int puntaje) {
		int puntajeAct = chofer.getPuntaje();
		chofer.setPuntaje(puntajeAct + puntaje);
	}

	public void verHistorialDeViaje(Cliente cliente) { // historial de un cliente especifico
		ArrayList<IViaje> viajesCliente = cliente.getViajes();
		for (int i = 0; i < viajesCliente.size(); i++) {
			System.out.println(viajesCliente.get(i));
		}
	}

	public IViaje solicitarViaje(Pedido pedido, int distancia) {
		return ViajeDecorar.agregarCapas(pedido, distancia);
	}

	public void calcularPuntajes(ArrayList<Chofer> choferes) {
		ArrayList<IViaje> viajes;
		int kms;
		int maxKms = 0;
		int indexMaxChofer = -1;
		int cantViajes;
		for(int i = 0;i < choferes.size(); i++) {
			viajes = choferes.get(i).getViajes();
			kms = 0;
			cantViajes = 0;
			for(int j = 0;j < viajes.size();j++) {
				kms += viajes.get(j).getDistancia();
				cantViajes++;
			}
			if(kms > maxKms) {
				maxKms = kms;
				indexMaxChofer = i;
			}
			choferes.get(i).setPuntaje(choferes.get(i).getPuntaje() + cantViajes*5);
		}
		choferes.get(indexMaxChofer).setPuntaje(choferes.get(indexMaxChofer).getPuntaje() + 15);
	}
	
	public ArrayList<String> reporteCliente() {
		ArrayList<String> reporte = new ArrayList<String>();
		
		for(int i = 0;i < this.clientes.size(); i++) {
			reporte.add(this.clientes.get(i).toString());
		}
		
		return reporte;
	}
	
	public ArrayList<String> reporteSalarios() {
		ArrayList<String> reporte = new ArrayList<String>();
		
		for(int i = 0;i < this.choferes.size(); i++) {
			Chofer chofer = this.choferes.get(i);
			reporte.add(chofer.getDni() + " " +  chofer.getNombre() + " " + chofer.getSueldo());
		}
		
		return reporte;
	}
	
	public ArrayList<String> reporteViajesPorChofer(Chofer chofer,LocalDateTime inicio,LocalDateTime fin) {
		ArrayList<String> reporte = new ArrayList<String>();
		ArrayList<IViaje> viajes = chofer.getViajes();
		int i = 0;
		while(this.viajes != null && i<this.viajes.size() && this.viajes.get(i).getPedido().getFecha().isBefore(fin)){
			if(this.viajes.get(i).getPedido().getFecha().isAfter(inicio)) {				
				reporte.add(this.viajes.get(i).toString());
			}
			i++;
		}
		return reporte;
	}
	
	public ArrayList<String> reporteViajesPorCliente(Cliente cliente,LocalDateTime inicio,LocalDateTime fin) {
		ArrayList<String> reporte = new ArrayList<String>();
		ArrayList<IViaje> viajes = cliente.getViajes();
		int i = 0;
		while(this.viajes != null && i<this.viajes.size() && this.viajes.get(i).getPedido().getFecha().isBefore(fin)){
			if(this.viajes.get(i).getPedido().getFecha().isAfter(inicio)) {				
				reporte.add(this.viajes.get(i).toString());
			}
			i++;
		}
		return reporte;
	}
	
}
