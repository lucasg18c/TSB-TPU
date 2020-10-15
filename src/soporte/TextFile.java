package soporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFile {
    private File archivo;

    public TextFile(String direccionArchivo) {
        archivo = new File(direccionArchivo);
    }

    public String leerEncabezado(){
        String linea = "";
        try {
            Scanner sc = new Scanner(archivo);
            if (sc.hasNext()) linea = sc.nextLine();

        } catch (FileNotFoundException exception) {
            System.err.println("No se pudo leer el archivo...");
        }
        return linea;
    }
}
