
package state;

import javax.swing.JOptionPane;


public class EnPreparacion implements IEstadoPizza{

@Override
    public void estadoRecibido(Pedido pedido) {
        // NO PERMITIDO: Volver atrás
        JOptionPane.showMessageDialog(null, "No puedes volver a 'Recibido' una vez iniciado.");
    }
    @Override
    public void estadoPreparacion(Pedido pedido) {
        // Ya está en preparación, no hace nada
    }
    @Override
    public void estadoEntregado(Pedido pedido) {
        pedido.cambiarEstado(new Entregado());
    }
    @Override
    public String getNombre() { return "En preparación"; }
}
