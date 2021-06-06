package com.example.tfg2.database.dataBaseOffline.domain;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.tabla.clases.Tabla;

import java.io.Serializable;

@Entity(tableName = "tabla_has_ejercicios")
public class TablaEjercicioRelacion implements Serializable {
    private int idTabla;

    @Ignore
    private Tabla tabla;
    private int idEjercicio;

    @Ignore
    private Ejercicio ejercicio;

    private int repeticiones;
    private int series;
    private int dia;

    public TablaEjercicioRelacion() {
    }

    public TablaEjercicioRelacion( Tabla tabla, Ejercicio ejercicio, int repeticiones, int series, int dia) {

        this.tabla = tabla;
        this.idTabla = tabla.getId();
        this.ejercicio = ejercicio;
        this.idEjercicio = ejercicio.getIdEjercicio();
        this.repeticiones = repeticiones;
        this.series = series;
        this.dia = dia;
    }

    public int getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}
