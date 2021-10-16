package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.*;
import modelo.*;

/**
 *
 * @author Elpo
 */
public class ControladorCarreraAgregar implements ActionListener {

    private Carrera_Agregar vista;
    private Carrera carr = new Carrera();

    public ControladorCarreraAgregar(Carrera_Agregar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnAgregar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--ProgramacionII");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            carr=null;
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
            JOptionPane.showMessageDialog(null, "El campo 'Codigo de carrera' tiene un maximo de 30 caracteres.");
        } else if (!Validaciones.validaAniosCarrera(vista.getTxtDuracion().getText())) {
            JOptionPane.showMessageDialog(null, "La duracion de las carreras no pueden ser menores a 2 y 5 anios");
        }else if (!Validaciones.validaCodigoCarrera(vista.getTxtCodCarrera().getText())) {
            JOptionPane.showMessageDialog(null, "El codigo de carrera no puede ser menor a 1 ni mayor a 99");
        }else if (carr.carreraExist(Integer.valueOf(vista.getTxtCodCarrera().getText())) == true) {
            JOptionPane.showMessageDialog(null, "Error! La carrera : " + vista.getTxtNombre().getText() + ", ya ha sido creada.");
        } else {
            //Si los datos cargados estan bien. Se carga la carrera
            carr.setCodigoCarrera(Integer.valueOf(vista.getTxtCodCarrera().getText()));
            carr.setNombre(vista.getTxtNombre().getText().toUpperCase());
            carr.setDuracion(vista.getTxtDuracion().getText());           

            if (carr.creaCarrera(carr)) {
                JOptionPane.showMessageDialog(null, "Guardado Con Exito!");
                vista.getTxtNombre().setText("");
                vista.getTxtCodCarrera().setText("");
                vista.getTxtDuracion().setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta");
            }
        }

    }

    public boolean validaCamposObligatorios() {
        if (vista.getTxtNombre().getText().isEmpty()
                || vista.getTxtCodCarrera().getText().isEmpty()
                || vista.getTxtDuracion().getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validarTextoCampos() {
        if (vista.getTxtNombre().getText().length() > 30) {
            return false;
        } else {
            return true;
        }
    }

}
