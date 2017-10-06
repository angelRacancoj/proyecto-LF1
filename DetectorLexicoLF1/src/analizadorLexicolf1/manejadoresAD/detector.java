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
                    if (((codigoAscii>=65 && codigoAscii<=90)||(codigoAscii>=97 && codigoAscii <= 122))&&
                            ((codigoAscii != 99)||(codigoAscii != 101)||(codigoAscii != 102)||(codigoAscii != 105)||(codigoAscii != 112)|| (codigoAscii != 116))) {
                    estado = 1;
                    lexema = ""+letra;
                    //class    
                    }else if (codigoAscii == 99) {
                        estado = 2;
                        lexema = "" + letra;
                    //else
                    } else if (codigoAscii == 101) {
                        estado = 3;
                        lexema = ""+letra;
                    //for
                    }else if (codigoAscii == 102) {
                        estado = 4;
                        lexema = ""+letra;
                    //if
                    }else if (codigoAscii == 105) {
                        estado = 5;
                        lexema = ""+letra;
                    //private-public
                    }else if (codigoAscii == 112) {
                        estado = 6;
                        lexema = ""+letra;
                    //then
                    }else if (codigoAscii == 116) {
                        estado = 7;
                        lexema = ""+letra;
                    //Numero
                    }else if (codigoAscii>= 48 && codigoAscii<=57) {
                        estado = 8;
                        lexema = ""+letra;
                    //Signo negativo para el numero
                    }else if (codigoAscii == 45) {
                        estado = 9;
                        lexema = ""+letra;
                        //String
                    }else if (codigoAscii == 34) {
                        estado = 10;
                        lexema = ""+letra;
                    }
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
            }

        }
    }

}
