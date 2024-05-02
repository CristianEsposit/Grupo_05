package excepciones;

public class PedidoIncoherenteException extends Exception {
	/**
	 * Excepcion que lanza el constructor de Pedido si el formato no es válido.
	 * @param arg0
	 */
	public PedidoIncoherenteException(String arg0) {
		super(arg0);
	}

}
