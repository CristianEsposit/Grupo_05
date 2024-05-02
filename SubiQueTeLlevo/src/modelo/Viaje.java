package modelo;

import java.util.ArrayList;
import java.util.HashMap;

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
