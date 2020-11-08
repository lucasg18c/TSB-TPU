package test;

import soporte.TSB_OAHashtable;

import java.util.Iterator;
import java.util.Map;

public class Test_TSB_OAHashtable {

    public static void main(String[] args) {
        TSB_OAHashtable<Integer, String> tabla = new TSB_OAHashtable<>(3);

        tabla.put(1, "holaa1");
        tabla.put(4, "holaa2");
        tabla.put(3, "holaa3");
        tabla.put(7, "holaa4");
        tabla.put(10, "holaa5");

        System.out.println(tabla.toString());

        System.out.println(tabla.get(4));

        tabla.remove(4);

        System.out.println(tabla.toString());
        System.out.println( "Tiene holaa3? " + tabla.contains("holaa3") );
        System.out.println( "Tiene holaa5? " + tabla.contains("holaa13") );

        for (Integer i: tabla.keySet()) System.out.println(i);

        Iterator<Integer> it = tabla.keySet().iterator();
        while (it.hasNext()){
            Integer key = it.next();

            if (key == 3) {
                it.remove();
            }

        }
        System.out.println(tabla.toString());

        for (String s: tabla.values()){
            System.out.println(s);
        }

        for (Map.Entry<Integer, String> s: tabla.entrySet()){
            System.out.println(s);
        }

        try {
            TSB_OAHashtable<Integer, String> t2 = (TSB_OAHashtable<Integer, String>) tabla.clone();
            System.out.println(t2.toString());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        Iterator itEntry = tabla.entrySet().iterator();

        while (itEntry.hasNext()){
            Map.Entry e = (Map.Entry<Integer, String>) itEntry.next();

            if (e.getValue().equals("holaa5")){
                itEntry.remove();
            }
        }

        System.out.println(tabla.toString());

        tabla.put(5, "jamón");

        System.out.println(tabla.toString());

        Iterator itValue = tabla.values().iterator();

        while (itValue.hasNext()){
            String valor = (String) itValue.next();
            if (valor.equals("jamón")) itValue.remove();
        }
        System.out.println(tabla.toString());

        if (tabla.values().contains("hola")) System.out.println("tiene hola");
        else System.out.println("No tiene hola");

        if (tabla.values().contains("holaa1")) System.out.println("tiene holaa1");
        else System.out.println("No tiene hola");

    }
}
