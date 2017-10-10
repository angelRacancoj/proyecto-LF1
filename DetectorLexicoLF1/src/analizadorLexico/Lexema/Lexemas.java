/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico.Lexema;

/**
 *
 * @author angelrg
 */
public class Lexemas {
    private String lexe;
    private String tipoLexe;
    private int linea;

    public Lexemas(String lexe, String tipoLexe, int linea) {
        this.lexe = lexe;
        this.tipoLexe = tipoLexe;
        this.linea = linea;
    }

    public String getLexe() {
        return lexe;
    }

    public void setLexe(String lexe) {
        this.lexe = lexe;
    }

    public String getTipoLexe() {
        return tipoLexe;
    }

    public void setTipoLexe(String tipoLexe) {
        this.tipoLexe = tipoLexe;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    
}
