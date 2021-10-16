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
public class ControladorCarrerasConsultar implements ActionListener {

    private  Carrera_Consultar vista = new Carrera_Consultar();
    private DefaultTableModel modelo;
    private Carrera carrera = new Carrera();

    public ControladorCarrerasConsultar(Carrera_Consultar vista, String titulo) {
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
            carrera=null;
            vista=null;
        }
    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaAlumnos().getModel();
        vista.getJtConsultaAlumnos().setRowHeight(25);
        List<Carrera> lista = carrera.listaCarreras();
        Object[] fila = new Object[3]; 

        for (int i = 0; i < lista.size(); i++) {            
            fila[0] = lista.get(i).getCodigoCarrera();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getDuracion();
           
            modelo.addRow(fila);
        }
        vista.getJtConsultaAlumnos().setModel(modelo);       
    }
}
