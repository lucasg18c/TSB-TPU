package negocio;

import soporte.TSBHashtable;

import java.util.Collection;

public class Region {
    private String codigo;
    private String nombre;
    private TSBHashtable subregiones;

    public Region(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        subregiones = new TSBHashtable();
    }

    public void agregarSubregion(Region region) {
        subregiones.put(region.codigo, region);
    }

    public Collection getSubregiones() {
        return subregiones.values();
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

    public Region getSubregion(String codigo) {
        return (Region) subregiones.get(codigo);
    }
}
