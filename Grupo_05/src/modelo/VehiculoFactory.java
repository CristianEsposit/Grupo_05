package modelo;

public class VehiculoFactory {

    /**
     * Genera un Vehiculo de tipo IVehiculo <br>
     * Precondiciones: Debe ingresar una patente valida. <br>
     * Postcondiciones: Retornara la instancia del objeto "tipoVehiculo" si es valido o null en caso contrario .
     * @param tipoVehiculo El tipo de vehiculo a instanciar.
     * @param patente La patente del vehiculo.
     * */
    public IVehiculo getVehiculo(String tipoVehiculo,String patente){
        if(tipoVehiculo == null){
            return null;
        }
        if(tipoVehiculo.equalsIgnoreCase("MOTO")){
            return new Moto(patente);
        } else if (tipoVehiculo.equalsIgnoreCase("AUTO")) {
            return new Auto(patente);
        } else if (tipoVehiculo.equalsIgnoreCase("COMBI")) {
            return new Combi(patente);
        }
        return null;
    }

}
