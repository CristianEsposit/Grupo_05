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
	private static int contClientesActivos;
	private static int contChoferesActivos;
	private ArrayList<IViaje> viajesActivos = new ArrayList<IViaje>();
	private ArrayList<Chofer> choferesDisponibles = new ArrayList<Chofer>(); //es necesario porque el Array del sistema guarda el historico de choferes
	private Sistema sistema;
	
	public RecursoCompartido(int choferes, int clientes) {
		contChoferesActivos = choferes;
		contClientesActivos = clientes;
	}
	/**
	 * Procesa e inicializa un pedido solicitado por un cliente.<br>
	 * Constructor de pedido
	 * @param fechaYHora Fecha en la que se realizo el pedido
	 * @param zona Zona por la que pasara el viaje
	 * @param mascota Si lleva mascota
	 * @param cantidadPasajeros Cantidad de pasajeros
	 * @param equipajeBaul Si usa el baul
	*/
	public void realizarPedido(LocalDateTime fechaYHora, String zona, boolean mascota, int cantPasajeros,
			boolean equipajeBaul, Cliente cliente, int distancia) {
		Pedido pedidoAct = null;
		if (contChoferesActivos > 0) {
			try {
				pedidoAct= this.sistema.realizarPedido(fechaYHora, zona, mascota, cantPasajeros, equipajeBaul, cliente, distancia);
				String cartel = cliente.getNombreReal() + "realizo un pedido valido";
				setChanged();
				notifyObservers(cartel);
				solicitarViaje(pedidoAct, distancia);
			} catch (PedidoIncoherenteException e) {
				//IMPRIMIR POR VENTANA
				e.printStackTrace();
			}
		}
	}
	/**
	 * activa y solicita un viaje.
	 * @param pedido a partir del cual se armará el viaje
	 * @param distancia a recorrer durante el viaje
	 */
	private synchronized void solicitarViaje(Pedido pedido, int distancia) {
		IViaje viajeAct = this.sistema.solicitarViaje(pedido, distancia); //devuelve un viaje en estado solicitado
		this.viajesActivos.add(viajeAct); //aca se genero el viaje solicitado a partir del pedido valido
	}
	
	/**
	 * 
	 * @return El indice del viaje dentro del arreglo de viajes activos con Vehiculo asignado
	 */
	private int posicionViajeConVehiculo() {
		int i = 0;
		while (i < this.viajesActivos.size() && !this.viajesActivos.get(i).getEstado().equalsIgnoreCase("con vehiculo")) {
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
	public synchronized void agarraViaje() {
		IViaje viajeAct = null;
		int posicionViaje;
		posicionViaje = this.posicionViajeConVehiculo();
		while (contClientesActivos > 0 && posicionViaje < 0 /*&& falta considerar al cliente usuario*/) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if ((contClientesActivos > 0/*|| hay un usuario activo*/) && posicionViaje >= 0) {
			try {
				this.sistema.asignarChofer(this.choferesDisponibles, viajeAct);
			} catch (FaltaChoferException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
	}
	
	/**
	 * 
	 * @return El indice del viaje dentro del arreglo de viajes activos en estado Solicitado
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
		IViaje viajeAct = null;
		int posicionViaje;
		posicionViaje = this.posicionViajeSolicitado();
		if (posicionViaje >= 0 && !this.sistema.listadoChoferes().isEmpty()) { //no se si esta bien que refiera a ese listado de vehiculos
			viajeAct = this.viajesActivos.get(posicionViaje);
			try { //este try y catch no estoy seguro de si va aca
				this.sistema.asignarVehiculo(vehiculosDisponibles, viajeAct);
			} catch (FaltaVehiculoException e) {
				e.printStackTrace();
				//aca hay que notificar a los ojos asi comunican lo que paso por las ventanas
			}
		}
	}
	/**
	 * Establece un Viaje como pagado<br>
	 * Llamado por ClienteThread
	 */
	public void pagarViaje() {
		
	}
	
	/**
	 * Establece un Viaje como Finalizado 
	 * Llamado por ChoferThread
	 */
	public void finalizaViaje() {
		
	}
	/**
	 * Agrega un Chofer a la lista de Disponibles
	 * @param chofer Chofer que ingresa (o reingresa) como disponible
	 */
	public void addChofer(Chofer chofer) {
		this.choferesDisponibles.add(chofer);
	}
	

	public static int getContClientesActivos() {
		return contClientesActivos;
	}

	public static void setContClientesActivos(int contClientesActivos) {
		RecursoCompartido.contClientesActivos = contClientesActivos;
	}

	public static void setContChoferesActivos(int contChoferesActivos) {
		RecursoCompartido.contChoferesActivos = contChoferesActivos;
	}

	public static int getContChoferesActivos() {
		return contChoferesActivos;
	}

	public ArrayList<Chofer> getChoferesDisponibles() {
		return choferesDisponibles;
	}
	
}
