
package decorator;

import state.Pedido;
import observer.Usuario;
public class Principal {

    public static void main(String[] args) {
        IPizza pz1 = new pizzaNapolitana();
        pz1 = new extraBarbeque(pz1);
        String nombre = pz1.getDescripcion();
        int precio = pz1.getPrecio();
        Pedido p1 = new Pedido(nombre,precio);
        
        Usuario u1 = new Usuario(nombre);
        u1.Notificar(p1);


        
        
    }
    
}
