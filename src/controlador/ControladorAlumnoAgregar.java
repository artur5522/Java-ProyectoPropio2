package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import vista.*;
import modelo.*;

/**
 *
 * @author Elpo
 */
public class ControladorAlumnoAgregar implements ActionListener {

    private Alumno_Agregar vista;
    private Alumno alum = new Alumno();

    public ControladorAlumnoAgregar(Alumno_Agregar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnAgregar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            alum=null;            
        }

        if (e.getSource().equals(vista.getBtnAgregar())) {            
           agregar();
        }
    }

    public void agregar() {
        //Hago validaciones correspondientes
        if (validaCamposObligatorios() == false) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
        } else if (validarTextoCampos() == false) {
            JOptionPane.showMessageDialog(null, "Los Campos Nombre y Apellido tienen un maximo de 45 caracteres.");
        } else if (!Validaciones.validaDni(vista.getTxtDni().getText())) {
            JOptionPane.showMessageDialog(null, "DNI debe ser solo numeros y tener una longitud de 7 u 8 caracteres");
        } else if (alum.alumnoExiste(Integer.valueOf(vista.getTxtDni().getText())) == true) {
            JOptionPane.showMessageDialog(null, "Error! El Alumno Con DNI : " + vista.getTxtDni().getText() + " ya ha sido creado.");
        } else {
           //Si los datos cargados estan bien. Se carga el alumno
            alum.setDni(Integer.valueOf(vista.getTxtDni().getText()));
            alum.setNombre(vista.getTxtNombre().getText().toUpperCase());
            alum.setApellido(vista.getTxtApellido().getText().toUpperCase());
            alum.setFechaNacimiento(Date.valueOf(vista.getDateChooser().getText())); 
            // alum.setCodigoInscripcionAcarrera(500); 
           
                     
            if(alum.creaAlumno(alum)){
                JOptionPane.showMessageDialog(null, "Guardado Con Exito!"); 
                vista.getTxtNombre().setText("");
                vista.getTxtApellido().setText("");
                vista.getTxtDni().setText("");              
            } else{
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta");
            }            
        }

    }

    public boolean validaCamposObligatorios() {
        if (vista.getTxtNombre().getText().isEmpty()
                || vista.getTxtApellido().getText().isEmpty()
                || vista.getTxtDni().getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validarTextoCampos() {
        if (vista.getTxtNombre().getText().length() > 45
                || vista.getTxtApellido().getText().length() > 45) {
            return false;
        } else {
            return true;
        }
    }

}
