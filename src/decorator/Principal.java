
package decorator;

import observer.Pedido;

public class Principal {

    public static void main(String[] args) {
        IPizza pz1 = new pizzaNapolitana();
        //pz1 = new extraBarbeque(pz1);
        String nombre = pz1.getDescripcion();
        int precio = pz1.getPrecio();
        Pedido p1 = new Pedido(nombre,precio);
        p1.enPreparacion();
        p1.entregado();
        p1.recibido();
        p1.Print();
    }
    
}
