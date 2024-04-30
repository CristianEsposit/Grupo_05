package modelo;

import java.util.ArrayList;

public class Cliente extends Usuario {
	private ArrayList<Viaje> viajes = new ArrayList<Viaje>();

	public Cliente(String nombreReal, String nombreUsuario, String password) {
		super(nombreReal, nombreUsuario, password);
	}

	public void agregaViaje(Viaje viaje) {
		this.viajes.add(viaje);
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	@Override
	public String toString() {
		return "Cliente " + super.toString() + "[viajes=" + viajes + "]";
	}

}
