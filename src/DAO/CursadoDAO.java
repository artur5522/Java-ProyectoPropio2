package DAO;

import modelo.Cursado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursadoDAO extends Conexion {

    private final String insertar = "INSERT INTO cursado (codigo, cur_alu_dni, cur_mat_cod,cur_nota) VALUES (?,?,?,?)";
    private final String seleccionar = "SELECT * FROM cursado";
    private final String borrar = "DELETE FROM cursado WHERE codigo=?";
    private final String actualizar = "UPDATE cursado SET cur_mat_cod=?, cur_nota=? WHERE codigo=?";
    private final String encontrar = "SELECT * FROM cursado WHERE codigo=?";
    
    public boolean crear(Cursado cursado) {
       
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(insertar);
            ps.setLong(1, cursado.getCodigo());
            ps.setInt(2, cursado.getAlumnoDni());
            ps.setInt(3, cursado.getCodigoMateria());
            ps.setDouble(4, cursado.getNota());

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

    public List<Cursado> leer() {      
        Cursado cursado;
        List<Cursado> listaCursados = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            ps = getConnection().prepareStatement(seleccionar);
            rs = ps.executeQuery();
            while (rs.next()) {
                cursado = new Cursado();                
             
                cursado.setCodigo(rs.getLong(1));
                cursado.setAlumnoDni(rs.getInt(2));
                cursado.setCodigoMateria(rs.getInt(3));
                cursado.setNota(rs.getDouble(4));

                listaCursados.add(cursado);

            }

        } catch (SQLException e) {

            System.out.println(e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);
            cursado=null;
        }
        return listaCursados;
    }

    public boolean actualizar(Cursado cursado) {
      
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(actualizar);            
            
            ps.setInt(1, cursado.getCodigoMateria());
            ps.setDouble(2, cursado.getNota());
            ps.setLong(3, cursado.getCodigo());
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

    public boolean borrar(int codCursado) {
      
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(borrar);
            Long parseado= (long) codCursado;
            ps.setLong(1, parseado);
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

    public boolean encontrar(int codCursado) {
      
        Cursado cursado = new Cursado();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            Long parseado= (long) codCursado;
            ps.setLong(1, parseado);
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
            cursado=null;

        }
        
            return false;
    }
    
       public Cursado traeCursado(int codCursado) {
     
        Cursado cursado = new Cursado();
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(encontrar);
            Long parseado= (long) codCursado;
            ps.setLong(1, parseado);
            rs = ps.executeQuery();   

            cursado.setCodigo(rs.getInt(1));
            cursado.setAlumnoDni(rs.getInt(2));
            cursado.setCodigoMateria(rs.getInt(3));
            cursado.setNota(rs.getInt(4));

        } catch (SQLException e) {
            System.out.println("Error al Buscar : " + e);

        } finally {
            Conexion.close(conn);
            Conexion.close(ps);
            Conexion.close(rs);

        }

        return cursado;
    }

}
