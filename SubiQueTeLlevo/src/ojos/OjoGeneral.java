package ojos;

import java.util.Observable;
import java.util.Observer;

import simulacion.RecursoCompartido;
import vistas.VentanasInformativas;
/**
 * responsable de las actualizaciones en la vista General
 */
public class OjoGeneral implements Observer {
	private Observable observado; //seria el recurso compartido
	private VentanasInformativas vista;
	
	public OjoGeneral(Observable o, VentanasInformativas vista) {
		this.observado = o;
		this.observado.addObserver(this);
		this.vista = vista;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (this.observado == o) {
			//RecursoCompartido rc = (RecursoCompartido) o; creo que no es necesario
			String cartel = (String) arg;
			this.vista.actualizaTextAreaGeneral(cartel);
		}
		else
			throw new IllegalArgumentException();
	}

}
