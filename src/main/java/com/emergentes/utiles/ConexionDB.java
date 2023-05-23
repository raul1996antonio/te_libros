package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConexionDB {

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/bd_biblio";
    static String usuario = "root";
    static String password = "root";

    Connection conn = null;

    public ConexionDB() throws SQLException {
        try {
            Class.forName(driver);
            conn =  DriverManager.getConnection(url, url, password);
            if (conn != null) {
                System.out.println("Conexion OK: " + conn);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error en Driver " + e.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error de SQL " + ex.getMessage());
        }
    }
    public Connection conectar() {
        return conn;
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la BD");
        }
    }

    private static class Conection {

        public Conection() {
        }

        private void close() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }


}
