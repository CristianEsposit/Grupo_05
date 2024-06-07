package simulacion;

import java.util.ArrayList;

import modelo.Vehiculo;

public class SistemaThread implements Runnable {
	private RecursoCompartido rc;
	private ArrayList<Vehiculo> vehiculosDisponibles = new ArrayList<Vehiculo>();
	
	public SistemaThread(RecursoCompartido rc) {
		this.rc = rc;
	}

	@Override
	public void run() {
		while (RecursoCompartido.getContChoferesActivos() > 0 /*&& no haya un cliente humano interactuando*/) {
			this.rc.asignarVehiculo(this.vehiculosDisponibles);
		}
	}

}
