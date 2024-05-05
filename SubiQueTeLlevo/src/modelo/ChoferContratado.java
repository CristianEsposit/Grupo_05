package modelo;

public class ChoferContratado extends Chofer {
	protected static double gananciaViaje = 0.25;	
	
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
	
	@Override
	public void modificar(String dni, String nombre, double sueldoBasico, double aportes, int cantHijos) {
		this.setDni(dni);
		this.setNombre(nombre);
	}
	
}
