package presentacion;
import modelo.*;
import java.time.LocalDateTime;
import negocio.Sistema;
public class Main {

	public static void main(String[] args) {
		Sistema sistema=Sistema.getInstance();
		Chofer a=new ChoferTemporario("Juan","4562321",1500,132);
		Chofer b=new ChoferContratado("GUille","46515632");
		Chofer c=new ChoferContratado("Lio","441351");
		sistema.agregar(a);
		sistema.agregar(b);
		sistema.agregar(c);
		
		sistema.realizarPedido(LocalDateTime.now(), "Zona Peligrosa", true, 6, true, null, 5);
		
		
		

	}

}
