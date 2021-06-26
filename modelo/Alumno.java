package modelo;

import DAO.AlumnoDAO;
import java.sql.Date;
import java.util.List;


public class Alumno {

    private int dni;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;    
    private int codigoInscripcionAcarrera;//falta optimizar por si el alumno se quiere inscribir a mas de una materia
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public Alumno() {
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

    public int getCodigoInscripcionAcarrera() {
        return codigoInscripcionAcarrera;
    }

    public void setCodigoInscripcionAcarrera(int codigoInscripcionAcarrera) {
        this.codigoInscripcionAcarrera = codigoInscripcionAcarrera;
    }

    public boolean creaAlumno(Alumno alumno) {
        return alumnoDAO.crear(alumno);
    }
 
    public List<Alumno> leeAlumnos() {
        return alumnoDAO.leer();
    }
  
    public boolean actualizaAlumno(Alumno alumno) {
        return alumnoDAO.actualizar(alumno);
    }

    public boolean borraAlumno(int idAlumno) {
        return alumnoDAO.borrar(idAlumno);
    }

    public Alumno encuentraAlumno(int dniAlumno) {
        return alumnoDAO.encontrar(dniAlumno);
    }
    

    public boolean actualizaCarreraaAlumno(Alumno alumno) {
        return alumnoDAO.actualizarCarrera(alumno);
    }

    public boolean alumnoExiste(int dniAlumno) {
        return alumnoDAO.existe(dniAlumno);
    }

}
