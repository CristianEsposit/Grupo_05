package modelo;

public interface IViaje extends Cloneable,Comparable<IViaje> {
	/**
	 * Devuelve el costo del viaje<br>
	 * <b>Pre: </b> El valorBase de viaje debe ser mayor a cero<br>
	 * <b>Post: </b> Se retorna un double con el costo total del viaje<br>
	 * @return costo del viaje
	 */
	double getCosto();
	/**
	 * Devuelve el pedido que origino al viaje<br>
	 * <b>Post: </b> Se retorna una referencia a una instancia de Pedido, el cual origino el viaje<br>
	 * @return pedido asignado al viaje
	 */
	Pedido getPedido();
	void setPedido(Pedido pedido);
	/**
	 * Devuelve la distancia total del viaje<br>
	 * <b>Pre: </b> La distancia debe ser mayor a cero<br>
	 * <b>Post: </b> Se retorna un int de la distancia del viaje<br>
	 * @return distancia del viaje
	 */
	int getDistancia();
	/**
	 * Retorna el clon de IViaje<br>
	 * <b>Post :</b> Retorna una instancia diferente pero con el mismo estado<br>
	 * @return Retorna una instancia de viaje
	 */
	
	String getEstado();
	
	Chofer getChofer();
	
	Vehiculo getVehiculo();
	
	void setEstado(String estado);
	
	void setChofer(Chofer chofer);
	
	void setVehiculo(Vehiculo vehiculo);	
	
	public Object clone() throws CloneNotSupportedException;
	
	String toString();

}
