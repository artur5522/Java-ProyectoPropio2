package DAO;

import modelo.Inscripcion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO extends Conexion {

    private final String insertar = "INSERT INTO inscripcion (insc_cod, insc_nombre, insc_fecha, insc_car_cod) VALUES (?,?,?,?)";
    private final String seleccionar = "SELECT * FROM inscripcion";
    private final String borrar = "DELETE FROM inscripcion WHERE insc_cod=?";
    private final String encontrar = "SELECT * FROM inscripcion WHERE insc_cod=?";
    
    public boolean crear(Inscripcion inscripcion) {
       
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertar);

            ps.setInt(1, inscripcion.getCodigoInscripcion());
            ps.setString(2, inscripcion.getNombre());
            ps.setDate(3, inscripcion.getFecha());
            ps.setInt(4, inscripcion.getCodigoCarrera());

            ps.executeUpdate();

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

    public List<Inscripcion> leer() {
       
        Inscripcion inscripcion;
        List<Inscripcion> listaInscripciones = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(seleccionar);
            rs = ps.executeQuery();

            while (rs.next()) {
                inscripcion = new Inscripcion();

                inscripcion.setCodigoInscripcion(rs.getInt(1));
                inscripcion.setNombre(rs.getString(2));
                inscripcion.setFecha(rs.getDate(3));
                inscripcion.setCodigoCarrera(rs.getInt(4));

                listaInscripciones.add(inscripcion);

            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
            inscripcion=null;
        }
        return listaInscripciones;
    }

    public boolean buscar(int codInscrip) {       

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            ps.setInt(1, codInscrip);
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

    public boolean borrar(int codInscripcion) {
       
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(borrar);
            ps.setInt(1, codInscripcion);
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

    public Inscripcion traeInscripcion(int codInscri) {
      
        Inscripcion inscri = new Inscripcion();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            ps.setInt(1, codInscri);
            rs = ps.executeQuery();
            while (rs.next()) {
                inscri.setCodigoInscripcion(rs.getInt(1));
                inscri.setNombre(rs.getString(2));
                inscri.setFecha(rs.getDate(3));
                inscri.setCodigoCarrera(rs.getInt(4));

            }

        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return inscri;
    }

}
