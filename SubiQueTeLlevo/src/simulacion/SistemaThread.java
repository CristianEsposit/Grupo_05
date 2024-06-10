package simulacion;

import java.util.ArrayList;

import modelo.Vehiculo;

public class SistemaThread implements Runnable {
	private RecursoCompartido rc;
	private static ArrayList<Vehiculo> vehiculosDisponibles = new ArrayList<Vehiculo>();
	
	public SistemaThread(RecursoCompartido rc) {
		this.rc = rc;
	}

	@Override
	/**
	 * El objetivo de este hilo es asignar siempre que pueda vehiculos a los viajes.
	 */
	public void run() {
		while (RecursoCompartido.getContChoferesActivos() > 0 /*this.controlador.getVista().isActive()*/) {
			this.rc.asignarVehiculo(vehiculosDisponibles);
		}
	}
	
	public static void agregaVehiculo(Vehiculo vehiculo) {
		vehiculosDisponibles.add(vehiculo);
	}

}
