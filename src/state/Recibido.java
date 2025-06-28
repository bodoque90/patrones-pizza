package state;

import javax.swing.JOptionPane;

public class Recibido implements IEstadoPizza {

    @Override
    public void estadoRecibido(Pedido pedido) {
        // Ya está en recibido, no hace nada
    }

    @Override
    public void estadoPreparacion(Pedido pedido) {
        pedido.cambiarEstado(new EnPreparacion());
    }

    @Override
    public void estadoEntregado(Pedido pedido) {
        // NO PERMITIDO: Saltar directo de Recibido a Entregado
        JOptionPane.showMessageDialog(null, "No puedes entregar un pedido que no está en preparación.");
    }

    @Override
    public String getNombre() {
        return "Recibido";
    }
}
