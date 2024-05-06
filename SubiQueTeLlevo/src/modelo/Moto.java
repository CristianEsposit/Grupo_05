package modelo;

public class Moto extends Vehiculo {

    public Moto(String nroPatente){
        this.cantPasajeros = 1;
        this.petFriendly = false;
        this.baul = false;
        this.nroPatente = nroPatente;
    }

    /**
     * Calcula el valor de prioridad de la moto <br>
     * Precondiciones: La cantidad de pasajeros debe ser 1. <br>
     * Postcondiciones: Retornara el valor de prioridad 1000 si cumple con las condiciones o null en otro caso.
     * @param cantPasajeros La cantidad de pasajeros. cantPasajeros == 1.
     * @param baul si el pasajero requiere el baul.
     * @param mascota si el pasajero lleva una mascota.
     * */
    @Override
    public Integer calcularPrioridad(int cantPasajeros, boolean baul, boolean mascota) {
        if(cantPasajeros == this.cantPasajeros && !mascota && !baul)
            return 1000;
        return null;
    }

    @Override
	public String toString() {
		return "Moto nroPatente=" + nroPatente;
	}
}
