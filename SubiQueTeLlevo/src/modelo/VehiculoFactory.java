package modelo;

public class VehiculoFactory {

    /**
     * Genera un Vehiculo de tipo IVehiculo <br>
     * Precondiciones: Debe ingresar una patente valida. <br>
     * Postcondiciones: Retornara la instancia del objeto "tipoVehiculo" si es valido o null en caso contrario .
     * @param tipoVehiculo El tipo de vehiculo a instanciar.
     * @param patente La patente del vehiculo.
     * */
    public Vehiculo getVehiculo(String tipoVehiculo,String patente){
    	Vehiculo respuesta = null;
        if(tipoVehiculo == null){
            respuesta = null;
        }
        if(tipoVehiculo.equalsIgnoreCase("MOTO")){
            respuesta = new Moto(patente);
        } else if (tipoVehiculo.equalsIgnoreCase("AUTO")) {
            respuesta = new Auto(patente);
        } else if (tipoVehiculo.equalsIgnoreCase("COMBI")) {
            respuesta = new Combi(patente);
        }
        return respuesta;
    }

}
