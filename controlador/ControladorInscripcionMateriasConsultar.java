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
public class ControladorInscripcionMateriasConsultar implements ActionListener {
    
      
    private Inscripcion_Cursado_Consultar vista;
    private DefaultTableModel modelo;
    private Cursado cursado = new Cursado();

    public ControladorInscripcionMateriasConsultar(Inscripcion_Cursado_Consultar vista, String titulo) {
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
            cursado=null;

        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaAlumnos().getModel();
        vista.getJtConsultaAlumnos().setRowHeight(25);
        List<Cursado> lista = cursado.listaCursado();
        Object[] fila = new Object[4]; 

        for (int i = 0; i < lista.size(); i++) { 
            
            fila[0] = lista.get(i).getCodigo();
            fila[1] = lista.get(i).getAlumnoDni();
            fila[2] = (lista.get(i).getCodigoMateria());
            fila[3] = lista.get(i).getNota();
            
            modelo.addRow(fila);
        }
        vista.getJtConsultaAlumnos().setModel(modelo);

       
    }

}
