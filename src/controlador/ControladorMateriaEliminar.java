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
public class ControladorMateriaEliminar implements ActionListener {

    private Materia_Eliminar vista;
    private DefaultTableModel modelo;
    private Materia materia = new Materia();    
    private Cursado cur = new Cursado();
    private List<Cursado> listaCursados = new ArrayList();

    public ControladorMateriaEliminar(Materia_Eliminar vista, String titulo) {
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
            materia = null;
            cur=null;
            listaCursados=null;

        }
        if (e.getSource().equals(vista.getBtnEliminar())) {
            eliminar();
        }

    }

    public void muestra() {
        modelo = (DefaultTableModel) vista.getJtConsultaMaterias().getModel();
        vista.getJtConsultaMaterias().setRowHeight(25);
        List<Materia> lista = materia.listaMaterias();
        Object[] fila = new Object[3];

        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getCodMateria();
            fila[1] = lista.get(i).getNombreMateria();
            fila[2] = lista.get(i).getDniProfesor();

            modelo.addRow(fila);
        }
        vista.getJtConsultaMaterias().setModel(modelo);

    }

    public void eliminar() {
        
        int codigo_materia=Integer.valueOf(vista.getTxtBorrar().getText());

        if (!validaCampoObligatorio()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el codigo de la materia a eliminar");
        } else if (!verificaCodigoMateria(codigo_materia)) {
            JOptionPane.showMessageDialog(null, "No existe una materia con ese codigo");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea Elimnar? Esto traerá aparejado que se pierdan posibles inscripciones", "Seleccione Una Opc.", JOptionPane.YES_NO_OPTION) == 0) {
                               
                //traigo las inscripciones para eliminar las que coinciden con la materia que se está borrando
                listaCursados=cur.listaCursado();
                for (Cursado lc : listaCursados) {
                    if (lc.getCodigoMateria() == codigo_materia) {
                        cur=lc;
                        cur.borraCursado((int)cur.getCodigo());
                    }
                }                
                
                materia.borraMateria(codigo_materia);
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

    public boolean verificaCodigoMateria(int numero) {

        if (materia.materiaExist(numero)) {
            return true;
        } else {
            return false;
        }

    }

}
