package com.example.tfg2.database.dataBaseOffline.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;


@Entity(tableName = "ejercicio_user")
public class EjercicioLocal {

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

   // Blob imagenEjercicio;


    public EjercicioLocal(int idEjercicio,  String nombreMusculo, @NonNull String nombre, @NonNull String descripcion, @NonNull Blob imagenEjercicio) {
        this.idEjercicio = idEjercicio;
       // this.idUser = idUser;
        this.nombreMusculo = nombreMusculo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        //this.imagenEjercicio = imagenEjercicio;
    }
    public EjercicioLocal( String nombreMusculo, @NonNull String nombre, @NonNull String descripcion,  Blob imagenEjercicio) {
        this.nombreMusculo = nombreMusculo;
        this.nombre = nombre;
        this.descripcion = descripcion;
       // this.imagenEjercicio = imagenEjercicio;
    }


    public EjercicioLocal() {
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


   /* public Blob getImagenEjercicio() {
        return imagenEjercicio;
    }

    public void setImagenEjercicio(@NonNull Blob imagenEjercicio) {
        this.imagenEjercicio = imagenEjercicio;
    }

    */
    public void mostrar(){
        System.out.println(this.idEjercicio + " " + this.nombre + ": " + this.descripcion + " ---" +this.nombreMusculo );
    }
}
