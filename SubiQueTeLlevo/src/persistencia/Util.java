package persistencia;

import negocio.Sistema;

public class Util {
	/**
	 * 
	 * @param sistema Es el objeto del que se extraerá y almacenará la informacion en el DTO
	 * @return un Objeto de tipo DTO. Transferible
	 */
	public static SistemaDTO SistemaDTOFromSistema(Sistema sistema) {
		SistemaDTO respuesta = new SistemaDTO();
		respuesta.setChoferes(Sistema.getInstance().listadoChoferes());
		respuesta.setClientes(Sistema.getInstance().listadoClientes());
		respuesta.setFlota(sistema.listadoVehiculos());
		respuesta.setSimulacion(sistema.getSimulacion());
		respuesta.setViajes(Sistema.getInstance().getLViajes());
		return respuesta;
	}
	
	
	/**
	 * 
	 * @param sDTO Es el objeto del que se extraerá e instanciara el sistema
	 * @return La instancia de del sistema segun los datos del DTO
	 */
	public static Sistema SistemaFromSistemaDTO(SistemaDTO sDTO) {
		Sistema sist = Sistema.getInstance();
		sist.setChoferes(sDTO.getChoferes());
		sist.setClientes(sDTO.getClientes());
		sist.setFlota(sDTO.getFlota());
		sist.setSimulacion(sDTO.getSimulacion());
		sist.setViajes(sDTO.getViajes());
		
		return Sistema.getInstance();
	}
}
