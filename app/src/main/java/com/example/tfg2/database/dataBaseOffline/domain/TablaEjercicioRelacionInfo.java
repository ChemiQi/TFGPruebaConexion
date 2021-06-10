package com.example.tfg2.database.dataBaseOffline.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tabla_ejercicio_info")
public class TablaEjercicioRelacionInfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idTablaEjercicioRelacionInfo;

    private int idTabla;
    private int idEjercicio;
    private int repeticiones;
    private int serie;
    private double peso;


    public TablaEjercicioRelacionInfo() {
    }

    public TablaEjercicioRelacionInfo(int idTablaEjercicioRelacionInfo, int idTabla, int idEjercicio, int repeticiones, int serie, double peso) {
        this.idTablaEjercicioRelacionInfo = idTablaEjercicioRelacionInfo;
        this.idTabla = idTabla;
        this.idEjercicio = idEjercicio;
        this.repeticiones = repeticiones;
        this.serie = serie;
        this.peso = peso;
    }
    public TablaEjercicioRelacionInfo( int idTabla, int idEjercicio, int repeticiones, int serie, double peso) {
        this.idTabla = idTabla;
        this.idEjercicio = idEjercicio;
        this.repeticiones = repeticiones;
        this.serie = serie;
        this.peso = peso;
    }

    public TablaEjercicioRelacionInfo(TablaEjercicioRelacion tablaEjercicioRelacion, int serie) {
        this.idTabla = tablaEjercicioRelacion.getIdTabla();
        this.idEjercicio = tablaEjercicioRelacion.getIdEjercicio();
        this.repeticiones = tablaEjercicioRelacion.getRepPesoMax();
        this.serie = serie;
        this.peso = tablaEjercicioRelacion.getPesoMax();
    }



    public int getIdTablaEjercicioRelacionInfo() {
        return idTablaEjercicioRelacionInfo;
    }

    public void setIdTablaEjercicioRelacionInfo(int idTablaEjercicioRelacionInfo) {
        this.idTablaEjercicioRelacionInfo = idTablaEjercicioRelacionInfo;
    }

    public int getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
