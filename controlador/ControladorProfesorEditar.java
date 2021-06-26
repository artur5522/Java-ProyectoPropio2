package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 *
 * @author Elpo
 */
public class ControladorProfesorEditar implements ActionListener {

    private Profesor_Editar vista;
    private DefaultTableModel modelo;
    private Profesor profe = new Profesor();
   
    public ControladorProfesorEditar(Profesor_Editar vistaprofe, String titulo) {
        this.vista = vistaprofe;
        this.vista.setTitle(titulo);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);           
            profe=null;
        }
        if (e.getSource().equals(vista.getBtnEditar())) {
            actualiza();
        }

    }
   

    public void actualiza() {
        if (!validaCampoObligatorio()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obigatorios");
        } else if (!verificaDni(Integer.valueOf(vista.getTxtDni().getText()))) {
            JOptionPane.showMessageDialog(null, "No existe profesor con ese DNI.");
            JOptionPane.showMessageDialog(null, "Si no recuerda el numero, puede ir a la seccion consultar.");
        } else {          
            profe.setDni(Integer.valueOf(vista.getTxtDni().getText()));
            profe.setApellido(vista.getTxtApellido().getText());
            profe.setNombre(vista.getTxtNombre().getText());            
            profe.setFechaNacimiento(Date.valueOf(vista.getDateChooser().getText()));                   
             
            if (profe.actualizaProfesores(profe)) {
                JOptionPane.showMessageDialog(null, "Editado Con Exito!"); 
                vista.getTxtNombre().setText("");
                vista.getTxtApellido().setText("");
                vista.getTxtDni().setText("");    
                
            }else {
                JOptionPane.showMessageDialog(null, "ERROR, Revisar Consola...");

            }
        }

    }

    public boolean validaCampoObligatorio() {
        if (vista.getTxtDni().getText().isEmpty() || vista.getTxtNombre().getText().isEmpty()
                || vista.getTxtApellido().getText().isEmpty()) {
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
