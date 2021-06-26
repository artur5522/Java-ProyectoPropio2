package controlador;

import vista.*;

public class main {

    public static void main(String[] args) {

        //creo la vista inicial y su controlador
        Inicio inicio = new Inicio();

        //le asigno la vista a mi controlador
        ControladorInicio ci = new ControladorInicio(inicio, "MENU DE INICIO");

        //le digo que haga visible la vista
        inicio.setVisible(true);
        //le digo que no se pueda modificar su tamaño
        inicio.setResizable(false);
        //lo centro en la pantalla 
        inicio.setLocationRelativeTo(null);
       
    }
}
