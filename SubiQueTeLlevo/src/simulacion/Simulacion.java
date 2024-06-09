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
/**
 * Clase responsable de ejecutar la simulacion
 */
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
	/**
	 * Constructor:
	 * @param sistema 			   : Instancia del Sistema actual 
	 * @param cantClientes 		   : Cantidad de clientes que se simularán 
	 * @param cantMaxViajesCliente : Máxima cantidad de viajes que un cliente podría realizar
	 * @param cantChoferContratado : Cantidad de Choferes contratados que se simularán
	 * @param cantChoferPermanente : Cantidad de Choferes permanentes que se simularán
	 * @param cantChoferTemporario : Cantidad de Choferes temporarios que se simularán
	 * @param cantMaxViajesChofer  : Máxima cantidad de Viajes que un chofer podría realizar
	 * @param cantMotos			   : Cantidad de autos que se simularán
	 * @param cantAutos			   : Cantidad de autos que se simularán
	 * @param cantCombis		   : Cantidad de autos que se simularán 
	 */
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
	/**
	 * Crea los hilos, el Recurso Compartido y todo lo necesario para la simulación
	 */
	public void iniciaSimulacion() { 
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
