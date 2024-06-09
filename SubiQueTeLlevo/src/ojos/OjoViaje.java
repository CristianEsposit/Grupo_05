package ojos;

import java.util.Observable;
import java.util.Observer;

import simulacion.RecursoCompartido;
import vistas.VentanaViaje;

public class OjoViaje implements Observer{
	VentanaViaje vista;
	Observer observado;
	
	@Override
	public void update(Observable o, Object arg) { // TO DO implementar luego del merge
		if(observado!=o) {
		RecursoCompartido recurso= (RecursoCompartido) o;
		}
	}

}
