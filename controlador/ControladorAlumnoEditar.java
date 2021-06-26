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
public class ControladorAlumnoEditar implements ActionListener {

    private Alumno_Editar vista;
    private DefaultTableModel modelo;
    private Alumno alu = new Alumno();  

    public ControladorAlumnoEditar(Alumno_Editar vistaAlumno, String titulo) {
        this.vista = vistaAlumno;
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
            alu=null;
        }
        if (e.getSource().equals(vista.getBtnEditar())) {
            actualiza();
        }

    }

    public void actualiza() {
        if (!validaCampoObligatorio()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
        } else if (!verificaDni(Integer.valueOf(vista.getTxtDni().getText()))) {
            JOptionPane.showMessageDialog(null, "No existe alumno con ese DNI.");
            JOptionPane.showMessageDialog(null, "Si no recuerda el numero, puede ir a la seccion consultar.");
        } else {

            int dni_alumno = Integer.valueOf(vista.getTxtDni().getText());           
            
            alu.setDni(dni_alumno);
            alu.setApellido(vista.getTxtApellido().getText());
            alu.setNombre(vista.getTxtNombre().getText());            
            alu.setFechaNacimiento(Date.valueOf(vista.getDateChooser().getText())); 
           
            if (alu.actualizaAlumno(alu)) {
                JOptionPane.showMessageDialog(null, "Editado Con Exito!"); 
                
            }else {
                JOptionPane.showMessageDialog(null, "ERROR, Revisar Consola...");

            }           

        }

    }

    public boolean validaCampoObligatorio() {
        if (vista.getTxtNombre().getText().isEmpty()
                || vista.getTxtApellido().getText().isEmpty()
                || vista.getTxtDni().getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificaDni(int numero) {

        if (alu.alumnoExiste(numero)) {
            return true;
        } else {
            return false;
        }

    }

}
