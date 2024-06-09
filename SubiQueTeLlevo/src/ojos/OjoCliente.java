package ojos;

import java.util.Observable;
import java.util.Observer;

import simulacion.ClienteThread;
import simulacion.RecursoCompartido;
import vistas.VentanasInformativas;

public class OjoCliente implements Observer {
	private Observable observado; //seria el recurso compartido
	private VentanasInformativas vista;
	private ClienteThread cliente;
	
	public OjoCliente(Observable o, VentanasInformativas vista, ClienteThread cliente) {
		this.observado = o;
		this.observado.addObserver(this);
		this.vista = vista;
		this.cliente = cliente;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (this.observado == o) {
			//RecursoCompartido rc = (RecursoCompartido) o; creo que no es necesario
			String cartel = (String) arg;
			if (cartel.contains(this.cliente.getCliente().getNombreReal()))
				this.vista.actualizaTextAreaCliente(cartel);
		}
		else
			throw new IllegalArgumentException();
	}
}
