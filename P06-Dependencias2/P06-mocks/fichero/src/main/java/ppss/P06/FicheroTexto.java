package ppss.P06;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FicheroTexto {
    //Creadxa por mi
    public FileReader getFileReader(String nombreFichero) throws FileNotFoundException {
        return new FileReader(nombreFichero);
    }
    public int contarCaracteres(String nombreFichero) throws FicheroException {
        int contador = 0;
        FileReader fichero = null;
        try {
            fichero = getFileReader(nombreFichero); // D.E Modificada por mi
            int i=0;
            while (i != -1) {
                i = fichero.read();//D.E
                contador++;
            }
            contador--;
        }
        catch (FileNotFoundException e1) {
            throw new FicheroException(nombreFichero + " (No existe el archivo o el directorio)");
        }
        catch (IOException e2) {
            throw new FicheroException(nombreFichero + " (Error al leer el archivo)");
        }
        try {
            fichero.close(); //D.E
        }
        catch (IOException e) {
            throw new FicheroException(nombreFichero + " (Error al cerrar el archivo)");
        }
        return contador;
    }
}
