package modelo;

public class ChoferContratado extends Chofer {
	protected static double gananciaViaje = 0.25;
	
	public ChoferContratado() { //para serializar
		
	}
	
	/**
	 * <b>Pre</b> <br>
	 * dni != null, dni != "" , nombre != null, nombre != ""
	 * @param dni DNI del chofer
	 * @param nombre Nombre del chofer
	 */
	public ChoferContratado(String dni, String nombre) {
		super(dni, nombre);
	}
	
	@Override
	public double getSueldo() {
		double total = 0;
		for(int i = 0; i<this.viajes.size(); i++) {
			total += this.viajes.get(i).getCosto();
		}
		return total;
	}

	public double getGananciaViaje() {
		return gananciaViaje;
	}
	
	public static void setGananciaViaje(double gananciaViaje) { //para serializar
		ChoferContratado.gananciaViaje = gananciaViaje;
	}

	@Override
	public void modificar(String dni, String nombre, double sueldoBasico, double aportes, int cantHijos) {
		this.setDni(dni);
		this.setNombre(nombre);
	}
	
}
