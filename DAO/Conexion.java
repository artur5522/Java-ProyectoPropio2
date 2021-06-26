package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    
    
    private static final String DATA_BASE = "rodriguez";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATA_BASE;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "datos55";
    

    public static Connection getConnection() {
        Connection conexion = null;

        try {
           
            conexion = DriverManager.getConnection(URL, USER_NAME, PASSWORD);            

        } catch (SQLException e) {
            System.err.println("Error De Conexión! :  " + e);
            JOptionPane.showMessageDialog(null, "Error De Conexión A Base De Datos!\n " + e);
            System.exit(0);

        }

        return conexion;

    }


    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    

}
