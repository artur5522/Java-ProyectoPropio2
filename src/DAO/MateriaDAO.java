package DAO;

import modelo.Materia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO extends Conexion {

    private final String insertar = "INSERT INTO materia (mat_cod,mat_nombre, mat_prof_dni) VALUES (?,?,?)";
    private final String seleccionar = "SELECT * FROM materia";
    private final String borrar = "DELETE FROM materia WHERE mat_cod=?";
    private final String actualizar = "UPDATE materia SET mat_nombre =?, mat_prof_dni=? WHERE mat_cod=?";
    private final String encontrar = "SELECT * FROM materia WHERE mat_cod =?";
   
    public boolean crear(Materia materia) {

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertar);

            ps.setInt(1, materia.getCodMateria());
            ps.setString(2, materia.getNombreMateria());
            ps.setInt(3, materia.getDniProfesor());

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

    public List<Materia> leer() {
        Materia materia;
        List<Materia> listaMaterias = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(seleccionar);
            rs = ps.executeQuery();

            while (rs.next()) {
                materia = new Materia();

                materia.setCodMateria(rs.getInt(1));
                materia.setNombreMateria(rs.getString(2));
                materia.setDniProfesor(rs.getInt(3));

                listaMaterias.add(materia);
            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
            materia = null;
        }
        return listaMaterias;
    }

    public boolean actualizar(Materia materia) {

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(actualizar);

            ps.setString(1, materia.getNombreMateria());
            ps.setInt(2, materia.getDniProfesor());

            ps.setInt(3, materia.getCodMateria());
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

    public boolean borrar(int idMateria) {

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(borrar);
            ps.setInt(1, idMateria);
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

    public boolean buscar(int codMate) {

        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            ps.setInt(1, codMate);
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
