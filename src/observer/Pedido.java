package observer;

public class Pedido {

    private String nombre;
    private IEstadoPizza estado;

    public Pedido(String nombre) {
        this.estado = new Recibido();
        this.nombre = nombre;
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
        System.out.println("Documento: " + this.nombre);
        System.out.println("Estado: " + this.estado.getNombre());
        System.out.println("******************** \n");
    }

}
