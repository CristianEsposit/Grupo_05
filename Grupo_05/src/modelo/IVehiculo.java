package modelo;


public interface IVehiculo {
    boolean verificaCantPasajeros(int pasajeros);
    Integer getPrioridad(Pedido pedido);
    boolean verificaTransporteMascota(boolean petFriendly);
    boolean verificaUsoBaul(boolean baul);

}
