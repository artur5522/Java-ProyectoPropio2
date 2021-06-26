package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;;

/**
 *
 * @author Elpo
 */
public class ControladorMateriasConsultar implements ActionListener {

    private Materia_Consultar vista;
    private DefaultTableModel modelo;
    private Materia materia = new Materia();

    public ControladorMateriasConsultar(Materia_Consultar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.btnVolver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.btnVolver)) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            materia=null;

        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaAlumnos().getModel();
        vista.getJtConsultaAlumnos().setRowHeight(25);
        List<Materia> lista = materia.listaMaterias();
        Object[] fila = new Object[3]; 

        for (int i = 0; i < lista.size(); i++) {            
            fila[0] = lista.get(i).getCodMateria();
            fila[1] = lista.get(i).getNombreMateria();
            fila[2] = lista.get(i).getDniProfesor();           
            modelo.addRow(fila);
        }
        vista.getJtConsultaAlumnos().setModel(modelo);

       
    }

}
