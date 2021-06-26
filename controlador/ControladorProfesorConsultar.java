package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 *
 * @author Elpo
 */
public class ControladorProfesorConsultar implements ActionListener {

    private Profesor_Consultar vista = new Profesor_Consultar();
    private DefaultTableModel modelo;
    private Profesor profe = new Profesor();

    public ControladorProfesorConsultar(Profesor_Consultar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.btnVolver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.btnVolver)) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--ProgramacionII");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);

        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaAlumnos().getModel();
        vista.getJtConsultaAlumnos().setRowHeight(25);
        List<Profesor> lista = profe.listaProfesores();
        Object[] fila = new Object[4]; 

        for (int i = 0; i < lista.size(); i++) {            
            fila[0] = lista.get(i).getDni();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getApellido();
            fila[3] = lista.get(i).getFechaNacimiento();          
            modelo.addRow(fila);
        }
        vista.getJtConsultaAlumnos().setModel(modelo);

       
    }

}
