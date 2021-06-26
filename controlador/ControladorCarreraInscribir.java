package controlador;

import DAO.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import vista.*;
import modelo.*;

/**
 *
 * @author Elpo
 */
public class ControladorCarreraInscribir implements ActionListener {

    private Inscripcion_Carreras_Agregar vista;
    private Inscripcion inscri = new Inscripcion();   
    private Carrera carr = new Carrera();  
    private Alumno alum = new Alumno();

    public ControladorCarreraInscribir(Inscripcion_Carreras_Agregar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnAgregar().addActionListener(this);
        cargarAmbosComboBox();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            inscri = null;
            carr=null;
        }

        if (e.getSource().equals(vista.getBtnAgregar())) {
            agregar();
        }
    }

    public void agregar() {
        //Hago validaciones correspondientes
        if (validaCamposObligatorios() == false) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
        } else if (Validaciones.validaCodigoInscripcionCarrera(vista.getTxtCodInscripcion().getText()) == false) {
            JOptionPane.showMessageDialog(null, "El codigo de inscripcion no puede ser menor a 1 ni mayor a 9999.");
        } else if (inscri.encuentraInscripcion(Integer.valueOf(vista.getTxtCodInscripcion().getText())) == true) {
            JOptionPane.showMessageDialog(null, "Ese codigo de inscripcion ya ha sido creado.");
        } else {
            int codigo_insc = Integer.valueOf(vista.getTxtCodInscripcion().getText());

            //Si los datos cargados estan bien. Se carga la materia
            inscri.setCodigoInscripcion(codigo_insc);
            inscri.setCodigoCarrera(agarraCodigoDeCarrera(vista.getCbxCarreras().getSelectedItem().toString()));
            inscri.setNombre(vista.getCbxAlumnos().getSelectedItem().toString());
            inscri.setFecha(Date.valueOf(vista.getDateChooser().getText()));

            if (inscri.creaInscripcion(inscri)) {
                //busco al alumno y le asigno el codigo de inscripcion
                int dni_alu = agarraDniDeAlumno(vista.getCbxAlumnos().getSelectedItem().toString());
                alum = buscaAlAlmumnoParaInscribirlo(dni_alu);
                alum.setCodigoInscripcionAcarrera(codigo_insc);
                alum.actualizaCarreraaAlumno(alum);
                
                JOptionPane.showMessageDialog(null, "Guardado Con Exito!");
                vista.getTxtCodInscripcion().setText("");
                vista.getCbxAlumnos().setSelectedIndex(0);
                vista.getCbxCarreras().setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta");
            }
        }

    }

    public void cargarAmbosComboBox() {
        //List<Alumno> listaAlumnos = alumnoDAO.leer();
        List<Alumno> listaAlumnos = alum.leeAlumnos();
        
        
        //List<Carrera> listaCarreras = carreraDao.leer();
        List<Carrera> listaCarreras = carr.listaCarreras();
       

        vista.getCbxAlumnos().removeAllItems();
        vista.getCbxAlumnos().addItem("Seleccionar Alumno");
        vista.getCbxCarreras().removeAllItems();
        vista.getCbxCarreras().addItem("Seleccionar Carrera");

        for (Alumno la : listaAlumnos) {
            vista.getCbxAlumnos().addItem(la.getNombre() + "-" + la.getApellido() + "-" + la.getDni());
        }

        for (Carrera lc : listaCarreras) {
            vista.getCbxCarreras().addItem(lc.getNombre() + "-" + lc.getCodigoCarrera());
        }
    }

    public int agarraCodigoDeCarrera(String nombre) {
        String partes[] = nombre.split("-");

        int resul = Integer.valueOf(partes[1]);
        return resul;
    }

    public int agarraDniDeAlumno(String nombre) {
        String partes[] = nombre.split("-");

        int resul = Integer.valueOf(partes[2]);
        return resul;
    }

    public Alumno buscaAlAlmumnoParaInscribirlo(int dni) {
        return alum.encuentraAlumno(dni);
    }

    public boolean validaCamposObligatorios() {
        if (vista.getTxtCodInscripcion().getText().isEmpty()
                || vista.getCbxAlumnos().getSelectedItem().equals("Seleccionar Alumno")
                || vista.getCbxCarreras().getSelectedItem().equals("Seleccionar Carrera")) {
            return false;
        } else {
            return true;
        }
    }

}
