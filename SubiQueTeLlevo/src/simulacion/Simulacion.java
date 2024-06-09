package simulacion;

import java.time.LocalDate;
import java.util.Random;

import excepciones.ChoferExistenteException;
import modelo.ChoferContratado;
import modelo.ChoferPermanente;
import modelo.ChoferTemporario;
import modelo.Moto;
import modelo.Auto;
import modelo.Combi;
import negocio.Sistema;

public class Simulacion {
	private Sistema sistema;
	private RecursoCompartido recursoCompartido;
	private int cantClientes;
	private int cantViajesCliente;
	private int cantChoferContratado;
	private int cantChoferPermanente;
	private int cantChoferTemporario;
	private int cantViajesChofer;
	private int cantMotos;
	private int cantAutos;
	private int cantCombis;
	
	public Simulacion() {
		
	}
	
	public Simulacion(Sistema sistema, int cantClientes, int cantMaxViajesCliente,int cantChoferContratado,
			int cantChoferPermanente,int cantChoferTemporario, int cantMaxViajesChofer, int cantMotos, int cantAutos, int cantCombis) {
		Random random = new Random();
		this.cantClientes = cantClientes;
		this.cantViajesCliente = random.nextInt(cantMaxViajesCliente)+1;
		this.cantChoferContratado = cantChoferContratado;
		this.cantChoferPermanente = cantChoferPermanente;
		this.cantChoferTemporario = cantChoferTemporario;
		this.cantViajesChofer = random.nextInt(cantMaxViajesChofer)+1;
		this.cantMotos = cantMotos;
		this.cantAutos = cantAutos;
		this.cantCombis = cantCombis;
		this.sistema = sistema;
	}
	
	public void iniciaSimulacion() { //crea los hilos, el RC y todo lo necesario para la simulacion
		this.recursoCompartido = new RecursoCompartido(this.cantViajesCliente,this.cantViajesChofer);
		for(int i = 1; i <= this.cantMotos; i++) {
			this.sistema.agregar(new Moto("MMM"+i));
		}
		for(int i = 1; i <= this.cantAutos; i++) {
			this.sistema.agregar(new Auto("AAA"+i));
		}
		for(int i = 1; i <= this.cantCombis; i++) {
			this.sistema.agregar(new Combi("CCC"+i));
		}
		for(int i = 1; i <= this.cantClientes;i++) {
			new Thread(new ClienteThread(this.recursoCompartido,cantViajesCliente,i));
		}
		for(int i = 1; i <= this.cantChoferContratado;i++) {
			try {				
				new Thread(new ChoferThread(this.recursoCompartido,cantViajesChofer,new ChoferContratado("" + i,"bot_chofer_contratado" + i))).start();
			}catch(ChoferExistenteException e) {}
		}
		for(int i = 1; i <= this.cantChoferPermanente;i++) {
			try {				
				new Thread(new ChoferThread(this.recursoCompartido,cantViajesChofer,new ChoferPermanente("" + i,"bot_chofer_permanente" + i,100,100,LocalDate.now(),1))).start();
			}catch(ChoferExistenteException e) {}
		}
		for(int i = 1; i <= this.cantChoferTemporario;i++) {
			try {
				new Thread(new ChoferThread(this.recursoCompartido,cantViajesChofer,new ChoferTemporario("" + i,"bot_chofer" + i,100,100))).start();
			}catch(ChoferExistenteException e) {}
		}
		new Thread(new SistemaThread(this.recursoCompartido)).start();
	}
}
