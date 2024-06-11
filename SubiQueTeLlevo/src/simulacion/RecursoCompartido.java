package simulacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;

import excepciones.FaltaChoferException;
import excepciones.FaltaVehiculoException;
import excepciones.PedidoIncoherenteException;
import modelo.Chofer;
import modelo.Cliente;
import modelo.IViaje;
import modelo.Pedido;
import modelo.Vehiculo;
import negocio.Sistema;

/**
 * Monitor
 */
public class RecursoCompartido extends Observable {
	private  int contClientesActivos;
	private  int contChoferesActivos;
	private  int contClientesHumanosActivos;
	private ArrayList<IViaje> viajesActivos = new ArrayList<IViaje>();
	private ArrayList<Chofer> choferesDisponibles = new ArrayList<Chofer>(); // es necesario porque el Array del sistema
																				// guarda el historico de choferes
	private Sistema sistema;

	public RecursoCompartido(Sistema sistema, int clientes, int choferes) {
		this.contChoferesActivos = choferes;
		this.contClientesActivos = clientes;
		this.sistema = sistema;
		this.contClientesHumanosActivos = 0;
	}

	/**
	 * Procesa e inicializa un pedido solicitado por un cliente.<br>
	 * Constructor de pedido
	 * 
	 * @param fechaYHora        Fecha en la que se realizo el pedido
	 * @param zona              Zona por la que pasara el viaje
	 * @param mascota           Si lleva mascota
	 * @param cantidadPasajeros Cantidad de pasajeros
	 * @param equipajeBaul      Si usa el baul
	 */

	public synchronized void realizarPedido(LocalDateTime fechaYHora, String zona, boolean mascota, int cantPasajeros,
			boolean equipajeBaul, Cliente cliente, int distancia) {
		Pedido pedidoAct = null;
		String cartel;
		if (this.contChoferesActivos > 0) {
			try {
				pedidoAct = this.sistema.realizarPedido(fechaYHora, zona, mascota, cantPasajeros, equipajeBaul, cliente,
						distancia);
				cartel = cliente.getNombreReal() + " realizo un pedido valido.";
				cliente.setPedidoValido(true);
				setChanged();
				notifyObservers(cartel);

				solicitarViaje(pedidoAct, distancia);
			} catch (PedidoIncoherenteException e) {
				cartel = cliente.getNombreReal() + " realizo un pedido que no es valido." + e.getMessage();
				setChanged();
				notifyObservers(cartel);
			}
		} else {
			cartel = "No se pueden realizar mas pedido porque no hay mas choferes disponibles.";
			setChanged();
			notifyObservers(cartel);
		}
		notifyAll();
	}

	/**
	 * activa y solicita un viaje.
	 * 
	 * @param pedido    a partir del cual se armará el viaje
	 * @param distancia a recorrer durante el viaje
	 */

	private synchronized void solicitarViaje(Pedido pedido, int distancia) {
		String cartel;
		while (this.contChoferesActivos > 0 && this.choferesDisponibles.isEmpty())
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		if (this.contChoferesActivos > 0) {
			IViaje viajeAct = this.sistema.solicitarViaje(pedido, distancia); // devuelve un viaje
			viajeAct.setEstado("Solicitado");
			this.viajesActivos.add(viajeAct); // aca se genero el viaje solicitado a partir del pedido valido
			cartel = viajeAct.getPedido().getCliente().getNombreReal() + " solicito un viaje y fue aceptado.";
		} else
			cartel = pedido.getCliente().getNombreReal()
					+ " solicito un viaje pero no se puede realizar porque no hay choferes trabajando.";
		setChanged();
		notifyObservers(cartel);
		notifyAll();
	}

	/**
	 * 
	 * @return El indice del viaje dentro del arreglo de viajes activos con Vehiculo
	 *         asignado
	 */

	private int posicionViajeConVehiculo() {
		int i = 0;
		while (i < this.viajesActivos.size()
				&& !this.viajesActivos.get(i).getEstado().equalsIgnoreCase("con vehiculo")) {
			i++;
		}
		if (i < this.viajesActivos.size())
			return i;
		else
			return -1;
	}

	/**
	 * Asigna un Chofer a un Viaje. <br>
	 * Llamado por ChoferThreads.
	 */
	public synchronized void agarraViaje(ChoferThread c) {
		String cartel;
		IViaje viajeAct = null;
		int posicionViaje;
		posicionViaje = this.posicionViajeConVehiculo();
		System.out.println("pos " + posicionViaje);
		while ((this.contClientesActivos > 0 || this.contClientesHumanosActivos > 0) && posicionViaje < 0) {
			try {
				wait();
				posicionViaje = this.posicionViajeConVehiculo();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// System.out.println("pos " + posicionViaje);
		if ((this.contClientesActivos > 0 || this.contClientesHumanosActivos > 0) && posicionViaje >= 0) {
			try {
				viajeAct = this.viajesActivos.get(posicionViaje);
				this.sistema.asignarChofer(c.getChofer(), viajeAct);
				this.choferesDisponibles.remove(c.getChofer());
				c.setAgarroViaje(true);
				cartel = "El chofer " + viajeAct.getChofer().getNombre() + " tomo el viaje del cliente "
						+ viajeAct.getPedido().getCliente().getNombreReal() + " y esta Iniciado";
			} catch (FaltaChoferException e) {
				cartel = "En este momento no hay choferes disponibles para el viaje del cliente "
						+ viajeAct.getPedido().getCliente().getNombreReal();
				setChanged();
				notifyObservers(cartel);
			}
		} else
			cartel = "Ya no existen viajes por tomar porque ya no hay mas clientes.";
		setChanged();
		notifyObservers(cartel);
		notifyAll();
	}

	/**
	 * 
	 * @return El indice del viaje dentro del arreglo de viajes activos en estado
	 *         Solicitado
	 */

	private int posicionViajeSolicitado() {
		int i = 0;
		while (i < this.viajesActivos.size() && !this.viajesActivos.get(i).getEstado().equalsIgnoreCase("solicitado")) {
			i++;
		}
		if (i < this.viajesActivos.size())
			return i;
		else
			return -1;
	}

	public synchronized void asignarVehiculo(ArrayList<Vehiculo> vehiculosDisponibles) {
		String cartel;
		IViaje viajeAct = null;
		int posicionViaje;
		posicionViaje = this.posicionViajeSolicitado();

		if (posicionViaje >= 0) {
			viajeAct = this.viajesActivos.get(posicionViaje);
			if (!vehiculosDisponibles.isEmpty()) {
				try {
					this.sistema.asignarVehiculo(vehiculosDisponibles, viajeAct);
					System.out.println(viajeAct.getEstado());
					cartel = "Al viaje del cliente " + viajeAct.getPedido().getCliente().getNombreReal()
							+ " se le asigno el vehiculo " + viajeAct.getVehiculo();
				} catch (FaltaVehiculoException e) {
					cartel = "No hay vehiculos para satisfacer el viaje del cliente "
							+ viajeAct.getPedido().getCliente().getNombreReal();
					setChanged();
					notifyObservers(cartel);
				}
			} else
				cartel = "No contamos con vehiculos en este momento para el viaje del cliente "
						+ viajeAct.getPedido().getCliente().getNombreReal();
			setChanged();
			notifyObservers(cartel);
		}
		notifyAll();
	}

	private int posicionViajeIniciado(Cliente c) {
		int i = 0;
		while (i < this.viajesActivos.size() && !this.viajesActivos.get(i).getPedido().getCliente().equals(c)) {
			i++;
		}
		if (i < this.viajesActivos.size() && this.viajesActivos.get(i).getEstado().equalsIgnoreCase("iniciado"))
			return i;
		else
			return -1;
	}

	/**
	 * Establece un Viaje como pagado<br>
	 * Llamado por ClienteThread
	 */
	public synchronized void pagarViaje(Cliente c) {
		String cartel = null;
		int i = 0;
		int posViajeIniciado = posicionViajeIniciado(c);
		IViaje viajeAct;
		// nuevo
		if (c.isPedidoValido()) {
			while (posViajeIniciado < 0 && this.contChoferesActivos > 0) {
				try {
					wait();
					posViajeIniciado = posicionViajeIniciado(c);
				} catch (InterruptedException e) {

				}
			}
			if (posViajeIniciado >= 0) {
				System.out.println(this.viajesActivos.get(posViajeIniciado));
				this.sistema.pagar(this.viajesActivos.get(posViajeIniciado));
				cartel = "El cliente " + c.getNombreReal() + " paga el viaje al chofer "
						+ this.viajesActivos.get(posViajeIniciado).getChofer().getNombre();
			} else {
				cartel = "El viaje del cliente " + c.getNombreReal()
						+ " no se pudo realizar debido a que ya no hay choferes.";
			}
		}
		else
			cartel = "El pedido realizado por el cliente " + c.getNombreReal() + " no es valido.";
		notifyAll();
		setChanged();
		notifyObservers(cartel);
	}

	private int posicionViajePagado(Chofer c) {
		int i = 0;
		while (i < this.viajesActivos.size() && !this.viajesActivos.get(i).getChofer().equals(c)) {
			i++;
		}
		if (i < this.viajesActivos.size() && this.viajesActivos.get(i).getEstado().equalsIgnoreCase("pagado"))
			return i;
		else
			return -1;
	}

	/**
	 * Establece un Viaje como Finalizado Llamado por ChoferThread
	 */

	public synchronized void finalizaViaje(Chofer c) {
		String cartel;
		int posViajePagado = posicionViajePagado(c);
		while (posViajePagado < 0 && (this.contClientesActivos > 0 || this.contClientesHumanosActivos > 0)) {
			try {
				wait();
				posViajePagado = posicionViajePagado(c);
			} catch (InterruptedException e) {

			}
		}
		if (posViajePagado >= 0) {
			notifyAll();
			this.sistema.finalizar(this.viajesActivos.get(posViajePagado));
			this.addChofer(c);
			SistemaThread.agregaVehiculo(this.viajesActivos.get(posViajePagado).getVehiculo());
			cartel = "El chofer " + c.getNombre() + " finaliza el viaje del cliente "
					+ this.viajesActivos.get(posViajePagado).getPedido().getCliente().getNombreReal();
		} else
			cartel = "Ya no hay mas clientes solicitando viajes.";
		notifyAll();
		setChanged();
		notifyObservers(cartel);
	}

	/**
	 * Agrega un Chofer a la lista de Disponibles
	 * 
	 * @param chofer Chofer que ingresa (o reingresa) como disponible
	 */
	public void addChofer(Chofer chofer) {
		this.choferesDisponibles.add(chofer);
	}

	public int getContClientesActivos() {
		return this.contClientesActivos;
	}

	public void setContClientesActivos(int contClientesActivos) {
		this.contClientesActivos = contClientesActivos;
	}

	public void setContChoferesActivos(int contChoferesActivos) {
		this.contChoferesActivos = contChoferesActivos;
	}

	public int getContChoferesActivos() {
		return this.contChoferesActivos;
	}

	public ArrayList<Chofer> getChoferesDisponibles() {
		return this.choferesDisponibles;
	}
	
	public synchronized void reduceClientesThread() {
		this.contClientesActivos--;
		if (this.contClientesActivos == 0) {
			setChanged();
			notifyObservers("Ya se fueron todos los clientes robots.");
		}
	}
	
	public synchronized void reduceChoferesThread() {
		this.contChoferesActivos--;
		if (this.contChoferesActivos == 0) {
			setChanged();
			notifyObservers("Ya se fueron todos los choferes robots.");
		}
	}

}
