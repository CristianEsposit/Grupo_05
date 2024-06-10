package ojos;

import java.util.Observable;
import java.util.Observer;

import modelo.Cliente;
import simulacion.RecursoCompartido;
import vistas.VentanaViaje;
import vistas.VentanasInformativas;
/**
 * responsable de las actualizaciones en la vista General
 */
public class OjoViaje implements Observer {
	private Observable observado;
	private Cliente humano;
	private VentanaViaje vista;
	
	public OjoViaje(Observable o, VentanaViaje vista, Cliente humano) {
		this.observado = o;
		this.observado.addObserver(this);
		this.vista = vista;
		this.humano=humano;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (this.observado == o) {
			//
			String cartel = (String) arg;
			if(cartel.contains(humano.getNombreReal()) && cartel.contains("Iniciado")) {
				this.vista.actualizaTextArea(cartel);
				this.vista.habilitarPagar();
			}
		}
		else
			throw new IllegalArgumentException();
	}

}

