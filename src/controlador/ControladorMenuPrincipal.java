package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;

public class ControladorMenuPrincipal implements ActionListener {

    private Menu_Principal mp;    
    
    //le asigno una vista y pongo a la escucha los botones
    public ControladorMenuPrincipal(Menu_Principal mp, String titulo) {
        mp.setTitle(titulo);
        this.mp = mp;

        this.mp.getBtnAlumnos().addActionListener(this);
        this.mp.getBtnProfesores().addActionListener(this);
        this.mp.getBtnCarrera().addActionListener(this);
        this.mp.getBtnInscripcionMaterias().addActionListener(this);
        this.mp.getBtnInscripcionCarreras().addActionListener(this);
        this.mp.getBtnMateria().addActionListener(this);      
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //en base al boton aproetado es a qué vista lo redirecciono
        if (e.getSource() == mp.getBtnProfesores()) {
           //oculto la vista de mi controlador actual
            mp.setVisible(false);
            //creo la nueva vista que vendrá a escena
            opciones op = new opciones();
            //el numero en el constructor es para que el controlador opciones sepa a que vista ir posteriormente
            ControladorOpciones cm = new ControladorOpciones(op, "Modulo Profesores",2);
            op.setLocationRelativeTo(null);
            //hago visible la vista
            op.setVisible(true);
            mp=null;
        } else if (e.getSource() == mp.getBtnAlumnos()) {
            mp.setVisible(false);
            opciones oa = new opciones();
            ControladorOpciones coa = new ControladorOpciones(oa, "Modulo Alumnos",1);
            oa.setLocationRelativeTo(null);
            oa.setVisible(true);
            mp=null;
        } else if (e.getSource() == mp.getBtnCarrera()) {
            mp.setVisible(false);
            opciones oc = new opciones();
            ControladorOpciones coc = new ControladorOpciones(oc, "Modulo Carreras",4);
            oc.setLocationRelativeTo(null);
            oc.setVisible(true);
            mp=null;
        } else if (e.getSource() == mp.getBtnMateria()) {
            mp.setVisible(false);
            opciones ma = new opciones();
            ControladorOpciones com = new ControladorOpciones(ma, "Modulo Materias",3);
            ma.setLocationRelativeTo(null);
            ma.setVisible(true);
            mp=null;
        } else if (e.getSource() == mp.getBtnInscripcionCarreras()) {
            mp.setVisible(false);
            opciones oi = new opciones();
            ControladorOpciones coi = new ControladorOpciones(oi, "Modulo de Inscripcion a carreras",5);
            oi.setLocationRelativeTo(null);
            oi.setVisible(true);
            mp=null;
        } else if (e.getSource() == mp.getBtnInscripcionMaterias()) {
            mp.setVisible(false);
            opciones oic = new opciones();
            ControladorOpciones cm = new ControladorOpciones(oic, "Modulo de Inscripciones a materias",6);
            oic.setLocationRelativeTo(null);
            oic.setVisible(true);
            mp=null;
        }         

    }
}
