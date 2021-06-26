package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 *
 * @author Elpo
 */
public class ControladorInscripcionCarrerasEliminar implements ActionListener {

    private Inscripcion_Carreras_Eliminar vista;
    private DefaultTableModel modelo;
    private Inscripcion inscri = new Inscripcion();
    private Alumno alum = new Alumno();

    public ControladorInscripcionCarrerasEliminar(Inscripcion_Carreras_Eliminar vista, String titulo) {
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
            inscri = null;
            alum=null;

        }
        if (e.getSource().equals(vista.getBtnEliminar())) {
            eliminar();
        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaCarreras().getModel();
        vista.getJtConsultaCarreras().setRowHeight(25);
        List<Inscripcion> lista = inscri.listaInscripciones();
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

    public void eliminar() {
        int codigo_inscri=Integer.valueOf(vista.getTxtBorrar().getText());

        if (!validaCampoObligatorio()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo de inscripcion a eliminar");
        } else if (!verificaCodigo(codigo_inscri)) {
            JOptionPane.showMessageDialog(null, "No existe un codigo con ese numero");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea Elimnar?", "Seleccione Una Opc.", JOptionPane.YES_NO_OPTION) == 0) {
                               
                inscri.borrarInscripcion(Integer.valueOf(vista.getTxtBorrar().getText()));
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

    public boolean verificaCodigo(int numero) {

        if (inscri.encuentraInscripcion(numero)) {
            return true;
        } else {
            return false;
        }

    }

}
