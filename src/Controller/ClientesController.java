
package Controller;
import ConexionBs.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientesController {
    private conexion conexionDb;

    public ClientesController(conexion conexionDb) {
        this.conexionDb = conexionDb;
    }

    public int guardarClienteYObtenerId(String nombre) {
        Connection con = conexionDb.establecerConexion();
        String sql = "INSERT INTO clientes (nombre) VALUES (?)";
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
        } finally {
            conexionDb.cerrarConexion();
        }
        return -1;
    }
}
