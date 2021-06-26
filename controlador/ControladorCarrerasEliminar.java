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
 *
 * @author Elpo
 */
public class ControladorCarrerasEliminar implements ActionListener {

    private Carrera_Eliminar vista;
    private DefaultTableModel modelo;
    private Carrera carrera = new Carrera();
    private Inscripcion inscri = new Inscripcion();
    private List<Inscripcion> listaInscripciones = new ArrayList();

    public ControladorCarrerasEliminar(Carrera_Eliminar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            carrera = null;        
            inscri = null;

        }
        if (e.getSource().equals(vista.getBtnEliminar())) {
            eliminar();
        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaCarreras().getModel();
        vista.getJtConsultaCarreras().setRowHeight(25);
        List<Carrera> lista = carrera.listaCarreras();
        Object[] fila = new Object[3];

        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getCodigoCarrera();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getDuracion();

            modelo.addRow(fila);
        }
        vista.getJtConsultaCarreras().setModel(modelo);

    }

    public void eliminar() {
        int codigo_carrera = Integer.valueOf(vista.getTxtBorrar().getText());

        if (!validaCampoObligatorio()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo de la carrera a eliminar");
        } else if (!verificaCodigoCarrera(codigo_carrera)) {
            JOptionPane.showMessageDialog(null, "No existe una carrera con ese codigo");
        } else {            
            if (JOptionPane.showConfirmDialog(null, "Desea Elimnar? Esto traerá aparejado que se pierdan inscripciones.", "Seleccione Una Opc.", JOptionPane.YES_NO_OPTION) == 0) {

                //listo y recorro todas las inscripciones para encontrar la/s indicadas
                listaInscripciones = inscri.listaInscripciones();
                for (Inscripcion listInc : listaInscripciones) {
                    if (listInc.getCodigoCarrera() == codigo_carrera) {
                        inscri = listInc;
                        //borro la/s inscripcion/es que coincide
                        inscri.borrarInscripcion(inscri.getCodigoInscripcion());
                    }
                }

                carrera.borraCarrera(codigo_carrera);
                JOptionPane.showMessageDialog(null, "Eliminado Con Exito!");
                JOptionPane.showMessageDialog(null, "Para ver reflejado el cambio, vuelva a entrar desde el menu princial");

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

    public boolean verificaCodigoCarrera(int numero) {

        if (carrera.carreraExist(numero)) {
            return true;
        } else {
            return false;
        }

    }

}
