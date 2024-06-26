package modelo;

import java.util.ArrayList;

public class Cliente extends Usuario implements Cloneable{
	private ArrayList<IViaje> viajes = new ArrayList<IViaje>();
	//nuevo
	private boolean pedidoValido;

	public Cliente() { //para serializar
		
	}
	
	public Cliente(String nombreReal, String nombreUsuario, String password) {
		super(nombreReal, nombreUsuario, password);
	}
	/**
	 * Agrega un viaje al ArrayList de IViaje del cliente.
	 * <b>Pre: </b> El parametro viaje no puede ser null.
	 * @param viaje : Parametro que indica el viaje que se quiere agregar.
	 */
	public void agregaViaje(IViaje viaje) {
		assert viaje != null : "El viaje no es valido.";
		this.viajes.add(viaje);
	}

	public ArrayList<IViaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<IViaje> viajes) {
		this.viajes = viajes;
	}

	@Override
	public String toString() {
		return super.toString() + " Listado de viajes  " + viajes;
	}
	
	public Object clone() throws CloneNotSupportedException{
		Cliente nCli = (Cliente)super.clone();
		nCli.viajes = (ArrayList<IViaje>)this.viajes.clone();
		return nCli;
	}

	//nuevo
	public boolean isPedidoValido() {
		return pedidoValido;
	}

	public void setPedidoValido(boolean pedidoValido) {
		this.pedidoValido = pedidoValido;
	}

}
