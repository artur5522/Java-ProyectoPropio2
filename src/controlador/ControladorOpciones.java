package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.*;

public class ControladorOpciones implements ActionListener {

    private opciones options;
    private int numero_operacion;

    //asigno su vista al controlador, y pongo a la escucha los botones
    public ControladorOpciones(opciones a, String titulo, int numero) {
        this.numero_operacion = numero;
        this.options = a;
        this.options.setTitle(titulo);

        this.options.btnAgregar.addActionListener(this);
        this.options.btnConsultar.addActionListener(this);
        this.options.btnEditar.addActionListener(this);
        this.options.btnEliminar.addActionListener(this);
        this.options.btnVolver.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        // en base al boton apretado es a que vista me dirigirá
        if (e.getSource().equals(options.btnVolver)) {
            options.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            options = null;
        } //CONSULTAR
        else if (e.getSource().equals(options.btnConsultar) && numero_operacion == 1) {
            options.setVisible(false);
            Alumno_Consultar ac = new Alumno_Consultar();
            ControladorAlumnoConsultar cp = new ControladorAlumnoConsultar(ac, "LISTADO DE ALUMNOS");
            cp.muestra();
            ac.setVisible(true);
            ac.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnConsultar) && numero_operacion == 2) {
            options.setVisible(false);
            Profesor_Consultar Pc = new Profesor_Consultar();
            ControladorProfesorConsultar cp = new ControladorProfesorConsultar(Pc, "LISTADO DE PROFESORES");
            cp.muestra();
            Pc.setVisible(true);
            Pc.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnConsultar) && numero_operacion == 3) {
            options.setVisible(false);
            Materia_Consultar mc = new Materia_Consultar();
            ControladorMateriasConsultar cp = new ControladorMateriasConsultar(mc, "LISTADO DE MATERIAS");
            cp.muestra();
            mc.setVisible(true);
            mc.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnConsultar) && numero_operacion == 4) {
            options.setVisible(false);
            Carrera_Consultar ca = new Carrera_Consultar();
            ControladorCarrerasConsultar cp = new ControladorCarrerasConsultar(ca, "LISTADO DE CARRERA");
            cp.muestra();
            ca.setVisible(true);
            ca.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnConsultar) && numero_operacion == 5) {
            options.setVisible(false);
            Inscripcion_Carreras_Consultar cc = new Inscripcion_Carreras_Consultar();
            ControladorInscripcionCarrerasConsultar cp = new ControladorInscripcionCarrerasConsultar(cc, "LISTADO DE ALUMNOS INSCRIPTOS A CARRERAS");
            cp.muestra();
            cc.setVisible(true);
            cc.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnConsultar) && numero_operacion == 6) {
            options.setVisible(false);
            Inscripcion_Cursado_Consultar ic = new Inscripcion_Cursado_Consultar();
            ControladorInscripcionMateriasConsultar cp = new ControladorInscripcionMateriasConsultar(ic, "LISTADO DE ALUMNOS INSCRIPTOS A MATERIAS");
            cp.muestra();
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } //AGREGAR
        else if (e.getSource().equals(options.btnAgregar) && numero_operacion == 1) {
            options.setVisible(false);
            Alumno_Agregar aa = new Alumno_Agregar();
            ControladorAlumnoAgregar ca = new ControladorAlumnoAgregar(aa, "INSCRIPCION DE ALUMNOS");
            aa.setVisible(true);
            aa.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnAgregar) && numero_operacion == 2) {
            options.setVisible(false);
            Profesor_Agregar ca = new Profesor_Agregar();
            ControladorProfesorAgregar cp = new ControladorProfesorAgregar(ca, "INSCRIPCION DE PROFESORES");
            ca.setVisible(true);
            ca.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnAgregar) && numero_operacion == 3) {
            options.setVisible(false);
            Materia_Agregar ma = new Materia_Agregar();
            ControladorMateriasAgregar cp = new ControladorMateriasAgregar(ma, "CREACION DE MATERIAS");
            ma.setVisible(true);
            ma.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnAgregar) && numero_operacion == 4) {
            options.setVisible(false);
            Carrera_Agregar ic = new Carrera_Agregar();
            ControladorCarreraAgregar cp = new ControladorCarreraAgregar(ic, "CREACION DE CARRERAS");
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnAgregar) && numero_operacion == 5) {
            options.setVisible(false);
            Inscripcion_Carreras_Agregar ic = new Inscripcion_Carreras_Agregar();
            ControladorCarreraInscribir cp = new ControladorCarreraInscribir(ic, "INSCRIPCION A CARRERA");
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnAgregar) && numero_operacion == 6) {
            options.setVisible(false);
            Inscripcion_Cursado_Agregar ic = new Inscripcion_Cursado_Agregar();
            ControladorMateriasInscribir cp = new ControladorMateriasInscribir(ic, "INCRIPCION A MATERIA");
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } //BORRAR
        else if (e.getSource().equals(options.btnEliminar) && numero_operacion == 1) {
            options.setVisible(false);
            Alumno_Eliminar ic = new Alumno_Eliminar();
            ControladorAlumnoEliminar cp = new ControladorAlumnoEliminar(ic, "ELIMINACION DE ALUMNOS");
            cp.muestra();
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEliminar) && numero_operacion == 2) {
            options.setVisible(false);
            Profesor_Eliminar ic = new Profesor_Eliminar();
            ControladorProfesorEliminar cp = new ControladorProfesorEliminar(ic, "ELIMINACION DE PROFESORES");
            cp.muestra();
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEliminar) && numero_operacion == 3) {
            options.setVisible(false);
            Materia_Eliminar ic = new Materia_Eliminar();
            ControladorMateriaEliminar cp = new ControladorMateriaEliminar(ic, "ELIMINACION DE MATERIAS");
            cp.muestra();
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEliminar) && numero_operacion == 4) {
            options.setVisible(false);
            Carrera_Eliminar ic = new Carrera_Eliminar();
            ControladorCarrerasEliminar cp = new ControladorCarrerasEliminar(ic, "ELIMINACION DE CARRERAS");
            cp.muestra();
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEliminar) && numero_operacion == 6) {
            options.setVisible(false);
            Inscripcion_Cursado_Eliminar ic = new Inscripcion_Cursado_Eliminar();
            ControladorInscripcionMateriasEliminar cp = new ControladorInscripcionMateriasEliminar(ic, "ELIMINACION DE INSCRIPCION DE MATERIAS");
            cp.muestra();
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEliminar) && numero_operacion == 5) {
            options.setVisible(false);
            Inscripcion_Carreras_Eliminar ic = new Inscripcion_Carreras_Eliminar();
            ControladorInscripcionCarrerasEliminar cp = new ControladorInscripcionCarrerasEliminar(ic, "ELIMINACION DE INSCRIPCION DE CARRERAS");
            cp.muestra();
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } //EDITAR
        else if (e.getSource().equals(options.btnEditar) && numero_operacion == 1) {
            options.setVisible(false);
            Alumno_Editar ic = new Alumno_Editar();
            ControladorAlumnoEditar cp = new ControladorAlumnoEditar(ic, "EDICION DE ALUMNOS");
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEditar) && numero_operacion == 2) {
            options.setVisible(false);
            Profesor_Editar ic = new Profesor_Editar();
            ControladorProfesorEditar cp = new ControladorProfesorEditar(ic, "EDICION DE PROFESORES");
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEditar) && numero_operacion == 3) {
            options.setVisible(false);
            Materia_Editar ic = new Materia_Editar();
            ControladorMateriasEditar cp = new ControladorMateriasEditar(ic, "EDICION DE MATERIAS");
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEditar) && numero_operacion == 4) {
            options.setVisible(false);
            Carrera_Editar ic = new Carrera_Editar();
            ControladorCarreraEditar cp = new ControladorCarreraEditar(ic, "EDICION DE CARRERAS");
            ic.setVisible(true);
            ic.setLocationRelativeTo(null);
            options = null;
        } else if (e.getSource().equals(options.btnEditar) && numero_operacion == 6) {
            JOptionPane.showMessageDialog(null, "Las inscripciones a cursados no pueden editarse"
                    + ". Debe borrar la inscripcion y crear una nueva");
        } else if (e.getSource().equals(options.btnEditar) && numero_operacion == 5) {
            JOptionPane.showMessageDialog(null, "Las inscripciones a carreras no pueden editarse"
                    + ". Debe borrar la inscripcion y crear una nueva");
        }

    }

}
