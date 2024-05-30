package modelo;

public class ViajeFactory {
	/**
	 * Añade las capas para encapsular Viaje<br>
	 * <b>Pre: </b> El pedido debe ser distinto de null y debio haber sido validado, y la distancia debe ser mayor a cero<br>
	 * <b>Post: </b> <br> Retorna la instancia de viaje con las capas del decorator
	 * @param pedido Instancia del pedido realizador por el cliente
	 * @param distancia Distancia a recorrer del viaje
	 * @return Instancia del viaje
	 */
	public static IViaje agregarCapas(Pedido pedido, int distancia) {
		assert pedido!=null: "El pedido debe ser distinto de null";
		assert distancia>0: "La distancia debe ser mayor a cero";
		IViaje respuesta=new Viaje(pedido, distancia);
		if(pedido.hasMascota())
			respuesta=new ConMascota(respuesta);
		else
			respuesta=new SinMascota(respuesta);
		if(pedido.hasEquipajeBaul())
			respuesta=new ConBaul(respuesta);
		else
			respuesta=new SinBaul(respuesta);
		if(pedido.getZona().equalsIgnoreCase("Peligrosa"))
			respuesta=new ZonaPeligrosa(respuesta);
		else if(pedido.getZona().equalsIgnoreCase("Estandar"))
			respuesta=new ZonaEstandar(respuesta);
		else
			respuesta=new ZonaCalleSinAsfaltar(respuesta);
		assert respuesta!=null: "Debe retornar una instancia de viaje";
		return respuesta;
	}

}
