package modelo;
import DAO.CarreraDAO;
import java.util.List;


public class Carrera {

    private int codigoCarrera;
    private String nombre;
    private String duracion;
    private CarreraDAO carreraDAO = new CarreraDAO();

    public Carrera() {
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

       public boolean creaCarrera(Carrera carrera) {
        return carreraDAO.crear(carrera);
    }

    public List<Carrera> listaCarreras() {
        return carreraDAO.leer();
    }
   
    public boolean actualizaCarrera(Carrera carrera) {
        return carreraDAO.actualizar(carrera);
    }

    public boolean borraCarrera(int idCarrera) {
        return carreraDAO.borrar(idCarrera);
    }

    public Carrera encuentraCarrera(int idCarrera) {
        return carreraDAO.encontrar(idCarrera);
    }

    public boolean carreraExist(int codCarrera) {
        return carreraDAO.exist(codCarrera);
    }
}
