
package observer;

import javax.swing.JOptionPane;

public class Usuario implements IUsuario{
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void Notificar(String estado) {
        JOptionPane.showMessageDialog(null, "El pedido de "+nombre+" a cambiado al estado : "+estado);
    }

    
}
