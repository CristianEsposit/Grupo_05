package modelo;

import java.util.ArrayList;

public class Cliente extends Usuario {
	private ArrayList<IViaje> viajes = new ArrayList<IViaje>();

	public Cliente(String nombreReal, String nombreUsuario, String password) {
		super(nombreReal, nombreUsuario, password);
	}

	public void agregaViaje(IViaje viaje) {
		this.viajes.add(viaje);
	}

	public ArrayList<IViaje> getViajes() {
		return viajes;
	}

	@Override
	public String toString() {
		return "Cliente " + super.toString() + "[viajes=" + viajes + "]";
	}
	
	public Object clone() throws CloneNotSupportedException{
		Cliente nCli = (Cliente)super.clone();
		nCli.viajes = ( ArrayList<Viaje> )this.viajes.clone();
		return nCli;
	}

}
