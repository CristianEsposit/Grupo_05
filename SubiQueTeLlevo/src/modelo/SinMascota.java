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

	public Object clone() throws CloneNotSupportedException {
		IViaje clon=(IViaje)super.clone(); 
		clon = (IViaje)this.getIviaje().clone();
		clon=new SinMascota(clon);
		return clon;
	}
	
	@Override
	public String toString() {
		return this.getIviaje().toString() + " sin mascota";
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

	@Override
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
	
	public int compareTo(IViaje o) {
		if(this.getIviaje().getCosto() < o.getCosto())
			return -1;
		else if(this.getIviaje().getCosto() > o.getCosto())
			return 1;
		else return 0;
	}

}
