package com.example.tfg2.partesDelCuerpo.clases;

import java.io.Serializable;

public class PartesDelCuerpo implements Serializable {
    int id;
    String nombre;

    public PartesDelCuerpo(int id) {
        this.id = id;
        this.nombre = setNombre(id);
    }
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

    private String setNombre(int id){
        switch (id){
            case 1:
                return "Brazos";
            case 2:
                return "Piernas";
            case 3:
                return "Tronco";
            case 4:
                return "Cardio";
            default:
                return "";
        }
    }
}
