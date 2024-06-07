package ojos;

import java.util.Observable;
import java.util.Observer;

import vistas.VentanasInformativas;

public class OjoChofer implements Observer {
	private Observable observado; //seria el recurso compartido
	private VentanasInformativas vista;
	
	public OjoChofer(Observable o, VentanasInformativas vista) {
		this.observado = o;
		this.observado.addObserver(this);
		this.vista = vista;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (this.observado == o) {
			
			this.vista.actualizaTextAreaChofer(null);
		}
		else
			throw new IllegalArgumentException();
	}

}
