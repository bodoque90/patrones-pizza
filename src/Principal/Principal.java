
package Principal;


import Conexion.conexion;
import Vista.frmVentanaInicio;
import javax.swing.UIManager;
import state.Pedido;
import observer.Usuario;
public class Principal {

    public static void main(String[] args) {
        conexion objConexion = new conexion();
        
            try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
         objConexion.establecerConexion();
        frmVentanaInicio formulario = new frmVentanaInicio();
        formulario.setVisible(true);

        
        
    }
    
}
