package state;

public class Pedido {

    private String nombre; 
    private int precio;
    private IEstadoPizza estado;

    public Pedido(String nombre,int precioPedido) {
        this.estado = new Recibido();
        this.nombre = nombre;
        this.precio = precioPedido;
        
    }
    public void recibido() {
        this.estado.estadoRecibido(this);
    }

    public void enPreparacion() {
        this.estado.estadoPreparacion(this);
    }

    public void entregado() {
        this.estado.estadoEntregado(this);
    }

    public void cambiarEstado(IEstadoPizza estado) {
        this.estado = estado;
    }

    public void Print() {
        System.out.println("\n ********************");
        System.out.println("nombre del pedido: " + this.nombre);
        System.out.println("precio del pedido: "+this.precio);
        System.out.println("Estado: " + this.estado.getNombre());
        System.out.println("******************** \n");
    }

}
