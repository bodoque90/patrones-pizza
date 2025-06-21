
package observer;


public class EnPreparacion implements IEstadoPizza{

    @Override
    public String getNombre() {
         return "En preparacion";
    }

    @Override
    public void estadoRecibido(Pedido pedido) {
        System.out.println("el pedido ya empezo a prepararse");
    }

    @Override
    public void estadoPreparacion(Pedido pedido) {
       System.out.println("El pedido se encuentra en preparacion");
    }

    @Override
    public void estadoEntregado(Pedido pedido) {
        pedido.cambiarEstado(new Entregado());
        System.out.println("pedido listo para entregar");
    }
    
}
