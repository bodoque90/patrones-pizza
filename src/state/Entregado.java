package state;

import javax.swing.JOptionPane;

public class Entregado implements IEstadoPizza {

    @Override
    public void estadoRecibido(Pedido pedido) {
        JOptionPane.showMessageDialog(null, "El pedido ya fue entregado, no puedes retroceder de estado.");
    }
    @Override
    public void estadoPreparacion(Pedido pedido) {
        JOptionPane.showMessageDialog(null, "El pedido ya fue entregado, no puedes retroceder de estado.");
    }
    @Override
    public void estadoEntregado(Pedido pedido) {
        // Ya está en entregado, no hace nada
    }
    @Override
    public String getNombre() { return "Entregado"; }

}
