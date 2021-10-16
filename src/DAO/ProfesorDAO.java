package DAO;

import modelo.Profesor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProfesorDAO extends Conexion {

    private final String insertar = "INSERT INTO profesor (prof_dni, prof_nombre,prof_apellido,prof_fec_nac) VALUES (?,?,?,?)";
    private final String seleccionar = "SELECT * FROM profesor";
    private final String borrar = "DELETE FROM profesor WHERE prof_dni=?";
    private final String actualiar = "UPDATE profesor SET prof_nombre=?,prof_apellido=?,prof_fec_nac=? WHERE prof_dni=?";
    private final String encontrar = "SELECT * FROM profesor WHERE prof_dni = ?";
   

    public boolean crear(Profesor profesor) {       

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertar);

            ps.setInt(1, profesor.getDni());
            ps.setString(2, profesor.getNombre());
            ps.setString(3, profesor.getApellido());
            ps.setDate(4, profesor.getFechaNacimiento());            

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

    public List<Profesor> leer() {       
        Profesor profesor;
        List<Profesor> listaProfesores = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(seleccionar);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                profesor = new Profesor();

                profesor.setDni(rs.getInt(1));
                profesor.setNombre(rs.getString(2));
                profesor.setApellido(rs.getString(3));
                profesor.setFechaNacimiento(rs.getDate(4));                

                listaProfesores.add(profesor);

            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
            profesor=null;
        }
        return listaProfesores;
    }

    public boolean actualizar(Profesor profesor) {
      
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(actualiar);

            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setDate(3, profesor.getFechaNacimiento());           

            ps.setInt(4, profesor.getDni());
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

    public boolean borrar(int idProf) {
       
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(borrar);
            ps.setInt(1, idProf);
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

    public boolean buscar(int dniProf) {
     
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            ps.setInt(1, dniProf);
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
