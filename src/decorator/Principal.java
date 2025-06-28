
package decorator;


import ConexionBs.conexion;
import Vistas.frmVentanaInicio;
import javax.swing.UIManager;
import state.Pedido;
import observer.Usuario;
public class Principal {

    public static void main(String[] args) {
        conexion objConexion = new conexion();
        IPizza pz1 = new pizzaNapolitana();
        pz1 = new extraBarbeque(pz1);
        String nombre = pz1.getDescripcion();
        int precio = pz1.getPrecio();
        Pedido p1 = new Pedido(nombre,precio);
        
        Usuario u1 = new Usuario("juan carlos");
        p1.agregarUsuario(u1);
        
        p1.enPreparacion();
        p1.entregado();
        p1.enPreparacion();
        p1.Print();
        
            try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
         objConexion.establecerConexion();
        frmVentanaInicio formulario = new frmVentanaInicio();
        formulario.setVisible(true);

        
        
    }
    
}
