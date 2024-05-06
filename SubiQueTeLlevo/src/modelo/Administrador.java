package modelo;

public class Administrador extends Usuario {
	private static Administrador instancia=null;

	private Administrador(String nombreReal, String nombreUsuario, String password) {
		super(nombreReal, nombreUsuario, password);
	}
	
	public static Administrador getInstance(String nombreReal, String nombreUsuario, String password) {
		if(instancia==null) 
			instancia=new Administrador(nombreReal,nombreUsuario,password);
		return instancia;
	}
}
