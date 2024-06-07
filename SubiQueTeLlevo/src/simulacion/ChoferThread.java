package simulacion;

import java.util.Observable;
import java.util.Observer;

public class ChoferThread implements Runnable, Observer {
	private Observable recursoCompartido;
	
	public ChoferThread(Observable o) {
		this.recursoCompartido = o;
		this.recursoCompartido.addObserver(this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
