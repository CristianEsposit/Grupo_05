package modelo;

public class ChoferTemporario extends ChoferEmpleado{
	private static final double plusCantidadViajes = 0.1;
	public int cantidadViajes;

	/**
	 *  <b>Pre: </b><br>
	 *   Dni != null y dni != "" <br>
	 * nombre != null y nombre != ""<br>
	 * sueldoBasico > 0, aportes > 0 <br>
	 * @param nombre
	 * @param dni
	 * @param sueldoBasico
	 * @param aportes
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

}
