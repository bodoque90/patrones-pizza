
package Controller;

import ConexionBs.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class pedidosController {
    
    private conexion conexionDb;

    public pedidosController(conexion conexionDb) {
        this.conexionDb = conexionDb;
    }

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
}
