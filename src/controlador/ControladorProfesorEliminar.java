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
public class ControladorProfesorEliminar implements ActionListener {

    private Profesor_Eliminar vista;
    private DefaultTableModel modelo;
    private Profesor profe = new Profesor();
    private Materia mate = new Materia();
    private List<Materia> listaMaterias = new ArrayList();

    public ControladorProfesorEliminar(Profesor_Eliminar vista, String titulo) {
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
            profe = null;
        }
        if (e.getSource().equals(vista.getBtnEliminar())) {
            eliminar();
        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaProfesor().getModel();
        vista.getJtConsultaProfesor().setRowHeight(25);
        List<Profesor> lista = profe.listaProfesores();
        Object[] fila = new Object[5];

        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDni();
            fila[1] = lista.get(i).getNombre();
            fila[2] = lista.get(i).getApellido();
            fila[3] = lista.get(i).getFechaNacimiento();
            modelo.addRow(fila);
        }
        vista.getJtConsultaProfesor().setModel(modelo);
    }

    public void eliminar() {

        int dni_profe = Integer.valueOf(vista.getTxtBorrar().getText());

        if (!validaCampoObligatorio()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el DNI del profesor a eliminar");
        } else if (!verificaDni(dni_profe)) {
            JOptionPane.showMessageDialog(null, "No existe profesor con ese DNI");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea Elimnar? Esto hará que materias queden sin profesor asignado.", "Seleccione Una Opc.", JOptionPane.YES_NO_OPTION) == 0) {

                profe.borraProfesor(dni_profe);
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

    public boolean verificaDni(int numero) {

        if (profe.profesorExist(numero)) {
            return true;
        } else {
            return false;
        }

    }

}
