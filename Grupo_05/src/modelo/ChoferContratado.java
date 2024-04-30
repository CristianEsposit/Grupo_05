package modelo;

public class ChoferContratado extends Chofer {
	protected static double gananciaViaje = 0.25;	
	
	/**
	 * <b>Pre</b> <br>
	 * dni != null, dni != "" , nombre != null, nombre != ""
	 * @param dni
	 * @param nombre
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
	
	// O hacer el getSueldo desde el sistema, con el listado
}
