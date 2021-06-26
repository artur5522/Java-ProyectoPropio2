package controlador;

import DAO.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import vista.*;
import modelo.*;

/**
 *
 * @author Elpo
 */
public class ControladorMateriasInscribir implements ActionListener {

    private Inscripcion_Cursado_Agregar vista;    
    private Cursado cursada = new Cursado();
    private Materia mat = new Materia();  
    private Alumno alu = new Alumno();

    public ControladorMateriasInscribir(Inscripcion_Cursado_Agregar vista, String titulo) {
        this.vista = vista;
        this.vista.setTitle(titulo);
        this.vista.getBtnVolver().addActionListener(this);
        this.vista.getBtnAgregar().addActionListener(this);
        cargarAmbosComboBox();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.getBtnVolver())) {
            vista.setVisible(false);
            Menu_Principal mp = new Menu_Principal();
            ControladorMenuPrincipal cp = new ControladorMenuPrincipal(mp, "Menu de Inicio--Programacion 2");
            mp.setVisible(true);
            mp.setLocationRelativeTo(null);
            mat=null;          
            alu=null;
            cursada=null;
        }

        if (e.getSource().equals(vista.getBtnAgregar())) {            
           agregar();
        }
    }

    public void agregar() {
        //Hago validaciones correspondientes
        if (validaCamposObligatorios() == false) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
        } else if (Validaciones.validaNota(vista.getTxtNota().getText())== false) {
            JOptionPane.showMessageDialog(null, "La nota no puede ser menor a 1 ni mayor a 10.");
        }
        else {
            int dni_Alumno=agarraDniDeAlumno(vista.getCbxAlumnos().getSelectedItem().toString());
           
           //Si los datos cargados estan bien. Se carga la inscripcion a la materia seteandola          
            cursada.setCodigoMateria(agarraCodigoDeMateria(vista.getCbxMaterias().getSelectedItem().toString()));  
            cursada.setAlumnoDni(dni_Alumno);             
            cursada.setNota(Integer.valueOf(vista.getTxtNota().getText()));
            
            //crea un codigo de inscripcion aleatorio del 1 al 100.000
            Random aleatorio = new Random();  
            int numero_Aleatroio = (aleatorio.nextInt(10000000) + 1);
            cursada.setCodigo(numero_Aleatroio);            
          
            if(cursada.creaCursado(cursada)){
                JOptionPane.showMessageDialog(null, "Inscripto Con Exito!"); 
                JOptionPane.showMessageDialog(null, "Su codigo unico de inscripcion es: " + cursada.getCodigo()+""
                        + " por favor guardelo en algun lugar seguro"); 
                vista.getTxtNota().setText("");  
                vista.getCbxAlumnos().setSelectedIndex(0);
                vista.getCbxMaterias().setSelectedIndex(0);
            } else{
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta");
            }            
        }

    }
    
    public void cargarAmbosComboBox() {
        List<Alumno> listaAlumnos = alu.leeAlumnos();      
        
        List<Materia> listaMaterias = mat.listaMaterias();     

        vista.getCbxAlumnos().removeAllItems();
        vista.getCbxAlumnos().addItem("Seleccionar Alumno");
        vista.getCbxMaterias().removeAllItems();
        vista.getCbxMaterias().addItem("Seleccionar Materia");
              
        for (Alumno la : listaAlumnos) {
            vista.getCbxAlumnos().addItem(la.getNombre() + "-" + la.getApellido()+ "-" + la.getDni());            
        }
        for (Materia lm : listaMaterias) {
             vista.getCbxMaterias().addItem(lm.getNombreMateria()+ "-" + lm.getCodMateria());
        }
       
    }
    
    public int agarraCodigoDeMateria(String nombre){
        String partes [] = nombre.split("-");
        
        int resul = Integer.valueOf(partes[1]);
        return resul;        
    }
    
     public int agarraDniDeAlumno(String nombre){
        String partes [] = nombre.split("-");
        
        int resul = Integer.valueOf(partes[2]);
        return resul;        
    }
     
     public Alumno buscaAlAlmumnoParaInscribirlo(int dni){         
         return alu.encuentraAlumno(dni);         
     }
 
    
     public boolean validaCamposObligatorios() {
        if (vista.getTxtNota().getText().isEmpty()
                || vista.getCbxAlumnos().getSelectedItem().equals("Seleccionar Alumno")
                || vista.getCbxMaterias().getSelectedItem().equals("Seleccionar Materia")) {
            return false;
        } else {
            return true;
        }
    }


}
