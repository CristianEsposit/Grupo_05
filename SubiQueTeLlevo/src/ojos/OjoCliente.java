package ojos;

import java.util.Observable;
import java.util.Observer;

import vistas.VentanasInformativas;

public class OjoCliente implements Observer {
	private Observable observado; //seria el recurso compartido
	private VentanasInformativas vista;
	
	public OjoCliente(Observable o, VentanasInformativas vista) {
		this.observado = o;
		this.observado.addObserver(this);
		this.vista = vista;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (this.observado == o) {
			
			this.vista.actualizaTextAreaCliente(null);
		}
		else
			throw new IllegalArgumentException();
	}
}
