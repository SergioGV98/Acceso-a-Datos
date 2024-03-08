package org.portada;

import org.portada.modelo.Grupo;

public class Main {
    public static void main(String[] args) {
        GestorGrupos gestor = new GestorGrupos("./grupos.json");
        Grupo dam = new Grupo(1L, "1DAM");
        Grupo daw = new Grupo(2L, "1DAW");
        gestor.persist(dam);
        gestor.persist(daw);

        Grupo busqueda = gestor.find(1);
        System.out.println(busqueda.toString());
        System.out.println(gestor.getGrupos().toString());
        gestor.remove(busqueda);
        System.out.println(gestor.getGrupos().toString());

    }
}
