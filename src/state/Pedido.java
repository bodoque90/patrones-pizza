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

    public Pedido(String nombre, int precioPedido) {
        this.estado = new Recibido();
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

    public void Print() {
        System.out.println("\n ********************");
        System.out.println("nombre del pedido: " + this.nombre);
        System.out.println("precio del pedido: " + this.precio);
        System.out.println("Estado: " + this.estado.getNombre());
        System.out.println("******************** \n");
    }

}
