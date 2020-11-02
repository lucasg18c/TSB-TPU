package negocio;

import soporte.TSBHashtable;
import soporte.TextFile;

import java.util.Collection;

public class Agrupaciones {
    private TSBHashtable conteo;
    private static TSBHashtable inicial;

    public Agrupaciones() {
        conteo = new TSBHashtable();

        for (Object o: inicial.values()){
            Agrupacion a = (Agrupacion) o;
            conteo.put(a.getCodigo(), new Agrupacion(a.getCodigo(), a.getNombre()));
        }
    }

    public static void leerAgrupaciones(String path){
        TextFile fileAgrupaciones = new TextFile(path + "\\descripcion_postulaciones.dsv");
        inicial = fileAgrupaciones.identificarAgrupaciones();
    }

    public Agrupacion getAgrupacion(String codigoAgrupacion){
        return (Agrupacion) conteo.get(codigoAgrupacion);
    }


    public Collection getResultados() {
        return conteo.values();
    }
}