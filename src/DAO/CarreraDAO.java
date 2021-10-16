package DAO;

import modelo.Carrera;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarreraDAO extends Conexion {

    private final String insertar = "INSERT INTO carrera (`codigo_carrera`, `nombre_carrera`,`duracion_carrera_anios`) VALUES (?,?,?)";
    private final String seleccionar = "SELECT * FROM carrera";
    private final String borrar = "DELETE FROM carrera WHERE `codigo_carrera` =?";
    private final String actualizar = "UPDATE carrera SET `nombre_carrera`=?,`duracion_carrera_anios`=? WHERE `codigo_carrera`=?";
    private final String encontrar = "SELECT * FROM carrera WHERE codigo_carrera=?";

    public boolean crear(Carrera carrera) {       

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertar);

            ps.setInt(1, carrera.getCodigoCarrera());
            ps.setString(2, carrera.getNombre());
            ps.setString(3, carrera.getDuracion());

           if (ps.executeUpdate()==0) {
                System.out.println("Puede que no se haya guardado.");
            }

            System.out.println("Agregado Con Exito");

            return true;
        } catch (SQLException e) {
            System.out.println("Error al Crear : " + e);
            return false;

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
        }
    }

    public List<Carrera> leer() {      
        Carrera carrera;
        List<Carrera> listaCarreras = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(seleccionar);
            rs = ps.executeQuery();

            while (rs.next()) {
                carrera = new Carrera();

                carrera.setCodigoCarrera(rs.getInt(1));
                carrera.setNombre(rs.getString(2));
                carrera.setDuracion(rs.getString(3));

                listaCarreras.add(carrera);

            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);          
        }
        return listaCarreras;
    }

    public boolean actualizar(Carrera carrera) {
        
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(actualizar);

            ps.setString(1, carrera.getNombre());
            ps.setString(2, carrera.getDuracion());

            ps.setInt(3, carrera.getCodigoCarrera());
            ps.executeUpdate();
            System.out.println("Actualizado Con Exito");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al Actualizar : " + e);
            return false;

        } finally {

            Conexion.close(conn);
            Conexion.close(ps);

        }
    }

    public boolean borrar(int codCarrera) {      

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(borrar);
            ps.setInt(1, codCarrera);
            ps.executeUpdate();
            System.out.println("Eliminado con Exito");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al Eliminar : " + e);
            return false;

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);

        }
    }

    public Carrera encontrar(int codCarrera) {
      
        Carrera carrera = new Carrera();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            ps.setInt(1, codCarrera);
            rs = ps.executeQuery();

            carrera.setCodigoCarrera(rs.getInt(1));
            carrera.setNombre(rs.getString(2));
            carrera.setDuracion(rs.getString(3));

        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);          

        }

        return carrera;
    }

    public boolean exist(int codCarrera) {
       
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            ps.setInt(1, codCarrera);
            rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return false;
    }

}
