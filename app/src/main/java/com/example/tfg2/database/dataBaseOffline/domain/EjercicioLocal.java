package com.example.tfg2.database.dataBaseOffline.domain;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.io.Serializable;
import java.sql.Blob;


@Entity(tableName = "ejercicio_user")
public class EjercicioLocal  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    int idEjercicio;
    //@NonNull
    //int idUser;
    @NonNull
    String nombreMusculo;
    @NonNull
    String nombre;
    @NonNull
    String descripcion;

    Boolean created;

    byte [] imagenEjercicio;

    public EjercicioLocal(int idEjercicio, @NonNull String nombreMusculo, @NonNull String nombre, @NonNull String descripcion, Boolean created, byte[] imagenEjercicio) {
        this.idEjercicio = idEjercicio;
        this.nombreMusculo = nombreMusculo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.created = created;
        this.imagenEjercicio = imagenEjercicio;
    }
    public EjercicioLocal( @NonNull String nombreMusculo, @NonNull String nombre, @NonNull String descripcion, Boolean created, byte[] imagenEjercicio) {
        this.nombreMusculo = nombreMusculo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.created = created;
        this.imagenEjercicio = imagenEjercicio;
    }

    public EjercicioLocal() {
    }

    public EjercicioLocal(Ejercicio ejercicioPorId) {
        this.idEjercicio = ejercicioPorId.getIdEjercicio();
        this.nombreMusculo = ejercicioPorId.getMusculo().getNombreMusculo();
        this.nombre = ejercicioPorId.getNombreEjercicio();
        this.descripcion = ejercicioPorId.getDescripcionEjercicio();
        this.created = false;
        this.imagenEjercicio = ImagenesBlobBitmap.bitmap_to_bytes(ejercicioPorId.getImageMusculo());
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }


    @NonNull
    public String getNombreMusculo() {
        return nombreMusculo;
    }

    public void setNombreMusculo(@NonNull String nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getCreated() {
        return created;
    }

    public void setCreated(Boolean created) {
        this.created = created;
    }

     public byte [] getImagenEjercicio() {
            return imagenEjercicio;
        }

        public void setImagenEjercicio(@NonNull byte [] imagenEjercicio) {
            this.imagenEjercicio = imagenEjercicio;
        }


    public void mostrar(){
        System.out.println(this.idEjercicio + " " + this.nombre + ": " + this.descripcion + " ---" +this.nombreMusculo );
    }
}
