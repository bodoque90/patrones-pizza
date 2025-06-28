package Controller;

import Conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import state.Pedido;
import state.IEstadoPizza;

public class pedidosController {

    private conexion conexionDb;

    public pedidosController(conexion conexionDb) {
        this.conexionDb = conexionDb;
    }

    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param idCliente El ID del cliente que realiza el pedido.
     * @param nombrePizza El nombre/descripción de la pizza solicitada.
     * @param estado El estado inicial del pedido "recibido"
     * @param precioTotal El precio total del pedido.
     * @return true si el pedido se guardó correctamente, false si ocurrió algún error.
     *
     * Funcionamiento: 1. Establece una conexión a la base de datos. 2. Prepara la sentencia SQL para insertar el pedido con los datos recibidos. 3. Asigna los valores a la consulta preparada. 4. Ejecuta la inserción. 5. Si la operación es exitosa, retorna true. 6. Si ocurre una excepción, muestra el error por consola y retorna false. 7. Finalmente, cierra la conexión a la base de datos.
     */
    public boolean guardarPedido(int idCliente, String nombrePizza, String estado, int precioTotal) {
        Connection con = conexionDb.establecerConexion();
        String sql = "INSERT INTO pedidos (idCliente, nombrePizza, estado, precioTotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setString(2, nombrePizza);
            ps.setString(3, estado);
            ps.setInt(4, precioTotal);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
            return false;
        } finally {
            conexionDb.cerrarConexion();
        }
    }

    /**
     * Busca y retorna el pedido asociado a un cliente dado su ID.
     *
     * @param idCliente ID del cliente cuyo pedido se desea buscar.
     * @return Un objeto Pedido si encuentra el pedido, o null si no existe.
     * @throws Exception Si ocurre un error en la consulta a la base de datos.
     *
     * Funcionamiento: 1. Prepara una consulta SQL para seleccionar el nombre de la pizza, el precio y el estado del pedido del cliente. 2. Establece la conexión y prepara la consulta con el ID del cliente. 3. Ejecuta la consulta y verifica si hay resultados. 4. Si encuentra un pedido: a. Obtiene los valores de la pizza, el precio y el estado. b. Convierte el estado obtenido de la base de datos al tipo IEstadoPizza usando un método de la clase Pedido. c. Crea y retorna un nuevo objeto Pedido con los datos recuperados. 5. Cierra la conexión y retorna el pedido (o null si no se encontró).
     */
    public Pedido buscarPedidoPorId(int idCliente) throws Exception {
        String sql = "SELECT nombrePizza, precioTotal, estado FROM pedidos WHERE idCliente = ?";
        conexion objConexion = new conexion();
        PreparedStatement consultaPreparada = objConexion.establecerConexion().prepareStatement(sql);
        consultaPreparada.setInt(1, idCliente);
        objConexion.Resultado = consultaPreparada.executeQuery();
        Pedido pedido = null;
        if (objConexion.Resultado.next()) {
            // Obtiene datos del pedido desde la consulta
            String nombre = objConexion.Resultado.getString("nombrePizza");
            int precio = objConexion.Resultado.getInt("precioTotal");
            String estadoBD = objConexion.Resultado.getString("estado");

            // Convierte el estado de texto a un objeto IEstadoPizza
            IEstadoPizza estado = Pedido.obtenerEstadoDesdeNombre(estadoBD);
            // Crea un nuevo pedido con los datos obtenidos
            pedido = new Pedido(nombre, precio, estado);
        }
        // Cierra la conexión a la base de datos
        objConexion.cerrarConexion();
        return pedido;
    }

    /**
     * Cambia el estado del pedido recibido según el nuevo estado y actualiza la base de datos.
     *
     * @param pedido El objeto Pedido cuyo estado se desea cambiar.
     * @param nuevoEstado El nuevo estado al que se desea cambiar el pedido ("En preparación" o "Entregado").
     * @param idCliente El ID del cliente asociado al pedido (usado para actualizar el registro en la base de datos).
     * @return true si el cambio de estado y la actualización en la base de datos fueron exitosos; false si la transición no es válida o no hubo cambios.
     * @throws Exception Si ocurre un error durante la conexión o actualización en la base de datos.
     *
     * Funcionamiento: 1. Guarda el estado actual del pedido. 2. Según el valor de nuevoEstado, llama al método correspondiente del pedido para cambiar su estado. 3. Verifica que el estado realmente haya cambiado y que sea igual al nuevo estado solicitado. 4. Si el cambio es válido, actualiza el estado en la base de datos para el cliente dado. 5. Retorna true si todo fue exitoso, o false si la transición fue inválida. 6. Cierra la conexión a la base de datos en todos los casos.
     */
    public boolean cambiarEstadoPedido(Pedido pedido, String nuevoEstado, int idCliente) throws Exception {
        String estadoAntes = pedido.getEstadoNombre();
        conexion objConexion = new conexion();

        // 2. Cambia el estado del pedido según el nuevo estado recibido
        if (nuevoEstado.equals("En preparación")) {
            pedido.enPreparacion();
        } else if (nuevoEstado.equals("Entregado")) {
            pedido.entregado();
        }

        // 3. Verifica que el estado realmente haya cambiado y sea el nuevo solicitado
        if (pedido.getEstadoNombre().equals(nuevoEstado) && !estadoAntes.equals(nuevoEstado)) {

            // 4. Actualiza el estado en la base de datos
            String sqlUpdate = "UPDATE pedidos SET estado = ? WHERE idCliente = ?";
            PreparedStatement psUpdate = objConexion.establecerConexion().prepareStatement(sqlUpdate);
            psUpdate.setString(1, nuevoEstado);
            psUpdate.setInt(2, idCliente);
            psUpdate.executeUpdate();
            objConexion.cerrarConexion();
            return true;
        } else {
            // 5. Si la transición es inválida o no hubo cambio, solo cierra la conexión y retorna false
            objConexion.cerrarConexion();
            return false;
        }
    }
}
