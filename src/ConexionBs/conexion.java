package ConexionBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {

    String bd = "bsPizzeria";
    String url = "jdbc:mysql://localhost:3306/" + bd;
    String usuario = "root";
    String contraseña = "Dracogamer90$";

    Connection estadoConexion = null;

    public Statement sentencia;
    public ResultSet Resultado;
    
    public Connection establecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            estadoConexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion exitosa con la base de datos" + bd);
        } catch (ClassNotFoundException | SQLException error) {
            System.out.println("Error: " + error.getMessage());
        }
        return estadoConexion;
    }

    public void cerrarConexion() {
        try {
            if (estadoConexion != null) {
                estadoConexion.close();
                System.out.println("Conexion cerrada");
            }
        } catch (Exception e) {
            System.err.println("Error");
        }
    }

    public String getBd() {
        return bd;
    }

}
