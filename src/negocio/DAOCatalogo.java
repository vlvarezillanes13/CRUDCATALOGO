package negocio;

import datos.Conexion;
import entidades.Catalogo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class DAOCatalogo {

    private final String selectAll = "select * from Proyecto";
    private final String insert = "insert into Proyecto (numeroInterno,nombreAplicacion,version,plataforma,fechaInicio,fechaEntrega,urlGit) VALUES (?,?,?,?,?,?,?)";
    private final String update = "update Proyecto set nombreAplicacion=?,version=?,plataforma=?,fechaInicio=?,fechaEntrega=?,urlGit=? where numeroInterno=? ";
    private final String delete = "delete from Proyecto where numeroInterno=?";

    private static final Conexion CONEXION = Conexion.obtenerConexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<Catalogo> listarTodo() {
        try {
            ArrayList<Catalogo> listaCatalogo = new ArrayList<>();
            ps = CONEXION.getConexion().prepareStatement(selectAll);
            rs = ps.executeQuery();

            while (rs.next()) {
                Catalogo cat = new Catalogo();
                cat.setNumeroInterno(rs.getFloat("numeroInterno"));
                cat.setNombreAplicaion(rs.getString("nombreAplicacion"));
                cat.setVersion( rs.getFloat("Version"));
                cat.setPlataforma(rs.getString("plataforma"));
                cat.setFechaInicio(rs.getDate("fechaInicio"));
                cat.setFechaTermino(rs.getDate("fechaEntrega"));
                cat.setUrlGit(rs.getString("urlGit"));
                listaCatalogo.add(cat);
            }

            return listaCatalogo;
        } catch (SQLException ex) {
            System.out.println("Error obtener catalogos: " + ex);
            return null;
        } finally {
            CONEXION.cerrarConexion();
        }
    }

    public boolean agregar(Catalogo cat) {
        try {
            ps = CONEXION.getConexion().prepareStatement(insert);
            ps.setFloat(1, cat.getNumeroInterno());
            ps.setString(2, cat.getNombreAplicaion());
            ps.setFloat(3, cat.getVersion());
            ps.setString(4, cat.getPlataforma());
            
            java.sql.Date sqlDateInicio = new java.sql.Date(cat.getFechaInicio().getTime()); 
            ps.setDate(5, sqlDateInicio);
            
            java.sql.Date sqlDateTermino = new java.sql.Date(cat.getFechaTermino().getTime()); 
            ps.setDate(6, sqlDateTermino);
            
            ps.setString(7, cat.getUrlGit());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensajes", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            CONEXION.cerrarConexion();
        }
    }

    public boolean modificar(Catalogo cat) {
        try {
            ps = CONEXION.getConexion().prepareStatement(update);
            
            ps.setString(1, cat.getNombreAplicaion());
            ps.setFloat(2, cat.getVersion());
            ps.setString(3, cat.getPlataforma());
            
            java.sql.Date sqlDateInicio = new java.sql.Date(cat.getFechaInicio().getTime()); 
            ps.setDate(4, sqlDateInicio);
            
            java.sql.Date sqlDateTermino = new java.sql.Date(cat.getFechaTermino().getTime()); 
            ps.setDate(5, sqlDateTermino);
            
            ps.setString(6, cat.getUrlGit());
            ps.setFloat(7, cat.getNumeroInterno());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensajes", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            CONEXION.cerrarConexion();
        }
    }

    public boolean eliminar(Catalogo cat) {
        try {

            ps = CONEXION.getConexion().prepareStatement(delete);
            ps.setFloat(1, cat.getNumeroInterno());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensajes", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            CONEXION.cerrarConexion();
        }
    }
    

}
