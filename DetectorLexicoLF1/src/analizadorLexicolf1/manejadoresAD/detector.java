package analizadorLexicolf1.manejadoresAD;

import analizadorLexico.Errores.ErrorLexema;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author angelrg
 */
public class detector {

    private final int a = 97;
    private final int A = 65;
    private final int b = 98;
    private final int c = 99;
    private final int e = 101;
    private final int f = 102;
    private final int h = 104;
    private final int i = 105;
    private final int l = 108;
    private final int n = 110;
    private final int o = 111;
    private final int p = 112;
    private final int r = 114;
    private final int s = 115;
    private final int t = 116;
    private final int u = 117;
    private final int v = 118;
    private final int z = 122;
    private final int Z = 90;
    private final int CERO = 48;
    private final int NUEVE = 57;
    private final int GUION = 45;
    private final int GUION_BAJO = 95;
    private final int PUNTO = 46;
    private final int COMILLA = 34;
    private final int ESPACIO = 32;

    private final int ESTADO_ERROR = 404;

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
                    if ((validacionIntervaloLetra(codigoAscii)) && (validacionSimple(codigoAscii, i) && validacionSimple(codigoAscii, p)
                            && validacionSimple(codigoAscii, c) && validacionSimple(codigoAscii, f) && validacionSimple(codigoAscii, t) && validacionSimple(codigoAscii, e))) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                        //class    
                    } else if (validacionSimple(codigoAscii, c)) {
                        estado = 2;
                        lexema = concatenacionLetras(lexema, letra);
                        //else
                    } else if (validacionSimple(codigoAscii, e)) {
                        estado = 3;
                        lexema = concatenacionLetras(lexema, letra);
                        //for
                    } else if (validacionSimple(codigoAscii, f)) {
                        estado = 4;
                        lexema = concatenacionLetras(lexema, letra);
                        //if
                    } else if (validacionSimple(codigoAscii, i)) {
                        estado = 5;
                        lexema = concatenacionLetras(lexema, letra);
                        //private-public
                    } else if (validacionSimple(codigoAscii, p)) {
                        estado = 6;
                        lexema = concatenacionLetras(lexema, letra);
                        //then
                    } else if (validacionSimple(codigoAscii, t)) {
                        estado = 7;
                        lexema = concatenacionLetras(lexema, letra);
                        //Numero
                    } else if (validacionIntervaloNumero(codigoAscii)) {
                        estado = 8;
                        lexema = concatenacionLetras(lexema, letra);
                        //Signo negativo para el numero
                    } else if (validacionSimple(codigoAscii, GUION)) {
                        estado = 9;
                        lexema = concatenacionLetras(lexema, letra);
                        //String
                    } else if (validacionSimple(codigoAscii, COMILLA)) {
                        estado = 10;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 1:
                    //variables
                    if ((validacionIntervaloLetra(codigoAscii)) || validacionIntervaloNumero(codigoAscii) || validacionSimple(codigoAscii, GUION) || validacionSimple(codigoAscii, GUION_BAJO)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 2:
                    //class
                    if (validacionSimple(codigoAscii, l)) {
                        estado = 11;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, l)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 3:
                    //else
                    if (validacionSimple(codigoAscii, l)) {
                        estado = 12;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, l)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 4:
                    //for
                    if (validacionSimple(codigoAscii, o)) {
                        estado = 13;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, o)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 5:
                    //if
                    if (validacionSimple(codigoAscii, f)) {
                        estado = 14;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, f)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 6:
                    //private
                    if (validacionSimple(codigoAscii, r)) {
                        estado = 15;
                        lexema = concatenacionLetras(lexema, letra);
                        //public
                    } else if (validacionSimple(codigoAscii, u)) {
                        estado = 16;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia la estado en 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, r) && validacionLetraPalabraReservada(codigoAscii, u)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
//                    }  
//                    else if ((((codigoAscii >= 65 && codigoAscii <= 90) || (codigoAscii >= 97 && codigoAscii <= 122)) || (codigoAscii >= 48 && codigoAscii <= 57)
//                            || (codigoAscii == 45) || (codigoAscii == 95)) && (codigoAscii != 114) && (codigoAscii != 117)) {
//                        estado = 1;
//                        lexema = "" + letra;
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 7:
                    //then
                    if (validacionSimple(codigoAscii, h)) {
                        estado = 17;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia la estado en 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, h)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 8:
                    //Numero
                    if (validacionIntervaloLetra(PUNTO)) {
                        estado = 18;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos quedamos en 'Q8'
                    } else if (validacionIntervaloNumero(codigoAscii)) {
                        estado = 8;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 9:
                    //numero negativo
                    //nos envia a 'Q8'
                    if (validacionIntervaloNumero(codigoAscii)) {
                        estado = 8;
                        lexema = "" + letra;
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 10:
                    //String
                    if (validacionSimple(codigoAscii, COMILLA)) {
                        estado = 19;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia a las variables en 'Q1'
                    } else if ((validacionIntervaloLetra(codigoAscii) || validacionIntervaloNumero(codigoAscii)
                            || validacionSimple(codigoAscii, GUION) || validacionSimple(codigoAscii, GUION_BAJO) || validacionSimple(codigoAscii, PUNTO))) {
                        estado = 10;
                        lexema = concatenacionLetras(lexema, letra);
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 11:
                    //class
                    if (validacionSimple(codigoAscii, a)) {
                        estado = 20;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, a)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 12:
                    //else
                    if (validacionSimple(codigoAscii, s)) {
                        estado = 21;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, s)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 13:
                    //for
                    if (validacionSimple(codigoAscii, r)) {
                        estado = 22;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, r)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 14:
                    //if (estado final)
                    if (validacionFinPalabraReservada(codigoAscii)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 15:
                    //private
                    if (validacionSimple(codigoAscii,i)) {
                        estado = 23;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,i)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 16:
                    //public
                    if (validacionSimple(codigoAscii, b)) {
                        estado = 24;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,b)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 17:
                    //then
                    if (validacionSimple(codigoAscii,e)) {
                        estado = 25;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,e)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (letra == ' ') {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 18:
                    //numero
                    if (validacionIntervaloNumero(codigoAscii)) {
                        estado = 18;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 19:
                    //String
                    if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 20:
                    //class
                    if (validacionSimple(codigoAscii,s)) {
                        estado = 26;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,s)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 21:
                    //else
                    if (validacionSimple(codigoAscii,e)) {
                        estado = 27;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,e)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 22:
                    //for (estado final)
                    if (validacionFinPalabraReservada(codigoAscii)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 23:
                    //private
                    if (validacionSimple(codigoAscii,v)) {
                        estado = 28;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia a las variables en 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,v)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 24:
                    //public
                    if (validacionSimple(codigoAscii, l)) {
                        estado = 29;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,l)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 25:
                    //then
                    if (validacionSimple(codigoAscii,n)) {
                        estado = 30;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia a las variables en 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,n)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 26:
                    //class
                    if (validacionSimple(codigoAscii,s)) {
                        estado = 31;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,s)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 27:
                    //else (estado final)
                    if (validacionFinPalabraReservada(codigoAscii)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 28:
                    //private
                    if (validacionSimple(codigoAscii,a)) {
                        estado = 32;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia a las variables en 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii,a)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 29:
                    //public
                    if (validacionSimple(codigoAscii,i)) {
                        estado = 33;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, i)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 30:
                    //then (estado final)
                    if (validacionFinPalabraReservada(codigoAscii)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 31:
                    //class (estado final)
                    if (validacionFinPalabraReservada(codigoAscii)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 32:
                    //private
                    if (validacionSimple(codigoAscii, t)) {
                        estado = 34;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, t)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 33:
                    //public
                    if (validacionSimple(codigoAscii, c)) {
                        estado = 35;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, c)) {
                        estado = 1;
                        lexema = "" + letra;
                    } else if (validacionSimple(codigoAscii,ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 34:
                    //private
                    if (validacionSimple(codigoAscii,e)) {
                        estado = 36;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos envia al estado 'Q1'
                    } else if (validacionLetraPalabraReservada(codigoAscii, e)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 35:
                    //public (estado final)
                    if (validacionFinPalabraReservada(codigoAscii)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case 36:
                    //private
                    if (validacionFinPalabraReservada(codigoAscii)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (validacionSimple(codigoAscii, ESPACIO)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
                case ESTADO_ERROR:
                    if (validacionSimple(codigoAscii, ESPACIO)) {
                        System.out.println("Error: " + lexema);
                        errores.add(new ErrorLexema(lexema, 0, 0));
                        estado = 0;
                        lexema = "";
                    }else{
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    break;
            }

        }
    }

    private boolean validacionLetraPalabraReservada(int codigoAscii, int letraAcsii) {
        return ((validacionIntervaloLetra(codigoAscii)) || (validacionIntervaloNumero(codigoAscii))
                || (validacionSimple(codigoAscii, GUION)) || (validacionSimple(codigoAscii, GUION_BAJO)) && (codigoAscii != letraAcsii));
    }

    private boolean validacionFinPalabraReservada(int codigoAscii) {
        return ((validacionIntervaloLetra(codigoAscii)) || (validacionIntervaloNumero(codigoAscii))
                || (validacionSimple(codigoAscii, GUION)) || (validacionSimple(codigoAscii, GUION_BAJO)));
    }

    private boolean validacionSimple(int codigoAscii, int simbolo) {
        return (codigoAscii == simbolo);
    }

    private boolean validacionIntervaloLetra(int codigoAscii) {
        return ((codigoAscii >= A && codigoAscii <= Z) || (codigoAscii >= a && codigoAscii <= z));
    }

    private boolean validacionIntervaloNumero(int codigoAscii) {
        return (codigoAscii >= CERO && codigoAscii <= NUEVE);
    }

    private String concatenacionLetras(String lexe, char letra) {
        lexe += letra;
        return lexe;
    }

    private void tomaDescision(int codigoAscii, int caracterAComprobar, char letra, int estadoDestino) {
        if (validacionSimple(codigoAscii, caracterAComprobar)) {
            estado = estadoDestino;
            lexema = concatenacionLetras(lexema, letra);
            // Nos envia al estado 'Q1'
        } else if (validacionLetraPalabraReservada(codigoAscii, caracterAComprobar)) {
            estado = 1;
            lexema = concatenacionLetras(lexema, letra);
        } else if (validacionSimple(codigoAscii, ESPACIO)) {
            estado = 0;
            lexema = "";
        } else {
            estado = ESTADO_ERROR;
            lexema = concatenacionLetras(lexema, letra);
        }
    }
}
