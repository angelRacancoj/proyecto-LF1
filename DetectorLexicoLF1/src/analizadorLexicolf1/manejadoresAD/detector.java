package analizadorLexicolf1.manejadoresAD;

import analizadorLexico.Errores.ErrorLexema;
import java.util.ArrayList;
import java.util.LinkedList;

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
    private int newLine;
    private int newColum;
    private int numLetras;
    private String lexema;
    ArrayList<ErrorLexema> errores = new ArrayList<>();

    public void detectorLexemas(String textoEntrada) {
        numLetras = 0;
        newColum = 0;
        newLine = 1;
        estado = 0;
        indice = 0;
        lexema = "";
        errores.clear();

        String textoLimpio = "";

        for (int i = 0; i < textoEntrada.length(); i++) {
            char letra = textoEntrada.charAt(i);
            switch (letra) {
//                case '\r':
                case '\t':
//                case '\n':
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
                    contadoresColumnas(letra);
                    //variables
                    if ((validacionIntervaloLetra(codigoAscii)) && ((!validacionSimple(codigoAscii, i)) && (!validacionSimple(codigoAscii, p))
                            && (!validacionSimple(codigoAscii, c)) && (!validacionSimple(codigoAscii, f)) && (!validacionSimple(codigoAscii, t)) && (!validacionSimple(codigoAscii, e)))) {
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
                        //Numero 12 12.1 12.
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
                        //returno 
                    } else if (siguientePalabra(codigoAscii, letra)) {
                        estado = 0;
                        lexema = "";
                    } else {
                        numeroLetrasInicial(newColum);
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
                case 1:
                    //variables
                    contadoresColumnas(letra);
                    if ((validacionIntervaloLetra(codigoAscii)) || validacionIntervaloNumero(codigoAscii) || validacionSimple(codigoAscii, GUION) || validacionSimple(codigoAscii, GUION_BAJO)) {
                        estado = 1;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (siguientePalabra(codigoAscii, letra)) {
                        estado = 0;
                        imprimir("Variable", lexema);
                        lexema = "";
                    } else {
                        numeroLetrasInicial(newColum);
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
                case 2:
                    //class
                    tomaDescision(codigoAscii, l, letra, 11);
                    break;
                case 3:
                    //else
                    tomaDescision(codigoAscii, l, letra, 12);
                    break;
                case 4:
                    //for
                    tomaDescision(codigoAscii, o, letra, 13);
                    break;
                case 5:
                    //if
                    tomaDescision(codigoAscii, f, letra, 14);
                    break;
                case 6:
                    //private
                    contadoresColumnas(letra);
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
                    } else if (siguientePalabra(codigoAscii, letra)) {
                        estado = 0;
                        imprimir("Variable", lexema);
                        lexema = "";
                    } else {
                        numeroLetrasInicial(newColum);
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
                case 7:
                    //then
                    tomaDescision(codigoAscii, h, letra, 17);
                    break;
                case 8:
                    //Numero
                    contadoresColumnas(letra);
                    if (validacionSimple(codigoAscii, PUNTO)) {
                        estado = 18;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos quedamos en 'Q8'
                    } else if (validacionIntervaloNumero(codigoAscii)) {
                        estado = 8;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (siguientePalabra(codigoAscii, letra)) {
                        estado = 0;
                        imprimir("Numero", lexema);
                        lexema = "";
                    } else {
                        numeroLetrasInicial(newColum);
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
                case 9:
                    //numero negativo
                    //nos envia a 'Q8'
                    contadoresColumnas(letra);
                    if (validacionIntervaloNumero(codigoAscii)) {
                        estado = 8;
                        lexema = "" + letra;
                    } else {
                        numeroLetrasInicial(newColum);
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
                case 10:
                    //String
                    contadoresColumnas(letra);
                    if (validacionSimple(codigoAscii, COMILLA)) {
                        estado = 19;
                        lexema = concatenacionLetras(lexema, letra);
                        // Nos quedamos en 'Q10'
                    } else if ((validacionIntervaloLetra(codigoAscii) || validacionIntervaloNumero(codigoAscii)
                            || validacionSimple(codigoAscii, GUION) || validacionSimple(codigoAscii, GUION_BAJO) || validacionSimple(codigoAscii, PUNTO))) {
                        estado = 10;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (siguientePalabra(codigoAscii, letra)) {
                        System.err.println("Error: " + lexema);
                        errores.add(new ErrorLexema(lexema, newLine, newColum));
                        estado = 0;
                        lexema = "";
                    } else {
                        numeroLetrasInicial(newColum);
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
                case 11:
                    //class
                    tomaDescision(codigoAscii, a, letra, 20);
                    break;
                case 12:
                    //else
                    tomaDescision(codigoAscii, s, letra, 21);
                    break;
                case 13:
                    //for
                    tomaDescision(codigoAscii, r, letra, 22);
                    break;
                case 14:
                    //if (estado final)
                    estadoFinal(codigoAscii, letra);
                    break;
                case 15:
                    //private
                    tomaDescision(codigoAscii, i, letra, 23);
                    break;
                case 16:
                    //public
                    tomaDescision(codigoAscii, b, letra, 24);
                    break;
                case 17:
                    //then
                    tomaDescision(codigoAscii, e, letra, 25);
                    break;
                case 18:
                    //numero
                    contadoresColumnas(letra);
                    if (validacionIntervaloNumero(codigoAscii)) {
                        estado = 18;
                        lexema = concatenacionLetras(lexema, letra);
                    } else if (siguientePalabra(codigoAscii, letra)) {
                        estado = 0;
                        imprimir("Numero", lexema);
                        lexema = "";
                    } else {
                        numeroLetrasInicial(newColum);
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
                case 19:
                    //String
                    contadoresColumnas(letra);
                    if (siguientePalabra(codigoAscii, letra)) {
                        estado = 0;
                        imprimir("String", lexema);
                        lexema = "";
                    } else {
                        numeroLetrasInicial(newColum);
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
                case 20:
                    //class
                    tomaDescision(codigoAscii, s, letra, 26);
                    break;
                case 21:
                    //else
                    tomaDescision(codigoAscii, e, letra, 27);
                    break;
                case 22:
                    //for (estado final)
                    estadoFinal(codigoAscii, letra);
                    break;
                case 23:
                    //private
                    tomaDescision(codigoAscii, v, letra, 28);
                    break;
                case 24:
                    //public
                    tomaDescision(codigoAscii, l, letra, 29);
                    break;
                case 25:
                    //then
                    tomaDescision(codigoAscii, n, letra, 30);
                    break;
                case 26:
                    //class
                    tomaDescision(codigoAscii, s, letra, 31);
                    break;
                case 27:
                    //else (estado final)
                    estadoFinal(codigoAscii, letra);
                    break;
                case 28:
                    //private
                    tomaDescision(codigoAscii, a, letra, 32);
                    break;
                case 29:
                    //public
                    tomaDescision(codigoAscii, i, letra, 33);
                    break;
                case 30:
                    //then (estado final)
                    estadoFinal(codigoAscii, letra);
                    break;
                case 31:
                    //class (estado final)
                    estadoFinal(codigoAscii, letra);
                    break;
                case 32:
                    //private
                    tomaDescision(codigoAscii, t, letra, 34);
                    break;
                case 33:
                    //public
                    tomaDescision(codigoAscii, c, letra, 35);
                    break;
                case 34:
                    //private
                    tomaDescision(codigoAscii, e, letra, 36);
                    break;
                case 35:
                    //public (estado final)
                    estadoFinal(codigoAscii, letra);
                    break;
                case 36:
                    //private (Estado Final)
                    estadoFinal(codigoAscii, letra);
                    break;
                case ESTADO_ERROR:
                    contadoresColumnas(letra);
                    if (siguientePalabra(codigoAscii, letra)) {
                        System.err.println("Error: " + lexema);
                        errores.add(new ErrorLexema(lexema, newLine, numLetras));
                        estado = 0;
                        lexema = "";
                    } else {
                        estado = ESTADO_ERROR;
                        lexema = concatenacionLetras(lexema, letra);
                    }
                    contadoresLineas(letra);
                    break;
            }

        }
    }

    private boolean siguientePalabra(int codigoAscii, char letra) {
        return (validacionSimple(codigoAscii, ESPACIO) || (letra == '\r') || (letra == '\n'));
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
        contadoresColumnas(letra);
        if (validacionSimple(codigoAscii, caracterAComprobar)) {
            estado = estadoDestino;
            lexema = concatenacionLetras(lexema, letra);
            // Nos envia al estado 'Q1'
        } else if (validacionLetraPalabraReservada(codigoAscii, caracterAComprobar)) {
            estado = 1;
            lexema = concatenacionLetras(lexema, letra);
        } else if (siguientePalabra(codigoAscii, letra)) {
            imprimir("Variable", lexema);
            estado = 0;
            lexema = "";
        } else {
            numeroLetrasInicial(newColum);
            estado = ESTADO_ERROR;
            lexema = concatenacionLetras(lexema, letra);
        }
        contadoresLineas(letra);
    }

    private void estadoFinal(int codigoAscii, char letra) {
        contadoresColumnas(letra);
        if (validacionFinPalabraReservada(codigoAscii)) {
            estado = 1;
            lexema = concatenacionLetras(lexema, letra);
        } else if (siguientePalabra(codigoAscii, letra)) {
            estado = 0;
            imprimir("Palabra Reservada", lexema);
            lexema = "";
        } else {
            estado = ESTADO_ERROR;
            lexema = concatenacionLetras(lexema, letra);
        }
        contadoresLineas(letra);
    }

    private void imprimir(String tipo, String lexe) {
        System.out.println("Palabra Reservada " + tipo + ":" + lexe);
    }

    public ArrayList<ErrorLexema> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<ErrorLexema> errores) {
        this.errores = errores;
    }

    public void contadoresLineas(char letra) {
        if (letra == '\n') {
            newLine++;
        }
    }

    public void contadoresColumnas(char letra) {
        if (letra == '\n') {
            newColum = 0;
        } else {
            newColum++;
        }
    }

    public void numeroLetrasInicial(int newColum) {
        numLetras = newColum;
    }
}