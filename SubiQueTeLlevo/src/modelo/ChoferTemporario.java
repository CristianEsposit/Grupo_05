package modelo;

public class ChoferTemporario extends ChoferEmpleado{
	private static final double plusCantidadViajes = 0.1;
	public int cantidadViajes;
	
	public ChoferTemporario() { //para serializar
		
	}

	/**
	 *  <b>Pre: </b><br>
	 *   Dni != null y dni != "" <br>
	 * nombre != null y nombre != ""<br>
	 * sueldoBasico > 0, aportes > 0 <br>
	 * @param nombre Nombre del chofer
	 * @param dni DNI del chofer
	 * @param sueldoBasico Sueldo basico del chofer
	 * @param aportes Aportes del chofer
	 */
	public ChoferTemporario(String nombre, String dni, double sueldoBasico,double aportes) {
		super(dni,nombre,sueldoBasico,aportes);
	}

	@Override
	public double getSueldo() {
		double sueldo = super.sueldoBasico*(1+this.viajes.size());
		return sueldo*(1 - super.aportes);
	}

	public int getCantidadViajes() {
		return cantidadViajes;
	}

	public void setCantidadViajes(int cantidadViajes) {
		this.cantidadViajes = cantidadViajes;
	}
	
	public double getPlusCantidadViajes(){
		return plusCantidadViajes;
	}

	@Override
	public void modificar(String dni, String nombre, double sueldoBasico, double aportes, int cantHijos) {
		this.setAportes(aportes);
		this.setDni(dni);
		this.setNombre(nombre);
		this.setSueldoBasico(sueldoBasico);
	}

	public static double getPluscantidadviajes() { //para serializar
		return plusCantidadViajes;
	}

}
