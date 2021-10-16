package controlador;

import DAO.ProfesorDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import vista.*;
import modelo.*;

/**
 *
 * @author Elpo
 */
public class ControladorMateriasAgregar implements ActionListener {

    private Materia_Agregar vista;
    private Materia mat = new Materia();
    private Profesor prof = new Profesor();    

    public ControladorMateriasAgregar(Materia_Agregar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnAgregar().addActionListener(this);
        cargarComboBoxProfesores();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            mat = null;
            prof = null;            
        }else if (e.getSource().equals(vista.getBtnAgregar())) {            
             agregar();
        }
        
    }

    public void agregar() {
        //Hago validaciones correspondientes
        if (validaCamposObligatorios() == false) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
        } else if (Validaciones.validaCodigoMateria(vista.getTxtCodMateria().getText()) == false) {
            JOptionPane.showMessageDialog(null, "El codigo de materia no puede ser menor a 1 ni mayor a 999.");
        } else if (mat.materiaExist(Integer.valueOf(vista.getTxtCodMateria().getText())) == true) {
            JOptionPane.showMessageDialog(null, "La materia de codigo : " + vista.getTxtCodMateria().getText() + " ya ha sido creada.");
        } else {
            //Si los datos cargados estan bien. Se carga la materia
            mat.setCodMateria(Integer.valueOf(vista.getTxtCodMateria().getText()));
            mat.setNombreMateria(vista.getTxtNombre().getText().toUpperCase());
            mat.setDniProfesor(agarraDni(vista.getCbxProfesores().getSelectedItem().toString()));
           

            if (mat.creaMateria(mat)) {
                JOptionPane.showMessageDialog(null, "Guardado Con Exito!");
                //dejo los campos en blanco nuevamente
                vista.getTxtNombre().setText("");
                vista.getTxtCodMateria().setText("");
                vista.getCbxProfesores().setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta");
            }
        }

    }

    public void cargarComboBoxProfesores() {
        List<Profesor> listaProfesores = prof.listaProfesores();

        vista.getCbxProfesores().removeAllItems();
        vista.getCbxProfesores().addItem("Seleccionar Profesor");

        for (Profesor lp : listaProfesores) {
            vista.getCbxProfesores().addItem(lp.getNombre() + "-" + lp.getApellido() + "-" + lp.getDni());
        }
    }

    public int agarraDni(String nombre) {
        String partes[] = nombre.split("-");

        int resul = Integer.valueOf(partes[2]);
        return resul;
    }

    public boolean validaCamposObligatorios() {
        if (vista.getTxtCodMateria().getText().isEmpty()
                || vista.getTxtNombre().getText().isEmpty()
                || vista.getCbxProfesores().getSelectedItem().equals("Seleccionar Profesor")) {
            return false;
        } else {
            return true;
        }
    }

}
