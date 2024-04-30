package modelo;

public abstract class ChoferEmpleado extends Chofer {
	protected double sueldoBasico;
	protected double aportes;	
	/**
	 * <b>Pre: </b><br>
	 * Dni != null y dni != "" <br>
	 * nombre != null y nombre != ""<br>
	 * sueldoBasico > 0, aportes > 0 <br>
	 * fechaIngreso != null, válida.
	 * @param dni
	 * @param nombre
	 * @param sueldoBasico
	 * @param aportes
	 */
	public ChoferEmpleado(String dni, String nombre,double sueldoBasico,double aportes) {
		super(dni, nombre);
		this.sueldoBasico = sueldoBasico;
		this.aportes = aportes;
	}

	public double getSueldoBasico() {
		return sueldoBasico;
	}

	public void setSueldoBasico(double sueldoBasico) {
		this.sueldoBasico = sueldoBasico;
	}

	public double getAportes() {
		return aportes;
	}

	public void setAportes(double aportes) {
		this.aportes = aportes;
	}
}
