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
import modelo.IViaje;
import modelo.Pedido;
import modelo.Vehiculo;
import modelo.Viaje;
import modelo.ViajeDecorar;
/**
 * Clase que representa al sistema. Contiene la totalidad de vehiculos, de choferes, de clientes y de los viajes que realiza la empresa.
 */
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
/////////////////////////////////////////////////////////////////////
////// ABM PARA ADMINISTRADOR
/////////////////////////////////////////////////////////////////////
	/**
	 * Agrega un cliente al sistema.<br>
	 * <b>pre: </b> El parametro cliente debe ser distinto de null.<br>
	 * <b>post: </b> Se agrega un cliente mas a la lista.<br>
	 * @param cliente : Parametro que sera agregado a la lista.
	 * @throws ClienteExistenteException : Excepcion que se lanza si el cliente ya existe en la lista.
	 */
	public void agregar(Cliente cliente) throws ClienteExistenteException {
		assert cliente != null : "cliente no valido";
		if (clientes.contains(cliente)) {
			throw new ClienteExistenteException("El cliente no puede agregarse porque ya existe.");
		} else {
			this.clientes.add(cliente);
		}
	}
	/**
	 * Modifica un cliente de la lista.<br>
	 * <b>Pre: </b> El parametro cliente debe ser distinto de null.<br>
	 * <b>Post: </b> Se modifica el cliente si existe y si no hay problema con el nombre de usuario.
	 * @param cliente : Parametro que sera modificado en la lista.
	 * @param nombre : Parametro que modifica el nomrbre real del cliente si no es null.
	 * @param usuario : Parametro que modifica el nombre de usuario del cliente si no es null.
	 * @param password : Parametro que modifica el password del cliente si no es null.
	 * @throws ClienteExistenteException : Excepcion que se lanza si quiero modificar el nombre de usuario por otro que ya esta en la lista. 
	 */
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
	/**
	 * Consulta por un cliente.<br>
	 * <b>Pre: </b> El parametro usuario no puede ser null ni vacio. <br>
	 * @param usuario : Parametro que indica el nombre de usuario por el cual se esta consultando.
	 * @return Devuelve el cliente consultado si existe y si no devuelve null.
	 */
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
	/**
	 * Agrega un vehiculo a la lista.<br>
	 * <b>Pre: </b> El parametro vehiculo no puede ser null.<br>
	 * <b>Post: </b> Se agrega un vehiculo mas a la lista.<br>
	 * @param vehiculo : Parametro que sera agregado a la lista.
	 */
	public void agregar(Vehiculo vehiculo) {
		assert vehiculo != null : "vehiculo no valido.";
		flota.add(vehiculo);
	}
	/**
	 * Modifica la patente de un vehiculo.<br>
	 * <b>Pre: </b> El parametro vehiculo no puede ser null.<br>
	 * <b>Post: </b> La patente del vechiculo se modifica si existe dicho vehiculo.
	 * @param vehiculo : Parametro que indica el vechiculo que se quiere modificar.
	 * @param patente : Parametro que modifica la patente del vehiculo.
	 */
	public void modificar(Vehiculo vehiculo, String patente) {
		assert vehiculo != null : "vehiculo no valido";
		Vehiculo v= this.flota.get(this.flota.indexOf(vehiculo));
		if (v != null) {
			v.setNroPatente(patente);
		}
	}
	/**
	 * Consulta por un vehiculo.<br>
	 * <b>Pre: </b> El parametro patente no puede ser null ni vacio.
	 * @param patente : Parametro que indica la patente del vehiculo consultado.
	 * @return Devuelve el vehiculo consultado si existe en la lista y si no devuelve null.
	 */
	public Vehiculo consultarVehiculo(String patente) {
		assert patente != null : "patente no valida.";
		assert patente != "" : "patente no valida.";
		int i = 0;
		while (i < flota.size() && !flota.get(i).getNroPatente().equals(patente)) {
			i++;
		}
		if (i < flota.size())
			return flota.get(i);
		else
			return null;
	}
	/**
	 * Agrega un chofer a la lista.<br>
	 * <b>Pre: </b> El chofer no puede ser null.<br>
	 * @param chofer : Parametro que sera agregado a la lista de choferes.
	 */
	public void agregar(Chofer chofer) {
		assert chofer != null : "chofer no valido.";
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
	/**
	 * Consulta por un chofer.<br>
	 * <b>Pre: </b> El parametro dni no puede ser null ni vacio.<br>
	 * @param dni : Parametro que indica el dni del chofer consultado.
	 * @return Devuelve el chofer consultado si existe en la lista y si no devuelve null.
	 */
	public Chofer consultarChofer(String dni) {
		assert dni != null : "dni no valido.";
		assert dni != "" : "dni no valido.";
		int i = 0;
		while (i < choferes.size() && choferes.get(i).getDni() != dni) {
			i++;
		}
		if (i < choferes.size()) // no encontro el cliente
			return choferes.get(i);
		else
			return null;
	}
	/**
	 *
	 * @return Devuelve un ArrayList de Cliente.
	 */
	public ArrayList<Cliente> listadoClientes() {
		return clientes;
	}
	/**
	 * 
	 * @return Devuelve un ArrayList de Chofer.
	 */
	public ArrayList<Chofer> listadoChoferes() {
		return choferes;
	}
	/**
	 * 
	 * @return Devuelve un ArrayList de Vehiculo.
	 */
	public ArrayList<Vehiculo> listadoVehiculos() {
		return flota;
	}
	/**
	 * Genera un listado de viajes ordenado de mayor a menor a partir de los
	 * costos de todos los viajes realizados por la empresa.
	 * @return Devuelve un ArrayList de IViaje.
	 * @throws CloneNotSupportedException
	 */
	public ArrayList<IViaje> listadoOrdenadoViaje() throws CloneNotSupportedException{
		if(this.viajes!=null && !this.viajes.isEmpty()) {
		ArrayList<IViaje> clonado=(ArrayList<IViaje>)super.clone();
		clonado.clear();
		for(int k=0;k<this.viajes.size();k++)
			clonado.add((IViaje)this.viajes.get(k).clone());
		for (int i=0; i< clonado.size();i++) {
			int menor =i;
			for (int j=i+1;j<clonado.size();j++) {
				if(clonado.get(j).getCosto()<clonado.get(menor).getCosto())
					menor=j;
			}
			IViaje aux=clonado.get(i);
			clonado.add(i, clonado.get(menor));
			clonado.add(menor, aux);
		}
		return clonado;
	  }
		else
		  throw new CloneNotSupportedException("No hay nada que clonar");
	}
	/**
	 * <b>Pre: </b> El parametro chofer no puede ser null y debe estar en la lista de choferes.
	 * @param chofer : Parametro que indica el chofer del cual se quiere conocer el sueldo.
	 * @return Devuelve el salario mensual de un chofer.
	 */
	public double salarioChofer(Chofer chofer) {
		assert chofer != null : "chofer no valido.";
		return chofer.getSueldo();
	}
	/**
	 * 
	 * @return Devuelve el dinero total necesario para pagar los salarios del mes.
	 */
	public double totalDineroNecesario() {
		double total = 0;
		for (int i = 0; i < this.choferes.size(); i++) {
			total += salarioChofer(this.choferes.get(i));
		}
		return total;
	}

/////////////////////////////////////////////////////////////////////
//////LOGICA DE VIAJES
/////////////////////////////////////////////////////////////////////
	/**
	 * Realiza el pedido confeccionado por el cleinte.<br>
	 * <b>Pre: </b> El parametro fechaYHora debe ser distinto de null.
	 * El parametro zona debe ser distinto de null.
	 * El parametro cantPasajeros debe ser mayor a 0.
	 * El cliente debe ser un cliente existente y distinto de null.
	 * La distancia debe ser mayor a 0.<br>
	 * <b>Post: </b> Si se cumple con los requisitos el pedido se realiza y 
	 * se solicita un viaje, asignando un vehiculo y un chofer. Luego se
	 * agrega el viaje a la lista de viajes de la empresa y a la lista
	 * individual del cliente. Si hay algun inconveniente a la hora de confeccionar
	 * el pedido o de armar el viaje se informa con la excepcion correspondiente.<br>
	 * @param fechaYHora : Parametro que indica la fecha y la hora en que se hace el pedido.
	 * @param zona : Parametro que indica la zona.
	 * @param mascota : Parametro que indica si desea viajar con mascota o no.
	 * @param cantPasajeros : Parametro que indica la cantidad de pasajeros en el viaje.
	 * @param equipajeBaul : Parametro que indica si requiere baul o no.
	 * @param cliente : Parametro que indica el cliente que realiza el pedido.
	 * @param distancia : Parametro que indica la distancia del viaje.
	 */
	public void realizarPedido(LocalDateTime fechaYHora, String zona, boolean mascota, int cantPasajeros,
			boolean equipajeBaul, Cliente cliente, int distancia) {
		assert fechaYHora != null : "fecha invalida";
		assert zona != null : "zona no puede ser null";
		assert cantPasajeros > 0 : "cantidad de pasajeros > a 0";
		assert cliente != null : "cliente no valido";
		assert distancia > 0 : "distancia no valida.";
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
		Integer prioridad = null;
		for(int i = 0; i<flota.size(); i++) {
			prioridad = flota.get(i).getPrioridad(viaje.getPedido());
            if(prioridad != null && prioridad > prioridadMax) {
                prioridadMax = prioridad;
                prioritario = flota.get(i);
            }
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
	 * <b>Pre: </b> El parametro viaje no puede ser null.<br>
	 * <b>Post: </b> El atributo estado cambiara a "Pagado"<br>
	 * @param viaje : Parametro que indica el viaje que sera pagado.
	 */
	public void pagar(IViaje viaje) {
		assert viaje != null : "El viaje no es valido.";
		viaje.setEstado("Pagado");
	}
	/**
	 * Cambia el estado del viaje a finalizado y agrega al chofer a la lista de
	 * choferes y al vehiculo a la lista de vehiculos. Tambien califica
	 * al chofer que realizo el viaje.<br>
	 * <b>Pre </b> El parametro viaje no puede ser null.<br> 
	 * @param viaje : Parametro que indica el viaje que sera finalizado.
	 */
	public void finalizar(IViaje viaje) {
		assert viaje != null : "El viaje no es valido.";
		viaje.setEstado("Finalizado");
		this.choferes.add(viaje.getChofer());
		this.flota.add(viaje.getVehiculo());
		calificarChofer(viaje.getChofer(),(int)(Math.random() * 10));
	}
	/**
	 * Califica al chofer que realiza un viaje.
	 * <b>Pre: </b> El parametro chofer no puede ser null.<br>
	 * @param chofer : Parametro que indica el chofer que se quiere calificar.
	 * @param puntaje : Puntaje que se le quiere dar al chofer.
	 */
	public void calificarChofer(Chofer chofer, int puntaje) {
		int puntajeAct = chofer.getPuntaje();
		chofer.setPuntaje(puntajeAct + puntaje);
	}
	/**
	 * Visualiza los viajes que realizo un cliente determinado.<br>
	 * <b>Pre: </b> El parametro cliente no puede ser null y debe ser un cliente
	 * que este en la lista de cliente.<br>
	 * @param cliente : Parametro que indica el cliente del cual quiere verse los
	 * viajes realizados.
	 */
	public void verHistorialDeViaje(Cliente cliente) {
		assert cliente != null : "El cliente no es valido.";
		ArrayList<IViaje> viajesCliente = cliente.getViajes();
		for (int i = 0; i < viajesCliente.size(); i++) {
			System.out.println(viajesCliente.get(i));
		}
	}
	/**
	 * Solicita un viaje a partir de un pedido aceptado.<br>
	 * <b>Pre: </b> El parametro pedido no puede ser null. La distancia debe
	 * ser mayor a 0.<br>
	 * @param pedido : Parametro que indica el pedido con el cual se solicita el
	 * viaje.
	 * @param distancia : Parametro que indica la distancia del viaje.
	 * @return Devuelve el viaje confeccionado.
	 */
	public IViaje solicitarViaje(Pedido pedido, int distancia) {
		return ViajeDecorar.agregarCapas(pedido, distancia);
	}
	/**
	 * Calcula los puntajes de todos los choferes.<br>
	 * @param choferes : Parametro que contiene a todos los choferes de la empresa.
	 */
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
	/**
	 * Genera el reporte de clientes.<br>
	 * @return Devuelve un ArrayList de String con la informacion
	 *  de todos los clientes. Si no hay clientes devuelve un ArrayList vacio.
	 */
	public ArrayList<String> reporteCliente() {
		ArrayList<String> reporte = new ArrayList<String>();
		
		for(int i = 0;i < this.clientes.size(); i++) {
			reporte.add(this.clientes.get(i).toString());
		}
		return reporte;
	}
	/**
	 * Genera el reporte de los salarios pagados a cada chofer en el mes.<br>
	 * @return Devuelve un ArrayList de String con los datos de cada chofer
	 * y con el sueldo que se le pago.
	 * Si no hay clientes devuelve un ArrayList vacio.
	 */
	public ArrayList<String> reporteSalarios() {
		ArrayList<String> reporte = new ArrayList<String>();
		
		for(int i = 0;i < this.choferes.size(); i++) {
			Chofer chofer = this.choferes.get(i);
			reporte.add(chofer.getDni() + " " +  chofer.getNombre() + " " + chofer.getSueldo());
		}
		
		return reporte;
	}
	/**
	 * Genera el reporte de los viajes realizados por un chofer en un periodo de
	 * dias.<br>
	 * <b>Pre: </b> El parametro chofer no puede ser null.
	 * El parametro inicio y fin no puden ser null.
	 * @param chofer : Parametro que indica el chofer del cual quiero generar el
	 * reporte de los viajes realizados.
	 * @param inicio : Fecha de inicio del periodo de dias del que quiero
	 * obtener los viajes.
	 * @param fin : Fecha de finalizacion del periodo de dias del que
	 * quiero obtener los viajes.
	 * @return Devuelve un ArrayList de String con los viajes que relizo el chofer
	 * en ese periodo de dias.
	 * Si no hizo viajes en ese periodo de dias devuelve un ArrayList vacio.
	 */
	public ArrayList<String> reporteViajesPorChofer(Chofer chofer,LocalDateTime inicio,LocalDateTime fin) {
		assert chofer != null : "chofer no valido.";
		assert inicio != null : "fecha no valida.";
		assert fin != null : "fecha no valida.";
		ArrayList<String> reporte = new ArrayList<String>();
		ArrayList<IViaje> viajes = chofer.getViajes();
		int i = 0;
		while(viajes != null && i<viajes.size() && viajes.get(i).getPedido().getFecha().isBefore(fin)){
			if(viajes.get(i).getPedido().getFecha().isAfter(inicio)) {				
				reporte.add(viajes.get(i).toString());
			}
			i++;
		}
		return reporte;
	}
	/**
	 * Genera el reporte de los viajes realizados por un cliente en un periodo de
	 * dias.<br>
	 * <b>Pre: </b> El parametro cliente no puede ser null.
	 * El parametro inicio y fin no puden ser null.
	 * @param cliente : Parametro que indica el cliente del cual quiero generar el
	 * reporte de los viajes realizados.
	 * @param inicio : Fecha de inicio del periodo de dias del que quiero
	 * obtener los viajes.
	 * @param fin : Fecha de finalizacion del periodo de dias del que
	 * quiero obtener los viajes.
	 * @return Devuelve un ArrayList de String con los viajes que relizo el cliente
	 * en ese periodo de dias.
	 * Si no hizo viajes en ese periodo de dias devuelve un ArrayList vacio.
	 */
	public ArrayList<String> reporteViajesPorCliente(Cliente cliente,LocalDateTime inicio,LocalDateTime fin) {
		assert cliente != null : "chofer no valido.";
		assert inicio != null : "fecha no valida.";
		assert fin != null : "fecha no valida.";
		ArrayList<String> reporte = new ArrayList<String>();
		ArrayList<IViaje> viajes = cliente.getViajes();
		int i = 0;
		while(viajes != null && i<viajes.size() && viajes.get(i).getPedido().getFecha().isBefore(fin)){
			if(viajes.get(i).getPedido().getFecha().isAfter(inicio)) {				
				reporte.add(viajes.get(i).toString());
			}
			i++;
		}
		return reporte;
	}

}
