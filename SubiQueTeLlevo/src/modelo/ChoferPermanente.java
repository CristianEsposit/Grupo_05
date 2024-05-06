package modelo;

import java.time.LocalDate;

public class ChoferPermanente extends ChoferEmpleado{
	protected static final double plusAntiguedad = 0.02; // "% por año de antigüedad
	protected static final double plusHijos = 0.02;	//
	protected LocalDate fechaIngreso; //clonable?
	protected int cantidadHijos;
	
	/**
	 * <b>Pre: </b><br>
	 * Dni != null y dni != "" <br>
	 * nombre != null y nombre != ""<br>
	 * sueldoBasico > 0, aportes > 0 <br>
	 * fechaIngreso != null, válida.
	 * @param dni DNI de chofer
	 * @param nombre Nombre del chofer
	 * @param sueldoBasico Sueldo basico del chofer
	 * @param aportes Aportes del chofer
	 * @param fechaIngreso Fecha de ingreso del chofer a la lista por primera vez
	 * @param cantidadHijos
	 */
	public ChoferPermanente(String dni, String nombre, double sueldoBasico, double aportes, LocalDate fechaIngreso,
			int cantidadHijos) {
		
		super(dni, nombre, sueldoBasico, aportes);
		this.fechaIngreso = fechaIngreso;
		this.cantidadHijos = cantidadHijos;
	}


	@Override
	public double getSueldo() {
		double sueldo = super.sueldoBasico;
		LocalDate fechaActual = LocalDate.now();
		int antiguedad = fechaActual.getYear() - fechaIngreso.getYear();
		
		sueldo *= (1 + plusAntiguedad*antiguedad + this.cantidadHijos*plusHijos);
		sueldo *= 1-aportes;
		
		return sueldo;
	}


	public int getCantidadHijos() {
		return cantidadHijos;
	}

	/**
	 * Pre: cantihijos > 0, entero.
	 * @param cantidadHijos
	 */
	public void setCantidadHijos(int cantidadHijos) {
		this.cantidadHijos = cantidadHijos;
	}


	public static double getPlusantiguedad() {
		return plusAntiguedad;
	}


	public static double getPlushijos() {
		return plusHijos;
	}


	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}


	@Override
	public void modificar(String dni, String nombre, double sueldoBasico, double aportes, int cantHijos) {
		this.setDni(dni);
		this.setAportes(aportes);
		this.setCantidadHijos(cantHijos);
		this.setSueldoBasico(sueldoBasico);
		this.setNombre(nombre);
		
	}


	

}
