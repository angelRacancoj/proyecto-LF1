package analizadorLexico.Archivo;

import analizadorLexicolf1.manejadoresAD.detector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author angelrg
 */
public class ManejadorArchivo {
    BufferedReader lectura;
    detector lexico = new detector();
    
    public String lecturaArchivo(String path) throws FileNotFoundException, IOException{
        File file = new File(path);
        String textoTotal = "";
        String linea= "";
        lectura = new BufferedReader(new FileReader(file));
        while((linea = lectura.readLine())!=null){
            textoTotal += (linea+"\n");
        }
        System.out.println(textoTotal);
        return textoTotal;
    }
}
