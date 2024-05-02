package modelo;

public abstract class Vehiculo{
    protected int cantPasajeros;
    protected boolean petFriendly;
    protected boolean baul;
    protected String nroPatente;

    /**
     * Retorna un Integer con el valor de prioridad del vehiculo si este cumple con el pedido <br>
     * Precondiciones: El pedido debe ser valido, no puede ser nulo. <br>
     * Postcondiciones: retornara el valor de prioridad del vehiculo si cumple con las condiciones del pedido, en caso contrario retorna null .
     * @param pedido Es objeto de tipo Pedido, pedido != null.
     * */

    public Integer getPrioridad(Pedido pedido){
        assert pedido != null: "pedido es nulo";
        if(this.verificaCantPasajeros(pedido.getCantidadPasajeros()) && this.verificaTransporteMascota(pedido.hasMascota()) && this.verificaUsoBaul(pedido.hasEquipajeBaul())){
            return calcularPrioridad(pedido.getCantidadPasajeros(),pedido.hasEquipajeBaul(),pedido.hasMascota());
        }
        return null;
    }

    public abstract Integer calcularPrioridad(int cantPasajeros, boolean baul, boolean mascota);


    public boolean verificaCantPasajeros(int pasajeros) {
        return pasajeros <= this.cantPasajeros;
    }

    public boolean verificaTransporteMascota(boolean petFriendly) {
        return this.petFriendly == petFriendly;
    }

    public boolean verificaUsoBaul(boolean baul) {
        return this.baul == baul;
    }

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public boolean isBaul() {
        return baul;
    }

    public String getNroPatente() {
        return nroPatente;
    }

	public void setNroPatente(String nroPatente) {
		this.nroPatente = nroPatente;
	}

}
