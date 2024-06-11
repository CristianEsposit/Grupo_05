package simulacion;

import excepciones.ChoferExistenteException;
import modelo.Chofer;
import negocio.Sistema;

public class ChoferThread implements Runnable {
	private RecursoCompartido rc;
	private int viajesPorRealizar;
	private Chofer chofer;
	//nuevo
	private boolean agarroViaje = false;
	
	public ChoferThread(RecursoCompartido rc, int viajes, Chofer chofer) throws ChoferExistenteException {
		this.rc = rc;
		this.viajesPorRealizar = viajes;
		this.chofer = chofer;
		this.rc.addChofer(chofer);
		try {			
			Sistema.getInstance().agregar(chofer);
		}catch(ChoferExistenteException e) {
			throw new ChoferExistenteException("Chofer no valido");
		}
	}

	@Override
	/**
	 * Busca un viaje activo para iniciar.
	 */

	public void run() {
		for(int i = 0; i < this.viajesPorRealizar; i++) {
			this.agarroViaje = false;
			this.rc.agarraViaje(this);
			try {
				Thread.sleep((long)(Math.random() * 1000) + 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (this.agarroViaje)
			this.rc.finalizaViaje(chofer);
		}
		this.rc.reduceChoferesThread();
		System.out.println(this.chofer.getNombre() + " se va.");
	}
	
	public Chofer getChofer() {
		return chofer;
	}

	public int getViajesPorRealizar() {
		return viajesPorRealizar;
	}

	public void setAgarroViaje(boolean agarroViaje) {
		this.agarroViaje = agarroViaje;
	}

}
