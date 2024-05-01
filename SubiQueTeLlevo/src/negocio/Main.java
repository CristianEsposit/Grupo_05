package negocio;

import modelo.ChoferPermanente;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Sistema sistema = Sistema.getInstance();
		
		ChoferPermanente c = (ChoferPermanente)sistema.choferes.get(3);
		
		sistema.modificarChofer(c,"asd","sdsd",3);
		

	}

}
