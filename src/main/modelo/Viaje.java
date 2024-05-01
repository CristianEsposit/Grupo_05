package modelo;

import java.util.ArrayList;

import excepciones.FaltaChoferException;
import excepciones.FaltaVehiculoException;

/**
 * <b>Invariante de clase: </b> El estado de un viaje es correcto si tiene un pedido(referencia no nula)<br>
 */
public class Viaje implements IViaje {
	private static double valorBase=1000;
	private Pedido pedido;
	private Chofer chofer;
	private int distancia;
	private IVehiculo vehiculo;
	private String estado="Solicitado";
	
	private boolean invariante() {
		return pedido!=null;
	}
	/**
	 * Cambia el valor base para calcular el costo del viaje<br>
	 * <b>Pre: </b>	El valor del parametro debe ser positiva <br>
	 * <b>Post: </b> El valor del atributo estado cambiara a "Solicitado" 
	 * @param valorBase valor base a utilizar
	 */
	public static void setValorBase(double valorBase) {
		Viaje.valorBase = valorBase;
	}
	/**
	 * Constructor de la clase Viaje <br>
	 * <b>Pre: </b> La distancia debera ser positiva y el pedido debio ser validado antes y distinto de null<br>
	 * <b>Post: </b> Creara una instancia de Viaje
	 * @param pedido Pedido del que se origino el viaje
	 * @param distancia Distancia total del recorrido del viaje
	 */

	public Viaje(Pedido pedido, int distancia) {
		super();
		assert distancia>-1: "La distancia debe ser positiva";
		assert pedido!=null:  "El pedido debe ser distinto de null";
		this.pedido = pedido;
		this.distancia = distancia;
		assert this!=null: "No se instancio Viaje";
		assert invariante(): "El pedido debe ser distinto de null";
	}
	
	/**
	 * Asigna el chofer al viaje<br>
	 * <b>Pre: </b> El ArrayList de Chofer debe ser distinto de null <br>
	 * <b>Post: </b> El atributo chofer obtendra la referencia del chofer asignado y el estado pasara a "Iniciado"<br>
	 * @param choferes ArrayList de Chofer para buscar el chofer a asignarle a viaje
	 * @throws FaltaChoferException Si no se encontro un chofer disponible, se dispara un excepción indicando que no hay chofer disponible
	 */
	
	public void asignarChofer(ArrayList<Chofer> choferes) throws FaltaChoferException{
		int n=choferes.size();
		assert choferes!=null: "El ArrayList debe ser distinto de null";
		if(n>0) {
			chofer=choferes.get(0);
			choferes.remove(0);
			estado="Iniciado";
			assert chofer!=null: "Chofer debio obtener la referencia del chofer asignado";
			assert estado.equalsIgnoreCase("Iniciado"): "El estado del viaje debe ser iniciado";
			assert invariante(): "El pedido debe ser disntinto de null";
		}else
			throw new FaltaChoferException("No hay chofer disponible");
		
	}
	
	/**
	 * Asigna el vehiculo al viaje<br>
	 * <b>Pre: </b> El ArrayList de Vehiculo deberá ser distinto de null<br>
	 * <b>Post: </b> El atributo vehiculo cambiará al mejor vehículo que cumpla con las condiciones del pedido<br>
	 * @param vehiculos ArrayList de Vehiculo para buscar el vehiculo a asignar al viaje
	 * @throws FaltaVehiculoException Si no hay un vehículo disponible que cumpla con los 
	 * 	                              requerimientos del pedido, se dispara un excepcion informando
	 *                                de que no hay ningún vehículo disponible que pueda cumplir con el pedido 
	 */
	public void asignarVehiculo(ArrayList<Vehiculo> vehiculos) throws FaltaVehiculoException{
		
		int i=0,n=vehiculos.size(),maxPrioridad=0;
		Vehiculo vehiculoMax=null;
		assert vehiculos!=null: "El ArrayList de Vehiculo debe ser distinto de null";
	    while(i<n) {
	    	if(vehiculos.get(i).getPrioridad(pedido)!=null && vehiculos.get(i).getPrioridad(pedido)>maxPrioridad) {
	    		maxPrioridad=vehiculos.get(i).getPrioridad(pedido);
	    		vehiculoMax=vehiculos.get(i);
	    	}
	        i++;	
	    }
	    if(vehiculoMax==null)
	    	throw new FaltaVehiculoException("No hay ningún vehículo disponible que pueda cumplir con el pedido");
	    else {
	    	vehiculo=vehiculoMax;
	    	assert vehiculo!=null: "El atributo vehiculo debe guardar la referencia del vehiculo asignado al viaje";
	    }	
	}
	/**
	 * Cambia el estado del viaje a pagado<br>
	 * <b>Post: </b> El atributo estado cambiara a "Pagado"
	 */
	public void pagar() {
		estado="Pagado";
		assert invariante(): "El pedido debe ser distinto de null";
	}
	@Override
	public double getCosto() {
		assert valorBase>0: "El valorBase debe ser positivo";
		assert invariante(): "El pedido debe ser distinto de null";
		return valorBase;
	}

	@Override
	public Pedido getPedido() {
		assert invariante(): "El pedido debe ser distinto de null";
		return this.pedido;
	}

	@Override
	public int getDistancia() {
		assert distancia>0: "La distancia debe ser positiva";
		assert invariante(): "El pedido debe ser distinto de null";
		return this.distancia;
	}
	
	public Object clone() throws CloneNotSupportedException{
		IViaje clonado=null;
		clonado=(IViaje)super.clone();
		clonado.setPedido((Pedido)this.pedido.clone());
		return clonado;
	}
	@Override
	public void setPedido(Pedido pedido) {
		this.pedido=pedido;	
	}
	@Override
	public String toString() {
		return "chofer: " + chofer + " distancia(KM): " + distancia + " vehiculo: " + vehiculo + "estado:" + estado + 
				"Caracteristicas: cantidad de pasajeros: " + this.getPedido().getCantidadPasajeros();
	}

}
