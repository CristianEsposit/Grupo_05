package simulacion;

public class ChoferThread implements Runnable {
	private RecursoCompartido rc;
	private int viajesPorRealizar;
	
	public ChoferThread(RecursoCompartido rc, int viajes) {
		this.rc = rc;
		this.viajesPorRealizar = viajes;
	}

	@Override
	public void run() {
		for(int i = 0; i < this.viajesPorRealizar; i++) {
			this.rc.agarraViaje();
			try {
				Thread.sleep((long)(Math.random() * 1000) + 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.rc.finalizaViaje();
		}
		RecursoCompartido.setContChoferesActivos(RecursoCompartido.getContChoferesActivos() - 1);
	}

}
