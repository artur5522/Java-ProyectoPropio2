package controlador;

import controlador.ControladorMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.*;

/**
 *
 * @author Elpo
 */
public class ControladorInicio implements ActionListener {

    private Inicio menu = new Inicio();    

    public ControladorInicio(Inicio menu, String titulo) {
        this.menu = menu;
        this.menu.setTitle(titulo);

        this.menu.getBtnEntrar().addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menu.getBtnEntrar())) {
            if (!verificaCampos()) {
                if (!verificarUsuarioYcontraseña()) {
                    menu.setVisible(false);
                    Menu_Principal mp = new Menu_Principal();
                    ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
                    mp.setVisible(true);
                    mp.setLocationRelativeTo(null);
                    menu=null;

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Se debe ingresar usando usuario y contraseña.");
            }

        }

    }

    public boolean verificarUsuarioYcontraseña() {
        if (menu.getTxtUsuario().getText().equals("programacion") && menu.getTxtContra().getText().equals("prog2")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificaCampos() {
        if (menu.getTxtUsuario().getText().isEmpty() || menu.getTxtContra().getText().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

}
