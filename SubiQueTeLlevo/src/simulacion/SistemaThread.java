package simulacion;

import java.util.Observable;
import java.util.Observer;

import modelo.IViaje;

public class SistemaThread implements Runnable, Observer  {
	private Observable observado;
	
	public SistemaThread(Observable o) {
		this.observado = o;
		this.observado.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == this.observado) {
			RecursoCompartido RC = (RecursoCompartido) o;
			IViaje viaje = (IViaje) arg;
			RC.asignarVehiculo(null, viaje);
		}
		else
			throw new IllegalArgumentException();
	}

	@Override
	public void run() {
		
	}

}
