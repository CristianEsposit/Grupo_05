package modelo;

/**
 * Clase concreta para decorar viaje<br>
 * <b>Invariante de clase: </b> El estado de un viaje es correcto si tiene un pedido(referencia no nula)<br>
 */
public class ConBaul extends DecoratorViaje {
	/**
	 * Constructor para encapsular iviaje
	 * @param iviaje viaje a decorar
	 */
	public ConBaul(IViaje iviaje) {
		super.setIviaje(iviaje);
		
	}
	
	@Override
	public double getCosto() {
		// TODO Auto-generated method stub
		return this.getIviaje().getCosto()*(1+this.getIviaje().getDistancia()*0.05+this.getIviaje().getPedido().getCantidadPasajeros()*0.10);
	}

	@Override
	public Pedido getPedido() {
		// TODO Auto-generated method stub
		return this.getIviaje().getPedido();
	}

	@Override
	public int getDistancia() {
		// TODO Auto-generated method stub
		return this.getIviaje().getDistancia();
	}

	@Override
	public void setPedido(Pedido pedido) {
		this.getIviaje().setPedido(pedido);
		
	}

	public Object clone() throws CloneNotSupportedException{
		return this.getIviaje().clone();
	}

	@Override
	public String toString() {
		return this.getIviaje().toString() + " con baul";
	}

}
