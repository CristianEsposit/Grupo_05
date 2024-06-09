package vistas;

import controlador.Controlador;

public interface IVista {
	
	void cerrarse();
	
	void setControlador(Controlador c);
	
	String getNombreReal(); // Posiblemente borrar
	
	String getNombreUsuario(); // Posiblemente borrar
	
	String getPasswordUsuario(); // Posiblemente borrar

	void crearMensaje(String arg);
	
	String getZona();
	
	String getMascota();
	
	String getEquipaje();
	
	String getPasajeros();
	
	String getDistancia();

	IVista pasarRegistro();

	IVista pasarRealizarPedido();

	IVista pasarViaje();

	
}