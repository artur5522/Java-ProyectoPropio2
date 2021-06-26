    package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 * @author Elpo
 */
public class ControladorAlumnoEliminar implements ActionListener {

    private Alumno_Eliminar vista;
    private DefaultTableModel modelo;
    private Alumno alu = new Alumno();   
    private Inscripcion inscri= new Inscripcion();
    private Cursado cur = new Cursado();
    private List<Cursado> listaCursados = new ArrayList();
 

    public ControladorAlumnoEliminar(Alumno_Eliminar vistaAlumno, String titulo) {
        this.vista = vistaAlumno;
        vista.setTitle(titulo);
        vista.getBtnVolver().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            alu = null;
            inscri=null;
            listaCursados=null;
            cur=null;
        }
        if (e.getSource().equals(vista.getBtnEliminar())) {
            eliminar();
        }
    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaAlumnos().getModel();
        vista.getJtConsultaAlumnos().setRowHeight(25);
        List<Alumno> lista = alu.leeAlumnos();
        Object[] fila = new Object[5];

        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDni();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getApellido();
            fila[3] = lista.get(i).getFechaNacimiento();
            fila[4] = lista.get(i).getCodigoInscripcionAcarrera();
            modelo.addRow(fila);
        }
        vista.getJtConsultaAlumnos().setModel(modelo);
    }

    public void eliminar() {
        int dni_alumno = Integer.valueOf(vista.getTxtBorrar().getText());
        if (!validaCampoObligatorio()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el DNI del alumno a eliminar");           
        } else if (!verificaDni(dni_alumno)) {
            JOptionPane.showMessageDialog(null, "No existe alumno con ese DNI");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea Elimnar? Esto eliminará tambien sus inscripcinoes.", "Seleccione Una Opc.", JOptionPane.YES_NO_OPTION) == 0) {
                
                //busco todos los cursados existentes
                listaCursados=cur.listaCursado();                
                for (Cursado lc : listaCursados) {
                    //por cada cursado que coincide con el DNI de alumno, lo borro
                    if (lc.getAlumnoDni() == dni_alumno) {
                        cur=lc;
                        cur.borraCursado((int)cur.getCodigo());
                    }
                }
                //traigo al alumno para poder obtener su codigo de inscripcion
                alu = alu.encuentraAlumno(dni_alumno);                
                int codigo_inscr= alu.getCodigoInscripcionAcarrera();
                //borro la inscripcion
                inscri = inscri.encuentraYtraeInscripcion(codigo_inscr);                
             
                inscri.borrarInscripcion(inscri.getCodigoInscripcion());
                alu.borraAlumno(dni_alumno);
                JOptionPane.showMessageDialog(null, "Alumno eliminado Con Exito!");
                JOptionPane.showMessageDialog(null, "Todas sus inscripciones a materias se han eliminado."); 
                JOptionPane.showMessageDialog(null, "Para ver reflejado los cambios, vuelva a entrar desde el menu princial");

            }
        }
    }

    public boolean validaCampoObligatorio() {
        if (vista.getTxtBorrar().getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificaDni(int numero) {

        if (alu.alumnoExiste(numero)) {
            return true;
        } else {
            return false;
        }
    }
}
