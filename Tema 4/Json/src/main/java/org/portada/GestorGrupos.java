package org.portada;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.portada.modelo.Grupo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestorGrupos {
    private String archivo;
    private Gson gson;
    private List<Grupo> grupos;

    public GestorGrupos(String archivo) {
        this.archivo = archivo;
        this.gson = new Gson();
        this.grupos = new ArrayList<>();
        cargarDatos();
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    private void cargarDatos() {
        try (Reader reader = new FileReader(archivo)) {
            grupos = gson.fromJson(reader, new TypeToken<List<Grupo>>() {}.getType());
            if (grupos == null) {
                grupos = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatos() {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(grupos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persist(Grupo grupo) {
        if (grupo != null) {
            grupos.removeIf(g -> Objects.equals(g.getId(), grupo.getId()));
            grupos.add(grupo);
            guardarDatos();
        }
    }

    public Grupo find(int id) {
        for (Grupo grupo : grupos) {
            if (grupo.getId() == id) {
                return grupo;
            }
        }
        return null;
    }

    public void remove(Grupo grupo) {
        if (grupo != null) {
            grupos.removeIf(g -> Objects.equals(g.getId(), grupo.getId()));
            guardarDatos();
        }
    }

}
