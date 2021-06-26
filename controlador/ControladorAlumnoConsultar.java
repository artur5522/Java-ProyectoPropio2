package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import vista.Alumno_Consultar;
import vista.Menu_Principal;

/**
 *
 * @author Elpo
 */
public class ControladorAlumnoConsultar implements ActionListener {

    private Alumno_Consultar vistaAlumno;
    private DefaultTableModel modelo;
    private Alumno alu = new Alumno();

    public ControladorAlumnoConsultar(Alumno_Consultar vistaAlumno, String titulo) {
        this.vistaAlumno = vistaAlumno;
        this.vistaAlumno.setTitle(titulo);
        this.vistaAlumno.getBtnVolver().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vistaAlumno.getBtnVolver())) {
            vistaAlumno.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            alu=null;
            vistaAlumno=null;

        }

    }

    public void muestra() {
        // cargo todos los alumnos en la tabla del jframe
        modelo = (DefaultTableModel) vistaAlumno.getJtConsultaAlumnos().getModel();
        vistaAlumno.getJtConsultaAlumnos().setRowHeight(25);
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
        vistaAlumno.getJtConsultaAlumnos().setModel(modelo);
        
       
    }

}
