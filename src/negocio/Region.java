package negocio;

import soporte.TSB_OAHashtable;

import java.util.Collection;

public class Region {
    private String codigo;
    private String nombre;
    private TSB_OAHashtable subregiones;

    public Region(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        subregiones = new TSB_OAHashtable();
    }

    public void agregarSubregion(Region region) {
        subregiones.put(region.codigo, region);
    }

    public Collection getSubregiones() {
        return subregiones.values();
    }

    public Region getOrPutSubregion(String codigo) {
        Region sub = (Region) subregiones.get(codigo);
        if (sub == null)
            subregiones.put(codigo, new Region(codigo, ""));
        return (Region) subregiones.get(codigo);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public Region getSubregion(String codigo) {
        return (Region) subregiones.get(codigo);
    }
}
