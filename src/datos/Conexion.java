/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    public static Conexion instance;
    private Connection conexion;
    private final String USER = "root";
    private final String PASSWORD = "admin123";//cambiar
    private final String SERVER = "localhost:3306";
    private final String BBDD = "Prueba3";

    
    private Conexion(){
        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://" + SERVER + "/" + BBDD;
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Problemas con la conexión a la base de datos" 
                    + "\nContáctese con el adminstrador:\n" + 
                    ex.getMessage(), "Mensajes", JOptionPane.ERROR_MESSAGE);
            System.out.println("ex: "+ex);
        }
    }

    public synchronized static Conexion obtenerConexion(){
        if(instance == null)
        {
            instance = new Conexion();
        }
        return instance;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void cerrarConexion(){
        instance = null;
    }
}
