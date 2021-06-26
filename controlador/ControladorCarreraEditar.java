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
public class ControladorCarreraEditar implements ActionListener {

    private Carrera_Editar vista;
    private Carrera carr = new Carrera();

    public ControladorCarreraEditar(Carrera_Editar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.getBtnCancelar().addActionListener(this);
        this.vista.getBtnEditar().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnCancelar())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            carr=null;
        }

        if (e.getSource().equals(vista.getBtnEditar())) {
            editar();
        }
    }

    public void editar() {
        //Hago validaciones correspondientes
        if (validaCamposObligatorios() == false) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
        } else if (!Validaciones.validaAniosCarrera(vista.getTxtDuracion().getText())) {
            JOptionPane.showMessageDialog(null, "La duracion de las carreras no pueden ser menores a 2 y 5 anios");
        }else if (!carr.carreraExist(Integer.valueOf(vista.getTxtCodCarrera().getText()))) {
            JOptionPane.showMessageDialog(null, "Error! La carrera de codigo: " + vista.getTxtCodCarrera().getText() + ", no existe."
                    + " Si no recuerda el codigo, puede consultarlo en la seccion de consultas");
        } else {
            //Si los datos cargados estan bien. Se carga la carrera
            carr.setCodigoCarrera(Integer.valueOf(vista.getTxtCodCarrera().getText()));
            carr.setNombre(vista.getTxtNombre().getText().toUpperCase());
            carr.setDuracion(vista.getTxtDuracion().getText());           

            if (carr.actualizaCarrera(carr)) {
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
