package com.example.tfg2.partesDelCuerpo.clases;

public class PartesDelCuerpo {
    int id;
    String nombre;

    public PartesDelCuerpo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public PartesDelCuerpo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
