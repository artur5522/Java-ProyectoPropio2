package modelo;

import DAO.MateriaDAO;
import java.util.List;


public class Materia {

    private int codMateria;
    private String nombreMateria;
    private int dniProfesor;
    private MateriaDAO materiaDAO = new MateriaDAO();

    public Materia() {
    }

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(int dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public MateriaDAO getMateriaDAO() {
        return materiaDAO;
    }

    public void setMateriaDAO(MateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }


    public boolean creaMateria(Materia materia) {
        return materiaDAO.crear(materia);
    }


    public List<Materia> listaMaterias() {
        return materiaDAO.leer();
    }

   
    public boolean actualizaMateria(Materia materia) {
        return materiaDAO.actualizar(materia);
    }
   
    public boolean borraMateria(int idMateria) {
        return materiaDAO.borrar(idMateria);
    }

    public boolean materiaExist(int codMate) {
        return materiaDAO.buscar(codMate);
    }
    
      
   
}
