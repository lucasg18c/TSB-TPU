package negocio;

import soporte.TSBHashtable;
import soporte.TextFile;

public class Resultados {
    private TSBHashtable tabla;

    public Resultados(String path) {
        this.tabla = new TSBHashtable();
        TextFile fileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        fileMesas.sumarVotosPorRegion(this);
    }

    public void sumarVotos(String codRegion, String codAgrupacion, int votos) {
        if (tabla.get(codRegion) == null)
            tabla.put(codRegion, new Agrupaciones());
//        else {
//            int actual = (int) tabla.get(codRegion);
//            tabla.put(codRegion, actual + votos);
//        }
        Agrupaciones a = (Agrupaciones) tabla.get(codRegion);
        a.getAgrupacion(codAgrupacion).sumarVotos(votos);
    }
}
