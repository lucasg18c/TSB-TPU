package soporte;

import negocio.Agrupacion;
import negocio.Region;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFile {
    private File archivo;

    public TextFile(String direccionArchivo) {
        archivo = new File(direccionArchivo);
    }

    public String leerEncabezado() {
        String linea = "";
        try {
            Scanner sc = new Scanner(archivo);
            if (sc.hasNext()) linea = sc.nextLine();

        } catch (FileNotFoundException exception) {
            System.err.println("No se pudo leer el archivo...");
        }
        return linea;
    }

    public TSBHashtable identificarAgrupaciones() {
        String linea = "", campos[];
        TSBHashtable table = new TSBHashtable();
        Agrupacion agrupacion;
        try {
            Scanner sc = new Scanner(archivo);
            while (sc.hasNext()) {
                linea = sc.nextLine();
                campos = linea.split("\\|");
                if (campos[0].compareTo("000100000000000") == 0) {
                    agrupacion = new Agrupacion(campos[2], campos[3]);
                    table.put(agrupacion.getCodigo(), agrupacion);
                }
            }


        } catch (FileNotFoundException exception) {
            System.err.println("No se pudo leer el archivo...");
        }
        return table;
    }

    public void sumarVotosPorAgrupacion(TSBHashtable table) {
        String linea = "", campos[];

        Agrupacion agrupacion;
        int votos;
        try {
            Scanner sc = new Scanner(archivo);
            while (sc.hasNext()) {
                linea = sc.nextLine();
                campos = linea.split("\\|");
                //Filtramos votacion para presidente
                if (campos[4].compareTo("000100000000000") == 0) {
                    agrupacion = (Agrupacion) table.get(campos[5]);
                    votos = Integer.parseInt(campos[6]);
                    agrupacion.sumarVotos(votos);
                }
            }


        } catch (FileNotFoundException exception) {
            System.err.println("No se pudo leer el archivo...");
        }

    }

    public Region identificarRegiones() {
        String linea = "", campos[], codigo, nombre;
        Region pais = new Region("00", "Argentina");
        Region distrito, seccion;

        try {
            Scanner sc = new Scanner(archivo);
            while (sc.hasNext()) {
                linea = sc.nextLine();
                campos = linea.split("\\|");

                codigo = campos[0];
                nombre = campos[1];
                switch (codigo.length()){

                    case  2:
                        distrito = pais.getOrPutSubregion(codigo);
                        distrito.setNombre(nombre);
                        break;
                    case 5:
                        distrito = pais.getOrPutSubregion(codigo.substring(0, 2));
                        seccion = distrito.getOrPutSubregion(codigo);
                        seccion.setNombre(nombre);
                        break;
                    case 11:
                        distrito = pais.getOrPutSubregion(codigo.substring(0,2));
                        seccion = distrito.getOrPutSubregion(codigo.substring(0,5));
                        seccion.agregarSubregion(new Region(codigo,nombre));
                        break;
                }
            }

        } catch (FileNotFoundException exception) {
            System.err.println("No se pudo leer el archivo...");
        }
        return pais;    }
}

