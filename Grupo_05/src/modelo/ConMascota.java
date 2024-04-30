package modelo;

/**
 * Clase concreta para decorar viaje<br>
 * <b>Invariante de clase: </b> El estado de un viaje es correcto si tiene un pedido(referencia no nula)<br>
 */
public class ConMascota extends DecoratorViaje {
	
	/**
	 * Constructor para encapsular iviaje
	 * @param iviaje viaje a decorar
	 */
	public ConMascota(IViaje iviaje) {
		super.setIviaje(iviaje);;
	}

	@Override
	public double getCosto() {
		return this.getIviaje().getCosto()*(1+this.getIviaje().getDistancia()*0.20+this.getIviaje().getPedido().getCantidadPasajeros()*0.10);
	}

	@Override
	public Pedido getPedido() {
		return this.getIviaje().getPedido();
	}

	@Override
	public int getDistancia() {
		return this.getIviaje().getDistancia();
	}

	public void setPedido(Pedido pedido) {
		this.getIviaje().setPedido(pedido);
		
	}

	public Object clone() throws CloneNotSupportedException{
		return this.getIviaje().clone();
	}
	
	@Override
	public String toString() {
		return this.getIviaje().toString() + " con mascota";
	}

}
