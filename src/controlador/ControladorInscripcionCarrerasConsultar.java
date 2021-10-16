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
public class ControladorInscripcionCarrerasConsultar implements ActionListener {

    private Inscripcion_Carreras_Consultar vista;
    private DefaultTableModel modelo;
    private Inscripcion inscripcion = new Inscripcion();

    public ControladorInscripcionCarrerasConsultar(Inscripcion_Carreras_Consultar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.btnVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.btnVolver)) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            inscripcion=null;

        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaCarreras().getModel();
        vista.getJtConsultaCarreras().setRowHeight(25);
        List<Inscripcion> lista = inscripcion.listaInscripciones();
        Object[] fila = new Object[4]; 

        for (int i = 0; i < lista.size(); i++) {   
            
            fila[0] = lista.get(i).getCodigoInscripcion();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getFecha();
            fila[3] = lista.get(i).getCodigoCarrera();  
            modelo.addRow(fila);
        }
        vista.getJtConsultaCarreras().setModel(modelo);

       
    }

}
