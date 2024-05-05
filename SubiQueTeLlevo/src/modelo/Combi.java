package modelo;

public class Combi extends Vehiculo {

    public Combi(String nroPatente){
        this.cantPasajeros = 10;
        this.petFriendly = false;
        this.baul = true;
        this.nroPatente = nroPatente;
    }

    /**
     * Calcula el valor de prioridad de la Combi <br>
     * Precondiciones: La cantidad de pasajeros debe ser > 1. <br>
     * Postcondiciones: Retornara el valor de prioridad 10*cantidad de pasajeros + 100 por el uso del baul.
     * @param cantPasajeros La cantidad de pasajeros. cantPasajeros >= 1.
     * @param baul Si el pasajero requiere el baul.
     * @param mascota Si el pasajero lleva una mascota.
     * */
    @Override
    public Integer calcularPrioridad(int cantPasajeros, boolean baul, boolean mascota) {
        return cantPasajeros*10 + 100 * (baul? 1:0);
    }

	@Override
	public String toString() {
		return "Combi nroPatente=" + nroPatente;
	}

}
