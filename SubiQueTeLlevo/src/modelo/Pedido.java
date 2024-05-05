package modelo;

import java.time.LocalDateTime;

import excepciones.CantidadDePasajerosException;
import excepciones.FechaIncorrectaException;
import excepciones.PedidoIncoherenteException;
import excepciones.ZonaInvalidaException;

public class Pedido {
	protected LocalDateTime fechaYHora;
	protected String zona; // Clase Zona?
	protected boolean mascota;
	protected int cantidadPasajeros;
	protected boolean equipajeBaul;
	protected Cliente cliente;

	/**
	 * Constructor de pedido
	 * 
	 * @param fechaYHora Fecha en la que se realizo el pedido
	 * @param zona Zona por la que pasara el viaje
	 * @param mascota Si lleva mascota
	 * @param cantidadPasajeros Cantidad de pasajeros
	 * @param equipajeBaul Si usa el baul
	 * @throws PedidoIncoherenteException Excepcion que se dispara cuando el cliente realiza un pedido que no cumple la tabla de prestaciones de vehiculos 
	 */
	public Pedido(LocalDateTime fechaYHora, String zona, boolean mascota, int cantidadPasajeros, boolean equipajeBaul,
			Cliente cliente) throws PedidoIncoherenteException {
		if(fechaYHora==null)
			throw new FechaIncorrectaException("La fecha debe ser distinta de null");
		else
		if (cantidadPasajeros > 10)
			throw new CantidadDePasajerosException("Numero de pasajeros inválido");
		else if (cantidadPasajeros > 4 && mascota)
			throw new PedidoIncoherenteException("No es posible llevar mascotas en una Combi");
		else if (zona.equalsIgnoreCase("Estandar") || zona.equalsIgnoreCase("Peligrosa")
				|| zona.equalsIgnoreCase("Calle Sin Asfaltar"))
			this.zona = zona;
		else
			throw new ZonaInvalidaException("La zona ingresada es incorrecta");
		this.fechaYHora = fechaYHora;
		this.mascota = mascota;
		this.cantidadPasajeros = cantidadPasajeros;
		this.equipajeBaul = equipajeBaul;
		this.cliente = cliente;
	}

	public LocalDateTime getFecha() {
		return fechaYHora;
	}

	public String getZona() {
		return zona;
	}

	public boolean hasMascota() {
		return mascota;
	}

	public int getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean hasEquipajeBaul() {
		return equipajeBaul;
	}
	
	public Object clone() throws CloneNotSupportedException{
		Pedido nPedido = (Pedido)super.clone();
		nPedido.cliente = (Cliente)this.cliente.clone();
		return nPedido;
	}

}
