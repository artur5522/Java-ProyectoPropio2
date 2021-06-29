package DAO;

import modelo.Alumno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AlumnoDAO extends Conexion {

    //defino mis sentencias SQL para las consultas
    private final String insertar = "INSERT INTO alumno (alumno_dni, alumno_nombre,alumno_apellido,alumno_fec_nac) VALUES (?,?,?,?)";
    private final String seleccionar = "SELECT * FROM alumno";
    private final String borrar = "DELETE FROM alumno WHERE alumno_dni=?";
    private final String actualizar = "UPDATE alumno SET alumno_nombre=?,alumno_apellido=?,alumno_fec_nac=? WHERE alumno_dni=?";
    private final String actualizar_carrera = "UPDATE alumno SET alu_insc_cod=? WHERE alumno_dni=?";
    private final String encontrar = "SELECT * FROM alumno WHERE alumno_dni=?";
    

    public boolean crear(Alumno alumno) {

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertar);

            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setDate(4, alumno.getFechaNacimiento());
            //ps.setInt(5, alumno.getCodigoInscripcionAcarrera());

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

    public List<Alumno> leer() {      
       
        Alumno alumno;
        List<Alumno> listaAlumnos = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(seleccionar);
            rs = ps.executeQuery();

            while (rs.next()) {
                alumno = new Alumno();

                alumno.setDni(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setApellido(rs.getString(3));
                alumno.setFechaNacimiento(rs.getDate(4));

                alumno.setCodigoInscripcionAcarrera(rs.getInt(5));
                listaAlumnos.add(alumno);

            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
            
        }
        return listaAlumnos;
    }

    public boolean actualizar(Alumno alumno) {
      
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(actualizar);

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, alumno.getFechaNacimiento());
            //ps.setInt(4, alumno.getCodigoInscripcionAmateria());

            ps.setInt(4, alumno.getDni());
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

    public boolean actualizarCarrera(Alumno alumno) {      

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(actualizar_carrera);

            ps.setInt(1, alumno.getCodigoInscripcionAcarrera());

            ps.setInt(2, alumno.getDni());
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

    public boolean borrar(int idAlumno) {
       
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(borrar);
            ps.setInt(1, idAlumno);
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

    public Alumno encontrar(int dniAlumno) {
     
        Alumno alumno = new Alumno();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            ps.setInt(1, dniAlumno);
            rs = ps.executeQuery();

            while (rs.next()) {
                alumno.setDni(rs.getInt(1));
                alumno.setNombre(rs.getString(2));
                alumno.setApellido(rs.getString(3));
                alumno.setFechaNacimiento(rs.getDate(4));
                alumno.setCodigoInscripcionAcarrera(rs.getInt(5));
            //return alumno;
            }


        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return alumno;
    }

    public boolean existe(int dniAlumno) {
      
        boolean bandera = false;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            ps.setInt(1, dniAlumno);
            rs = ps.executeQuery();
            /*si tiene un next significa que encontró un resultado
            por ende, el alumno ya existe*/
            while (rs.next()) {
                bandera = true;
                return bandera;
            }

        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return bandera;
    }

}
