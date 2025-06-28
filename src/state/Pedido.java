package state;

import java.util.ArrayList;
import java.util.List;
import observer.IUsuario;
import observer.Usuario;

public class Pedido {

    private String nombre;
    private int precio;
    private IEstadoPizza estado;
     private List<IUsuario> usuarios = new ArrayList<>();

     //este es para contruir pedidos originales
    public Pedido(String nombre, int precioPedido) {
        this.estado = new Recibido();
        this.nombre = nombre;
        this.precio = precioPedido;

    }
        /**
     * metodo para reconstruir pedido desde la base de datos
     * @param nombre
     * @param precioPedido
     * @param estado 
     */
    public Pedido(String nombre, int precioPedido, IEstadoPizza estado) {
    this.estado = estado;
    this.nombre = nombre;
    this.precio = precioPedido;
}
  
    //mas mano
    public void agregarUsuario(IUsuario usuario){
    usuarios.add(usuario);}
    
//aqui meti mano de mas porsi no funciona
    private void notificarlos() {
        for(IUsuario usuario:usuarios){
        usuario.Notificar(this.estado.getNombre());}
    }

    public void recibido() {
        this.estado.estadoRecibido(this);
        notificarlos();
    }

    public void enPreparacion() {
        this.estado.estadoPreparacion(this);
        notificarlos();
    }

    public void entregado() {
        this.estado.estadoEntregado(this);
        notificarlos();
    }

    public void cambiarEstado(IEstadoPizza estado) {
        this.estado = estado;

    }
    
    public String getEstadoNombre() {
    return this.estado.getNombre();
}
public static IEstadoPizza obtenerEstadoDesdeNombre(String nombreEstado) {
    switch (nombreEstado) {
        case "Recibido":
            return new Recibido();
        case "En preparación":
            return new EnPreparacion();
        case "Entregado":
            return new Entregado();
        default:
            return new Recibido(); // Por si acaso
    }
}
    
}
