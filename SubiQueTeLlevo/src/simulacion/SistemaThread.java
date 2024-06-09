package simulacion;

import java.util.ArrayList;

import modelo.Vehiculo;

public class SistemaThread implements Runnable {
	private RecursoCompartido rc;
	private ArrayList<Vehiculo> vehiculosDisponibles = new ArrayList<Vehiculo>();
	/**
	 * 
	 * @param rc :Es el recurso compartido al cual va a acceder.
	 */
	public SistemaThread(RecursoCompartido rc) {
		this.rc = rc;
	}

	@Override
	/**
	 * El objetivo de este hilo es asignar siempre que pueda vehiculos a los viajes.
	 */
	public void run() {
		while (RecursoCompartido.getContChoferesActivos() > 0 /*&& no haya un cliente humano interactuando*/) {
			this.rc.asignarVehiculo(this.vehiculosDisponibles);
		}
	}

}
