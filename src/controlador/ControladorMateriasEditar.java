package controlador;

import DAO.ProfesorDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import vista.*;
import modelo.*;

/**
 * @author Elpo
 */
public class ControladorMateriasEditar implements ActionListener {

    private Materia_Editar vista;
    private Materia mat = new Materia();
    private Profesor prof = new Profesor();   

    public ControladorMateriasEditar(Materia_Editar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
        cargarComboBoxProfesores();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            mat=null;
            prof=null;
        }

        if (e.getSource().equals(vista.getBtnEditar())) {            
           agregar();
        }
    }

    public void agregar() {
        //Hago validaciones correspondientes
        if (validaCamposObligatorios() == false) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
        }else if (!mat.materiaExist(Integer.valueOf(vista.getTxtCodMateria().getText()))) {
            JOptionPane.showMessageDialog(null, "La materia de codigo : " + vista.getTxtCodMateria().getText() + " no existe"
                    + ". Si no recuerda el codigo, puede verlo en la seccion consultas");
        }
        else {
           //Si los datos cargados estan bien. Se edita la materia
            mat.setCodMateria(Integer.valueOf(vista.getTxtCodMateria().getText()));
            mat.setNombreMateria(vista.getTxtNombre().getText().toUpperCase());
            mat.setDniProfesor(agarraDni(vista.getCbxProfesores().getSelectedItem().toString()));           
                     
            if(mat.actualizaMateria(mat)){
                JOptionPane.showMessageDialog(null, "Editado Con Exito!"); 
                vista.getTxtNombre().setText("");
                vista.getTxtCodMateria().setText("");  
                vista.getCbxProfesores().setSelectedIndex(0);
            } else{
                JOptionPane.showMessageDialog(null, "Error al ejecutar la edicion");
            }            
        }

    }
    
    public void cargarComboBoxProfesores() {
        List<Profesor> listaProfesores = prof.listaProfesores();

        vista.getCbxProfesores().removeAllItems();
        vista.getCbxProfesores().addItem("Seleccionar Profesor");
              
        for (Profesor lp : listaProfesores) {
            vista.getCbxProfesores().addItem(lp.getNombre() + "-" + lp.getApellido()+ "-" + lp.getDni());            
        }        
    }
    
    public int agarraDni (String nombre){
        String partes [] = nombre.split("-");
        
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
