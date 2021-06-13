package com.example.tfg2.ejercicios.clases;

import android.graphics.Bitmap;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.io.Serializable;
import java.sql.Blob;

public class Ejercicio implements Serializable {
    private int idEjercicio;
    private Musculo musculo;
    private PartesDelCuerpo partesDelCuerpo;
    private String nombreEjercicio;
    private String descripcionEjercicio;
    private Bitmap imageMusculo;

    public Ejercicio(int idEjercicio, Musculo musculo,PartesDelCuerpo partesDelCuerpo ,String nombreEjercicio, String descripcionEjercicio,Bitmap imageMusculo) {
        this.idEjercicio = idEjercicio;
        this.musculo = musculo;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.imageMusculo = imageMusculo;
        this.partesDelCuerpo = partesDelCuerpo;
    }
    public Ejercicio(Musculo musculo, PartesDelCuerpo partesDelCuerpo,String nombreEjercicio, String descripcionEjercicio,Bitmap imageMusculo) {
        this.musculo = musculo;
        this.nombreEjercicio = nombreEjercicio;
        this.descripcionEjercicio = descripcionEjercicio;
        this.imageMusculo = imageMusculo;
        this.partesDelCuerpo = partesDelCuerpo;
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

    public Ejercicio(int idJercicio, Musculo musculoSeleccionado, String nombre, String descripcion, Bitmap bmImage) {
        this.idEjercicio = idJercicio;
        this.musculo = musculoSeleccionado;
        this.nombreEjercicio = nombre;
        this.descripcionEjercicio = descripcion;
        this.imageMusculo = bmImage;
    }

    public Ejercicio(EjercicioLocal ejercicioLocal) {
        this.idEjercicio = ejercicioLocal.getIdEjercicio();
         musculo = new Musculo(ejercicioLocal.getNombreMusculo());
        this.nombreEjercicio= ejercicioLocal.getNombre();
        this.descripcionEjercicio = ejercicioLocal.getDescripcion();
        this.imageMusculo = ImagenesBlobBitmap.bytes_to_bitmap(ejercicioLocal.getImagenEjercicio());
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
