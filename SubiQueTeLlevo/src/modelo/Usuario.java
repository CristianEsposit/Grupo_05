package modelo;

public class Usuario {
	private String nombreReal;
	private String nombreUsuario;
	private String password;
	
	public Usuario(String nombreReal, String nombreUsuario, String password) {
		super();
		this.nombreReal = nombreReal;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [nombreReal=" + nombreReal + ", nombreUsuario=" + nombreUsuario + ", password=" + password
				+ "]";
	}

	public String getNombreReal() {
		return nombreReal;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
