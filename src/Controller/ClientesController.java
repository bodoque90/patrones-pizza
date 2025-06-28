package Controller;

import Conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientesController {

    private conexion conexionDb;

    public ClientesController(conexion conexionDb) {
        this.conexionDb = conexionDb;
    }

    /**
     * Guarda un nuevo cliente en la base de datos y devuelve el ID generado.
     *
     * @param nombre Nombre del cliente a guardar.
     * @return El ID generado para el nuevo cliente si la operación es exitosa, -1 si ocurre algún error.
     *
     * Funcionamiento: 1. Establece conexión a la base de datos. 2. Prepara la sentencia SQL para insertar un nuevo cliente. 3. Ejecuta la inserción y obtiene la clave primaria generada automáticamente. 4. Si la inserción es exitosa, retorna el ID del nuevo cliente. 5. Si ocurre un error, muestra el mensaje de error y retorna -1. 6. Cierra la conexión a la base de datos en el bloque finally.
     */
    public int guardarClienteYObtenerId(String nombre) {
        Connection con = conexionDb.establecerConexion();
        String sql = "INSERT INTO cliente (nombre) VALUES (?)";
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Retorna el ID generado automáticamente
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        } finally {
            conexionDb.cerrarConexion();
        }
        return -1; // Retorna -1 si hubo algún problema
    }

    /**
     *
     * @param int idCliente
     * @return si el la id del cliente se encuentra en la base de datos devuelve el nombre del cliente sino da mensaje de error al buscarlos
     */
    public String obtenerNombreClientePorId(int idCliente) {
        String nombre = null;
        try {
            Connection conn = conexionDb.establecerConexion();
            String sql = "SELECT nombre FROM cliente WHERE idCliente = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al obtener nombre del cliente: " + e.getMessage());
        }
        return nombre;
    }

}
