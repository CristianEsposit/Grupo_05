package presentacion;
import modelo.*;
import excepciones.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import negocio.Sistema;
public class Main {

/*	public static void main(String[] args) {
		Sistema sistema=Sistema.getInstance();
		
		try {
			sistema.agregar(new Cliente("Aurelio Cesar","cesar1024","12345678"));
			sistema.agregar(new Cliente("Aurelio Cesar","cesar1024","12345678"));
		} catch (ClienteExistenteException e) {
			System.out.println(e.getMessage());
		}//cliente con usuario repetido
		
		try {
			sistema.agregar(new Cliente("Moises Lujan","moises1000","12345678"));
			sistema.agregar(new Cliente("Agustin Echeverria","agustin15","12345678"));
			sistema.agregar(new Cliente("Augusto Cesar","roma1024","12345678"));
		}catch(ClienteExistenteException e) {
			System.out.println(e.getMessage());
		}// no entra aca 
		
		sistema.realizarPedido(LocalDateTime.now(), "Peligrosa", false, 1, false, sistema.listadoClientes().get(0), 1);//No hay Vehiculo
		
		sistema.agregar(new Moto("12345678"));
		sistema.agregar(new Auto("12345679"));
		sistema.agregar(new Combi("12345566"));
		
		sistema.realizarPedido(LocalDateTime.now(), "Peligrosa", false, 1, false, sistema.listadoClientes().get(0), 1); //No hay Chofer
		
		try {
		sistema.agregar(new ChoferContratado("11111111","Adan Gomez"));
		sistema.agregar(new ChoferPermanente("11111112","Baltasar Capo",1000,0.05,LocalDate.of(2020, Month.MARCH, 15),3));
		sistema.agregar(new ChoferTemporario("Bastian Gomez","11111113",700,0.05));
		sistema.agregar(new ChoferContratado("11111111","Gaspar Humar"));
		}catch(ChoferExistenteException e) {
			System.out.println(e.getMessage());
		}//dispara la excepcion porque el dni se repite
		
		try {
		sistema.agregar(new ChoferPermanente("11111114","Gaspar Humar",1200,0.1,LocalDate.of(2018, Month.MARCH, 30),1));
		sistema.agregar(new ChoferTemporario("Lucio Lamar","11111115",900,0.05));
		sistema.agregar(new ChoferPermanente("11111116","Octivo Lopez",1200,0.1,LocalDate.of(2018, Month.APRIL, 3),0));
		}catch(ChoferExistenteException e) {
			System.out.println(e.getMessage());
		}//no deberia entrar ya que lo datos son correctos
		
		
		System.out.println("------------------------------------------------------");
		
		sistema.realizarPedido(LocalDateTime.now(), "Peligrosa", false, 1, false, sistema.listadoClientes().get(0), 1); //pide moto
		System.out.println("1");
		sistema.realizarPedido(LocalDateTime.now(), "Estandar", true, 5, true, sistema.listadoClientes().get(0), 3); //pide imposible
		System.out.println("2");
		sistema.realizarPedido(LocalDateTime.now(), "Estandar", true, 4, true, sistema.listadoClientes().get(1), 3); // pide auto
		System.out.println("3");
		sistema.realizarPedido(LocalDateTime.now(), "Estandar", true, 1, true, sistema.listadoClientes().get(1), 3); // pide combi
		System.out.println("4");
		sistema.realizarPedido(LocalDateTime.now(), "Calle Sin asfaltar", true, 4, true, sistema.listadoClientes().get(2), 6); // pide auto
		System.out.println("5");
		sistema.pagar(sistema.listadoClientes().get(1).getViajes().get(0));
		sistema.finalizar(sistema.listadoClientes().get(1).getViajes().get(0));
		sistema.realizarPedido(LocalDateTime.now(), "Peligrosa", false, 10, false, sistema.listadoClientes().get(3), 10); // pide combi
		sistema.pagar(sistema.listadoClientes().get(3).getViajes().get(0));
		sistema.finalizar(sistema.listadoClientes().get(3).getViajes().get(0));
		System.out.println("6");
		sistema.realizarPedido(LocalDateTime.now(), "Calle Sin asfaltar", false, 2, false, sistema.listadoClientes().get(2), 15); //pide auto
		sistema.pagar(sistema.listadoClientes().get(2).getViajes().get(0));
		sistema.finalizar(sistema.listadoClientes().get(2).getViajes().get(0));
		sistema.pagar(sistema.listadoClientes().get(0).getViajes().get(0));
		sistema.finalizar(sistema.listadoClientes().get(0).getViajes().get(0));
		
		System.out.println("------------------------------------------------------");
		//MODIFICACIONES DE CHOFER, CLIENTE Y VEHICULO------------------------------------------------------------------------------
		//modificaciones de chofer
		try {
			sistema.modificarChofer(sistema.listadoChoferes().get(0), "11111111", "Adan Gomez", 1100, 0.05, 0);
			sistema.modificarChofer(sistema.listadoChoferes().get(0), "11111112", "Adan Gomez", 1100, 0.05, -1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//modificaciones de vehiculo
		try {
			sistema.modificar(sistema.listadoVehiculos().get(0), "99999999");
			sistema.modificar(sistema.listadoVehiculos().get(0), null);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//modificaciones de cliente
		try {
			sistema.modificar(sistema.listadoClientes().get(0), "Aurelio Cesar", "cesarRoma", "12345678");
			sistema.modificar(sistema.listadoClientes().get(0), "Aurelio Cesar", "moises1000", "12345678");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------------------------------------------------");
		
		int i;
		ArrayList<String> reporte;
		System.out.println("Reporte de clientes");
		reporte=sistema.reporteCliente();
		for(i=0;i<reporte.size();i++)
			System.out.println(reporte.get(i).toString());
		
		System.out.println("");
		
		System.out.println("Reporte de viajes por chofer");
		try {
			reporte=sistema.reporteViajesPorChofer(sistema.listadoChoferes().get(4), LocalDateTime.of(2024, 5, 1, 0, 0), LocalDateTime.of(2024, 5, 31, 0, 0));
			for(i=0;i<reporte.size();i++)
				System.out.println(reporte.get(i).toString());
		} catch (DatosIncorrectosException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		System.out.println("");
		
		System.out.println("Reporte de salarios");
		reporte=sistema.reporteSalarios();
		for(i=0;i<reporte.size();i++)
			System.out.println(reporte.get(i).toString());
		
		System.out.println("");
		
		System.out.println("Reporte de viajes por cliente");
		try {
			reporte=sistema.reporteViajesPorCliente(sistema.listadoClientes().get(0), LocalDateTime.of(2024, 5, 1, 0, 0), LocalDateTime.of(2024, 5, 31, 0, 0));
			for(i=0;i<reporte.size();i++)
				System.out.println(reporte.get(i).toString());
		} catch (DatosIncorrectosException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("");
		
		System.out.println("Reporte de viajes ordenado "+ sistema.getLViajes().size());
		ArrayList<IViaje> clon=null;
		try {
			clon = sistema.listadoOrdenadoViaje();
			for(i=0;i<clon.size();i++)
				System.out.println(clon.get(i).toString());
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
		
		System.out.println("dahsasio");
	}
	
	
	
}*/
}
