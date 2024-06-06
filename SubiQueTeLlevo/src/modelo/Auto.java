package modelo;

public class Auto extends Vehiculo {
	
	public Auto() { //para serializar
		
	}

    public Auto(String nroPatente){
        this.cantPasajeros = 4;
        this.petFriendly = true;
        this.baul = true;
        this.nroPatente = nroPatente;
    }
    /**
     * Calcula el valor de prioridad del Auto <br>
     * Precondiciones: La cantidad de pasajeros debe ser >= 1. <br>
     * Postcondiciones: Retornara el valor de prioridad 40* la cantidad de pasajeros o 30* la cantidad de pasajeros en caso contrario.
     * @param cantPasajeros La cantidad de pasajeros.  cantPasajeros >= 1.
     * @param baul si el pasajero requiere el baul.
     * @param mascota si el pasajero lleva una mascota.
     * */
    @Override
    public Integer calcularPrioridad(int cantPasajeros, boolean baul, boolean mascota) {
        if(baul) {
            return 40*cantPasajeros;
        }else{
            return 30*cantPasajeros;
        }
    }

    @Override
	public String toString() {
		return "Auto nroPatente=" + nroPatente;
	}
}
