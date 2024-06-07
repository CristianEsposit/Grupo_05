package persistencia;

import java.util.ArrayList;

import modelo.Chofer;
import modelo.Cliente;
import modelo.IViaje;
import modelo.Vehiculo;
import simulacion.Simulacion;

public class SistemaDTO {
	private ArrayList<Vehiculo> flota = new ArrayList<Vehiculo>();
	protected ArrayList<Chofer> choferes = new ArrayList<Chofer>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private ArrayList<IViaje> viajes = new ArrayList<IViaje>();
	private Simulacion simulacion;
	
	public SistemaDTO() {
		
	}

	public ArrayList<Vehiculo> getFlota() {
		return flota;
	}

	public void setFlota(ArrayList<Vehiculo> flota) {
		this.flota = flota;
	}

	public ArrayList<Chofer> getChoferes() {
		return choferes;
	}

	public void setChoferes(ArrayList<Chofer> choferes) {
		this.choferes = choferes;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<IViaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<IViaje> viajes) {
		this.viajes = viajes;
	}

	public Simulacion getSimulacion() {
		return simulacion;
	}

	public void setSimulacion(Simulacion simulacion) {
		this.simulacion = simulacion;
	}
	
}
