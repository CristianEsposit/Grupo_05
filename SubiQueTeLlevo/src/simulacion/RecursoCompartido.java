package simulacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Observable;

import excepciones.FaltaChoferException;
import excepciones.FaltaVehiculoException;
import excepciones.PedidoIncoherenteException;
import modelo.Chofer;
import modelo.Cliente;
import modelo.IViaje;
import modelo.Vehiculo;
import negocio.Sistema;

public class RecursoCompartido extends Observable {
	private static int contClientesActivos;
	private static int contChoferesActivos;
	private ArrayList<IViaje> viajesSolicitado = new ArrayList<IViaje>();
	//private ArrayList<Chofer> choferesDisponibles = new ArrayList<Chofer>(); esto no
	private Sistema sistema;
	
	public synchronized void realizarPedido(LocalDateTime fechaYHora, String zona, boolean mascota, int cantPasajeros,
			boolean equipajeBaul, Cliente cliente, int distancia) throws FaltaChoferException, FaltaVehiculoException, PedidoIncoherenteException {
		IViaje viajeAct =this.sistema.realizarPedido(fechaYHora, zona, mascota, cantPasajeros, equipajeBaul, cliente, distancia);
		this.viajesSolicitado.add(viajeAct);
		setChanged();
		notifyObservers(viajeAct);
		//this.asignarChofer(Sistema.getInstance().listadoChoferes(), viajeAct); esto no
		//las excepciones las agarra el ojo general
	}
	
	private synchronized void agarraViaje(ArrayList<Chofer> choferes, IViaje viaje) throws FaltaChoferException {
		while (contChoferesActivos <= 0 && Sistema.getInstance().listadoChoferes().isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.sistema.asignarChofer(choferes, viaje);
		notifyAll();
	}
	
	public synchronized void asignarVehiculo(ArrayList<Vehiculo> flota, IViaje viaje) {
		try {
			Sistema.getInstance().asignarVehiculo(flota, viaje);
		} catch (FaltaVehiculoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
