
package observer;
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
        System.out.println("el pedido de: "+this.nombre +"ha cambiado su estado a :"+estado);
    }

    
}
