
package observer;
public interface IEstadoPizza {
    String getNombre();
    void estadoRecibido(Pedido pedido);
    void estadoPreparacion(Pedido pedido);
    void estadoEntregado(Pedido pedido);
}
