package Controller;

import ConexionBs.conexion;
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

    public Pedido buscarPedidoPorId(int idCliente) throws Exception {
        String sql = "SELECT nombrePizza, precioTotal, estado FROM pedidos WHERE idCliente = ?";
        conexion objConexion = new conexion();
        PreparedStatement consultaPreparada = objConexion.establecerConexion().prepareStatement(sql);
        consultaPreparada.setInt(1, idCliente);
        objConexion.Resultado = consultaPreparada.executeQuery();
        Pedido pedido = null;
        if (objConexion.Resultado.next()) {
            String nombre = objConexion.Resultado.getString("nombrePizza");
            int precio = objConexion.Resultado.getInt("precioTotal");
            String estadoBD = objConexion.Resultado.getString("estado");

            IEstadoPizza estado = Pedido.obtenerEstadoDesdeNombre(estadoBD);
            pedido = new Pedido(nombre, precio, estado);
        }

        objConexion.cerrarConexion();
        return pedido;
    }

    
    
    public boolean cambiarEstadoPedido(Pedido pedido, String nuevoEstado, int idCliente) throws Exception {
        String estadoAntes = pedido.getEstadoNombre();
        conexion objConexion = new conexion();
        if (nuevoEstado.equals("En preparación")) {
            pedido.enPreparacion();
        } else if (nuevoEstado.equals("Entregado")) {
            pedido.entregado();
        }
        if (pedido.getEstadoNombre().equals(nuevoEstado) && !estadoAntes.equals(nuevoEstado)) {

            String sqlUpdate = "UPDATE pedidos SET estado = ? WHERE idCliente = ?";
            PreparedStatement psUpdate = objConexion.establecerConexion().prepareStatement(sqlUpdate);
            psUpdate.setString(1, nuevoEstado);
            psUpdate.setInt(2, idCliente);
            psUpdate.executeUpdate();
            objConexion.cerrarConexion();
            return true;
        } else {
            objConexion.cerrarConexion();
            return false;
        }
    }
}
