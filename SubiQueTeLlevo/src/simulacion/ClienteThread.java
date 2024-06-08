package simulacion;

public class ClienteThread implements Runnable {
	private RecursoCompartido rc;
	private int viajesPorSolicitar;
	
	public ClienteThread(RecursoCompartido rc, int viajes) {
		this.rc = rc;
		this.viajesPorSolicitar = viajes;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.viajesPorSolicitar; i++) {
			this.rc.realizarPedido(null, null, false, i, false, null, i);
			try {
				Thread.sleep((long)(Math.random() * 1000) + 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.rc.pagarViaje();
		}
		RecursoCompartido.setContClientesActivos(RecursoCompartido.getContClientesActivos() - 1);
	}

}
