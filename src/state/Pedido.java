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

    /**
     * Constructor para crear un pedido original (nuevo).
     *
     * @param nombre Nombre o descripción de la pizza solicitada.
     * @param precioPedido Precio total del pedido.
     *
     * Funcionamiento: - Inicializa el estado del pedido como "Recibido" (estado inicial). - Asigna el nombre y el precio recibidos por parámetro.
     */
    public Pedido(String nombre, int precioPedido) {
        this.estado = new Recibido(); // Estado inicial del pedido
        this.nombre = nombre;         // Nombre o descripción de la pizza
        this.precio = precioPedido;   // Precio total del pedido
    }

    /**
     * metodo para reconstruir pedido desde la base de datos
     *
     * @param nombre
     * @param precioPedido
     * @param estado
     */
    public Pedido(String nombre, int precioPedido, IEstadoPizza estado) {
        this.estado = estado;
        this.nombre = nombre;
        this.precio = precioPedido;
    }

    public void agregarUsuario(IUsuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Notifica a todos los usuarios (observers) registrados sobre el cambio de estado del pedido.
     *
     * Funcionamiento: 1. Recorre la lista de usuarios observadores. 2. Llama al método Notificar de cada usuario, pasándole el nombre del estado actual del pedido. Así, cada usuario recibe una notificación personalizada con el nuevo estado.
     */
    private void notificarlos() {
        for (IUsuario usuario : usuarios) {
            usuario.Notificar(this.estado.getNombre());
        }
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
        notificarlos();

    }

    public String getNombrePizza() {
        return nombre;
    }

    public int getPrecioTotal() {
        return precio;
    }

    public String getEstadoNombre() {
        return this.estado.getNombre();
    }

    /**
     * Convierte el nombre del estado (como texto) en una instancia del estado correspondiente de la pizza.
     *
     * @param nombreEstado El nombre del estado (por ejemplo, "Recibido", "En preparación", "Entregado").
     * @return Una instancia de la clase que representa ese estado específico de la pizza.
     *
     * Funcionamiento: 1. Según el valor de nombreEstado, retorna una nueva instancia de la clase correspondiente: - "Recibido" => new Recibido() - "En preparación" => new EnPreparacion() - "Entregado" => new Entregado() 2. Si el nombre no coincide con ninguno de los casos conocidos, retorna por defecto new Recibido().
     */
    public static IEstadoPizza obtenerEstadoDesdeNombre(String nombreEstado) {
        switch (nombreEstado) {
            case "Recibido":
                return new Recibido();
            case "En preparación":
                return new EnPreparacion();
            case "Entregado":
                return new Entregado();
            default:
                return new Recibido(); // Estado por defecto si el texto no coincide
        }
    }

}
