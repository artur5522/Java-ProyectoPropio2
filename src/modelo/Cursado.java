package modelo;

import DAO.CursadoDAO;
import java.util.List;


public class Cursado {

    private int alumnoDni;
    private int codigoMateria;
    private double nota;
    private long codigo;
    private CursadoDAO cursadoDAO = new CursadoDAO();

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Cursado() {
    }

    public int getAlumnoDni() {
        return alumnoDni;
    }

    public void setAlumnoDni(int alumnoDni) {
        this.alumnoDni = alumnoDni;
    }

    public int getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(int codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

   
    public boolean creaCursado(Cursado cursado) {
        return cursadoDAO.crear(cursado);
    }

   
    public List<Cursado> listaCursado() {
        return cursadoDAO.leer();
    }
  
    public boolean actualizaCursado(Cursado cursado) {
        return cursadoDAO.actualizar(cursado);
    }

      public boolean borraCursado(int idCursado) {
        return cursadoDAO.borrar(idCursado);
    }
  
    public boolean encuentraCursado(int codigo) {
        return cursadoDAO.encontrar(codigo);
    }
    
      public Cursado encuentraYtraeCursado(int codigo) {
        return cursadoDAO.traeCursado(codigo);
    }

}
