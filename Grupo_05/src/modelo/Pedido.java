package modelo;

import java.time.LocalDateTime;

import excepciones.CantidadDePasajerosException;
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
	 * <b>Pre</b> <br>
	 * fechaIngreso != null, válida.<br>
	 * zona != null <br>
	 * cantidadPasajeros>0; <br>
	 * equipajeBaul!= null ^ mascota != null
	 * 
	 * @param fechaYHora
	 * @param zona
	 * @param mascota
	 * @param cantidadPasajeros
	 * @param equipajeBaul
	 * @throws PedidoIncoherenteException
	 */
	public Pedido(LocalDateTime fechaYHora, String zona, boolean mascota, int cantidadPasajeros, boolean equipajeBaul,
			Cliente cliente) throws PedidoIncoherenteException {
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

}
