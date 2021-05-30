package com.example.tfg2.ejercicios.clases;

import android.graphics.Bitmap;

import com.example.tfg2.musculos.clases.Musculo;

import java.io.Serializable;
import java.sql.Blob;

public class Ejercicio implements Serializable {
    private int idEjercicio;
    private Musculo musculo;
    private String nombreEjercicio;
    private String descripcionEjercicio;
    private Bitmap imageMusculo;

    public Ejercicio(int idEjercicio, Musculo musculo, String nombreEjercicio, String descripcionEjercicio,Bitmap imageMusculo) {
        this.idEjercicio = idEjercicio;
        this.musculo = musculo;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.imageMusculo = imageMusculo;
    }

    public Ejercicio(Musculo musculo, String nombreEjercicio, String descripcionEjercicio) {
        this.musculo = musculo;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
    }

    public Ejercicio() {
    }

    public Ejercicio(int idJercicio, String nombre, String descripcion) {
        this.idEjercicio = idJercicio;
        this.nombreEjercicio = nombre;
        this.descripcionEjercicio = descripcion;
    }



    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Musculo getMusculo() {
        return musculo;
    }

    public void setMusculo(Musculo musculo) {
        this.musculo = musculo;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getDescripcionEjercicio() {
        return descripcionEjercicio;
    }

    public void setDescripcionEjercicio(String descripcionEjercicio) {
        this.descripcionEjercicio = descripcionEjercicio;
    }

    public Bitmap getImageMusculo() {
        return imageMusculo;
    }

    public void setImageMusculo(Bitmap imageMusculo) {
        this.imageMusculo = imageMusculo;
    }
}
