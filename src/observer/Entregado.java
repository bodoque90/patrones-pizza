package observer;

public class Entregado implements IEstadoPizza {

    @Override
    public String getNombre() {
        return "entregado";
    }

    @Override
    public void estadoRecibido(Pedido pedido) {
        System.out.println("El pedido ya fue entregado");
    }

    @Override
    public void estadoPreparacion(Pedido pedido) {
        System.out.println("El pedido ya fue entregado");
    }

    @Override
    public void estadoEntregado(Pedido pedido) {
        System.out.println("El pedido ya fue entregado");
    }

}
