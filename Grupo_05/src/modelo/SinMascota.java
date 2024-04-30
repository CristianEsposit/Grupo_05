package modelo;

/**
 * Clase concreta para decorar viaje<br>
 * <b>Invariante de clase: </b> El estado de un viaje es correcto si tiene un pedido(referencia no nula)<br>
 */
public class SinMascota extends DecoratorViaje {
	
	/**
	 * Constructor para encapsular iviaje
	 * @param iviaje viaje a decorar
	 */
	public SinMascota(IViaje iviaje) {
		super.setIviaje(iviaje);;
	}

	@Override
	public double getCosto() {
		return this.getIviaje().getCosto();
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
		return this.getIviaje().toString() + " sin mascota";
	}

}
