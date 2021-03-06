package negocio;

import soporte.TSB_OAHashtable;
import soporte.TextFile;

import java.util.Collection;

public class Resultados {
    private TSB_OAHashtable tabla;

    public Resultados(String path) {
        this.tabla = new TSB_OAHashtable();
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

    public Collection getResultadosRegion(String codRegion){
        Agrupaciones a = (Agrupaciones ) tabla.get(codRegion);
        return a.getResultados();
    }
}
