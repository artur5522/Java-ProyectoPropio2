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
public class ControladorInscripcionMateriasEliminar implements ActionListener {

    private Inscripcion_Cursado_Eliminar vista;
    private DefaultTableModel modelo;
    private Cursado cursada = new Cursado();

    public ControladorInscripcionMateriasEliminar(Inscripcion_Cursado_Eliminar vista, String titulo) {
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
            cursada = null;

        }
        if (e.getSource().equals(vista.getBtnEliminar())) {
            eliminar();
        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaCarreras().getModel();
        vista.getJtConsultaCarreras().setRowHeight(25);
        List<Cursado> lista = cursada.listaCursado();
        Object[] fila = new Object[4];

        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getCodigo();
            fila[1] = lista.get(i).getAlumnoDni();
            fila[2] = (lista.get(i).getCodigoMateria());
            fila[3] = lista.get(i).getNota();
            modelo.addRow(fila);
        }
        vista.getJtConsultaCarreras().setModel(modelo);

    }

    public void eliminar() {

        if (!validaCampoObligatorio()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo unico de inscripcion a eliminar");
        } else if (!verificaCodigo(Integer.valueOf(vista.getTxtBorrar().getText()))) {
            JOptionPane.showMessageDialog(null, "No existe un codigo con ese numero");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea Elimnar?", "Seleccione Una Opc.", JOptionPane.YES_NO_OPTION) == 0) {
            cursada.borraCursado(Integer.valueOf(vista.getTxtBorrar().getText()));
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

        if (cursada.encuentraCursado(numero)) {
            return true;
        } else {
            return false;
        }

    }

}
