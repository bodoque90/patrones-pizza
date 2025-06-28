
package state;


public class Recibido implements IEstadoPizza{

    @Override
    public String getNombre() {
       return "Recibido";
    }

    @Override
    public void estadoRecibido(Pedido pedido) {
        System.out.println("El Pedido ya se encuentra recibido");
    }

    @Override
    public void estadoPreparacion(Pedido pedido) {
        pedido.cambiarEstado(new EnPreparacion());
        System.out.println("Pedido en preparacion.");
    }

    @Override
    public void estadoEntregado(Pedido pedido) {
        System.out.println("el pedido debe pasar por preparacion");
    }
    
}
