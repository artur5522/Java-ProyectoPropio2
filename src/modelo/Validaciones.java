package modelo;

import java.util.InputMismatchException;

public class Validaciones {

    public static boolean validaDni(String numero) {
        //valido que se ingrese un numero, y que ese numero sea de 8 caracteres
        try {
            long dni = Integer.valueOf(numero);
            String documento = String.valueOf(dni);
            if (numero.length() == 8 || numero.length() == 7) {
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException e) {
            return false;
        }

    }

    public static boolean validaNota(String nota) {
    //Valido que la nota sea un numero y que esté entre 1 y 10

        try {
            double calificacion = Double.valueOf(nota);           
            if (calificacion < 1 || calificacion > 10) {               
                return false;
            } else {
                return true;
            }
        } catch (InputMismatchException e) {
            return false;
        }
    } 
    
     public static boolean validaAniosCarrera(String anios) {
    //Valido que los años de la carrera no sean menor a 2 ni mayor a 5

        try {
            int anio = Integer.valueOf(anios);           
            if (anio < 2 || anio > 5) {               
                return false;
            } else {
                return true;
            }
        } catch (InputMismatchException e) {
            return false;
        }
    } 
     
      public static boolean validaCodigoCarrera(String codigo) {
    //Valido que el codigo de la carrera no sea de mas de 2 digitos

        try {
            int numero = Integer.valueOf(codigo);           
            if (numero < 1 || numero > 99) {               
                return false;
            } else {
                return true;
            }
        } catch (InputMismatchException e) {
            return false;
        }
    } 
      
      public static boolean validaCodigoMateria(String codigo) {
    //Valido que el codigo de la materia no sea de mas de 2 digitos

        try {
            int numero = Integer.valueOf(codigo);           
            if (numero < 1 || numero > 999) {               
                return false;
            } else {
                return true;
            }
        } catch (InputMismatchException e) {
            return false;
        }
    } 
      
       public static boolean validaCodigoInscripcionCarrera(String codigo) {
    //Valido que el codigo de inscripcion no sea de mas de 4 digitos

        try {
            int numero = Integer.valueOf(codigo);           
            if (numero < 1 || numero > 9999) {               
                return false;
            } else {
                return true;
            }
        } catch (InputMismatchException e) {
            return false;
        }
    } 
    

}
