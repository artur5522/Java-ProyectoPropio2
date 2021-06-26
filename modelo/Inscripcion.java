package modelo;

import DAO.InscripcionDAO;
import java.sql.Date;
import java.util.List;


public class Inscripcion {

    private int codigoInscripcion; //falta optimizar. deberia ser arraylist 
    private String nombre;
    private Date fecha;
    private int codigoCarrera; 
    private InscripcionDAO inscripcionDAO = new InscripcionDAO();

    public Inscripcion() {
    }

    public Inscripcion(int codigoInscripcion, String nombre, Date fecha, int codigoCarrera) {
        this.codigoInscripcion = codigoInscripcion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.codigoCarrera = codigoCarrera;
    }

    public int getCodigoInscripcion() {
        return codigoInscripcion;
    }

    public InscripcionDAO getInscripcionDAO() {
        return inscripcionDAO;
    }

    public void setInscripcionDAO(InscripcionDAO inscripcionDAO) {
        this.inscripcionDAO = inscripcionDAO;
    }

    public void setCodigoInscripcion(int codigoInscripcion) {
        this.codigoInscripcion = codigoInscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public boolean creaInscripcion(Inscripcion inscripcion) {
        return inscripcionDAO.crear(inscripcion);
    }

   
    public List<Inscripcion> listaInscripciones() {
        return inscripcionDAO.leer();
    }

    public boolean borrarInscripcion(int idInscripcion) {
        return inscripcionDAO.borrar(idInscripcion);
    }
    
     public boolean encuentraInscripcion(int codMate) {
        return inscripcionDAO.buscar(codMate);
    }
     
       public Inscripcion encuentraYtraeInscripcion(int codigo) {
        return inscripcionDAO.traeInscripcion(codigo);
    }

}
