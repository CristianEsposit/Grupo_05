package ojos;

import java.util.Observable;
import java.util.Observer;

import simulacion.ChoferThread;
import vistas.VentanasInformativas;
/**
 * responsable de las actualizaciones en la vista del Chofer
 */
public class OjoChofer implements Observer {
	private Observable observado; //seria el recurso compartido
	private VentanasInformativas vista;
	private ChoferThread chofer;
	
	public OjoChofer(Observable o, VentanasInformativas vista, ChoferThread chofer) {
		this.observado = o;
		this.observado.addObserver(this);
		this.vista = vista;
		this.chofer = chofer;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (this.observado == o) {
			//RecursoCompartido rc = (RecursoCompartido) o; creo que no es necesario
			String cartel = (String) arg;
			if (cartel.contains(this.chofer.getChofer().getNombre()))
				this.vista.actualizaTextAreaChofer(cartel);
		}
		else
			throw new IllegalArgumentException();
	}

}
