package persistencia;

import negocio.Sistema;

public class Util {
	
	public static SistemaDTO SistemaDTOFromSistema(Sistema sistema) {
		SistemaDTO respuesta = new SistemaDTO();
		respuesta.setChoferes(Sistema.getInstance().listadoChoferes());
		respuesta.setClientes(Sistema.getInstance().listadoClientes());
		respuesta.setFlota(sistema.listadoVehiculos());
		respuesta.setSimulacion(sistema.getSimulacion());
		respuesta.setViajes(Sistema.getInstance().getLViajes());
		return respuesta;
	}
	
	public static Sistema SistemaFromSistemaDTO(SistemaDTO sDTO) {
		
		return null;
	}
}
