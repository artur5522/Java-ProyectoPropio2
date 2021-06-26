package modelo;

import DAO.ProfesorDAO;
import java.sql.Date;
import java.util.List;


public class Profesor {

    private int dni;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;    
    private ProfesorDAO profesorDAO = new ProfesorDAO();

    public Profesor() {
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public boolean creaProfesor(Profesor profesor) {
        return profesorDAO.crear(profesor);
    }

   
    public List<Profesor> listaProfesores() {
        return profesorDAO.leer();
    }

    public boolean actualizaProfesores(Profesor profesor) {
        return profesorDAO.actualizar(profesor);
    }
 
    public boolean borraProfesor(int idProfesor) {
        return profesorDAO.borrar(idProfesor);
    }
 
    public boolean profesorExist(int dniProf) {
        return profesorDAO.buscar(dniProf);
    }
}
