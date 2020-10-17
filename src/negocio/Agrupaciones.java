package negocio;

import soporte.TSBHashtable;
import soporte.TextFile;

public class Agrupaciones {
    private TextFile fileAgrupaciones;
    private TextFile fileMesas;
    private TSBHashtable table;

    public Agrupaciones(String path) {
        fileAgrupaciones = new TextFile(path + "\\descripcion_postulaciones.dsv");
        fileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        table = fileAgrupaciones.identificarAgrupaciones();
        fileMesas.sumarVotosPorAgrupacion(table);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object o : table.values()
        )
            sb.append("\n" + o);
        return sb.toString();
    }
}