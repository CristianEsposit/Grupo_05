package modelo;

import java.util.ArrayList;


public abstract class Chofer {
	protected String dni;
	protected String nombre;
	private int puntaje;
	ArrayList<IViaje> viajes = new ArrayList<IViaje>();
	
	//private int calificacion;

	public Chofer(String dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}
	/**
	 * Modifica los atributos del chofer, aunque en algunos casos habra parametros sin usar<br>
	 * <b>Pre: </b> dni y nombre son distinto de null y de vacio, sueldoBasico y aportes son positivos, y cantHijos es un numero natural<br>
	 * <b>Post: </b> los atributos se cambiaron del chofer se cambiaron<br>
	 * @param dni DNI del chofer
	 * @param nombre Nombre del chofer
	 * @param sueldoBasico Sueldo basico del chofer
	 * @param aportes Aportes del chofer
	 * @param cantHijos Cantidad de hijo que tiene el chofer
	 */
	public abstract void modificar(String dni, String nombre, double sueldoBasico, double aportes, int cantHijos);
	
	public abstract double getSueldo();

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<IViaje> getViajes() {
		return viajes;
	}

	public void agregarViaje(IViaje viaje) {
		this.viajes.add(viaje);
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	protected void setDni(String dni) {
		this.dni = dni;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "[dni=" + dni + ", nombre=" + nombre + ", puntaje=" + puntaje + "]";
	}
	
	
	
	/*getCalificacion()- setCalificacion()*/
}
