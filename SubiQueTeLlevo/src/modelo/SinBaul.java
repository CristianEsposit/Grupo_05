package modelo;

/**
 * Clase concreta para decorar viaje<br>
 * <b>Invariante de clase: </b> El estado de un viaje es correcto si tiene un pedido(referencia no nula)<br>
 */
public class SinBaul extends DecoratorViaje {
	
	/**
	 * Constructor para encapsular iviaje
	 * @param iviaje viaje a decorar
	 */
	public SinBaul(IViaje iviaje) {
		super.setIviaje(iviaje);;
	}

	@Override
	public double getCosto() {
		return this.getIviaje().getCosto();
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

	public void setPedido(Pedido pedido) {
		this.getIviaje().setPedido(pedido);
		
	}

	public Object clone() throws CloneNotSupportedException{
		return this.getIviaje().clone();
	}
	
	@Override
	public String toString() {
		return this.getIviaje().toString() + " equipaje manual";
	}
	
	@Override
	public String getEstado() {
		return this.getIviaje().getEstado();
	}

	@Override
	public Chofer getChofer() {
		return this.getIviaje().getChofer();
	}

	@Override
	public Vehiculo getVehiculo() {
		return this.getIviaje().getVehiculo();
	}

	public void setEstado(String estado) {
		this.getIviaje().setEstado(estado);
	}

	@Override
	public void setChofer(Chofer chofer) {
		this.getIviaje().setChofer(chofer);
	}

	@Override
	public void setVehiculo(Vehiculo vehiculo) {
		this.getIviaje().setVehiculo(vehiculo);
	}
}
