package analizadorLexicolf1.manejadoresAD;

import analizadorLexico.Errores.ErrorLexema;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author angelrg
 */
public class detector {

    public final int NUMERO_ID = 0;
    public final int STRING_ID = 1;
    public final int VARIABLES_ID = 2;
    public final int PALABRA_RESERVADA_ID = 3;
    public final int ERROR_ID = 3;

    private int estado;
    private int indice;
    private String lexema;
    List<ErrorLexema> errores = new LinkedList<>();

    public void detectorLexemas(String textoEntrada) {
        estado = 0;
        indice = 0;
        lexema = "";

        String textoLimpio = "";

        for (int i = 0; i < textoEntrada.length(); i++) {
            char letra = textoEntrada.charAt(i);
            switch (letra) {
                case '\r':
                case '\t':
                case '\n':
                case '\b':
                case '\f':
                    textoLimpio += " ";
                    break;
                default:
                    textoLimpio += letra;
            }
        }

        for (indice = 0; indice < textoLimpio.length(); indice++) {
            char letra = textoLimpio.charAt(indice);
            int codigoAscii = letra;
            switch (estado) {
                case 0:
                    if (((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122))
                            && ((codigoAscii != 99) && (codigoAscii != 101) && (codigoAscii != 102) && (codigoAscii != 105) && (codigoAscii != 112) && (codigoAscii != 116))) {
                        estado = 1;
                        lexema = "" + letra;
                        //class    
                    } else if (codigoAscii == 99) {
                        estado = 2;
                        lexema = "" + letra;
                        //else
                    } else if (codigoAscii == 101) {
                        estado = 3;
                        lexema = "" + letra;
                        //for
                    } else if (codigoAscii == 102) {
                        estado = 4;
                        lexema = "" + letra;
                        //if
                    } else if (codigoAscii == 105) {
                        estado = 5;
                        lexema = "" + letra;
                        //private-public
                    } else if (codigoAscii == 112) {
                        estado = 6;
                        lexema = "" + letra;
                        //then
                    } else if (codigoAscii == 116) {
                        estado = 7;
                        lexema = "" + letra;
                        //Numero
                    } else if (codigoAscii >= 48 && codigoAscii <= 57) {
                        estado = 8;
                        lexema = "" + letra;
                        //Signo negativo para el numero
                    } else if (codigoAscii == 45) {
                        estado = 9;
                        lexema = "" + letra;
                        //String
                    } else if (codigoAscii == 34) {
                        estado = 10;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 1:
                    //variables
                    if (((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57) || (codigoAscii == 45) || (codigoAscii == 95)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 2:
                    //class
                    if (codigoAscii == 108) {
                        estado = 11;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 108)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 3:
                    //else
                    if (codigoAscii == 108) {
                        estado = 12;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 108)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 4:
                    //for
                    if (codigoAscii == 111) {
                        estado = 13;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 111)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 5:
                    //if
                    if (codigoAscii == 102) {
                        estado = 14;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 102)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 6:
                    //private
                    if (codigoAscii == 114) {
                        estado = 15;
                        lexema = "" + letra;
                        //public
                    } else if (codigoAscii == 117) {
                        estado = 16;
                        lexema = "" + letra;
                        // Nos envia la estado en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 114) && (codigoAscii != 117)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 7:
                    //then
                    if (codigoAscii == 104) {
                        estado = 17;
                        lexema = "" + letra;
                        // Nos envia la estado en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 104)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 8:
                    //Numero
                    if (codigoAscii == 46) {
                        estado = 18;
                        lexema = "" + letra;
                        // Nos quedamos en 'Q8'
                    } else if (codigoAscii >= 48 && codigoAscii <= 57) {
                        estado = 8;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 9:
                    //numero negativo
                    //nos envia a 'Q8'
                    if (codigoAscii >= 48 && codigoAscii <= 57) {
                        estado = 8;
                        lexema = "" + letra;
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 10:
                    //String
                    if (codigoAscii == 34) {
                        estado = 19;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95) || (codigoAscii == 46))) {
                        estado = 9;
                        lexema = "" + letra;
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 11:
                    //class
                    if (codigoAscii == 97) {
                        estado = 20;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 97)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 12:
                    //else
                    if (codigoAscii == 115) {
                        estado = 21;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 115)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 13:
                    //for
                    if (codigoAscii == 114) {
                        estado = 22;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 114)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 14:
                    //if (estado final)
                    if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95))) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 15:
                    //private
                    if (codigoAscii == 105) {
                        estado = 23;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 105)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 16:
                    //public
                    if (codigoAscii == 98) {
                        estado = 24;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 98)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 17:
                    //then
                    if (codigoAscii == 101) {
                        estado = 25;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 101)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 18:
                    //numero
                    if (codigoAscii >= 48 && codigoAscii <= 57) {
                        estado = 18;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 19:
                    //String
                    if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = 0;
                        lexema = "" + letra;
                        System.out.println("error" + lexema);
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 20:
                    //class
                    if (codigoAscii == 115) {
                        estado = 26;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 115)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 21:
                    //else
                    if (codigoAscii == 101) {
                        estado = 27;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 101)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 22:
                    //for (estado final)
                    if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95))) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 23:
                    //private
                    if (codigoAscii == 118) {
                        estado = 28;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 118)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 24:
                    //public
                    if (codigoAscii == 108) {
                        estado = 29;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 108)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 25:
                    //then
                    if (codigoAscii == 110) {
                        estado = 30;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 110)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 26:
                    //class
                    if (codigoAscii == 115) {
                        estado = 31;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 115)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 27:
                    //else (estado final)
                    if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95))) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 28:
                    //private
                    if (codigoAscii == 97) {
                        estado = 32;
                        lexema = "" + letra;
                        // Nos envia a las variables en 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 97)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 29:
                    //public
                    if (codigoAscii == 105) {
                        estado = 33;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 105)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 30:
                    //then (estado final)
                    if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95))) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 31:
                    //class (estado final)
                    if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95))) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 32:
                    //X
                    if (codigoAscii == 116) {
                        estado = 34;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 116)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 33:
                    //public
                    if (codigoAscii == 99) {
                        estado = 35;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 99)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 34:
                    //private
                    if (codigoAscii == 101) {
                        estado = 36;
                        lexema = "" + letra;
                        // Nos envia al estado 'Q1'
                    } else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 101)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 35:
                    //public (estado final)
                    if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95))) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
                case 36:
                    //private
                    if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
                            || (codigoAscii == 45) || (codigoAscii == 95))) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        System.out.println("Error" + letra);
                        estado = 0;
                        lexema = "" + letra;
                        errores.add(new ErrorLexema(lexema, 0, 0));
                    }
                    break;
            }

        }
    }
}
