package com.example.tfg2.tabla.clases;

import com.example.tfg2.ejercicios.clases.EjercicioInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class Tabla implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private int dias;
    private ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio;

    public Tabla(int id, String nombre, String descripcion, int dias, ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias = dias;
        this.listaDiasEjercicio = listaDiasEjercicio;
    }

    public Tabla(int id, String nombre, String descripcion, int dias) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias = dias;
    }

    public Tabla() {
    }

    public Tabla(String nombre, int dias) {
        this.nombre = nombre;
        this.dias = dias;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public ArrayList<ArrayList<EjercicioInfo>> getListaDiasEjercicio() {
        return listaDiasEjercicio;
    }

    public void setListaDiasEjercicio(ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio) {
        this.listaDiasEjercicio = listaDiasEjercicio;
    }
}
