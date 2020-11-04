package test;

import soporte.TSB_OAHashtable;

import java.util.Iterator;

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

    }
}
