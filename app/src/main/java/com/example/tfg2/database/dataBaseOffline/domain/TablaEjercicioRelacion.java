package com.example.tfg2.database.dataBaseOffline.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.tabla.clases.Tabla;

import java.io.Serializable;

@Entity(tableName = "tabla_has_ejercicios")
public class TablaEjercicioRelacion implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idTablaEjercico;

    private int idTabla;

    @Ignore
    private TablaLocal tabla;
    private int idEjercicio;

    @Ignore
    private Ejercicio ejercicio;

    private int repeticiones;
    private int series;
    private int dia;
    private double pesoMax;
    private int repPesoMax;

    public TablaEjercicioRelacion() {
    }
    public TablaEjercicioRelacion(int idTabla,int idEjercicio, int repeticiones, int series, int dia) {
        this.idTabla = idTabla;
        this.idEjercicio = idEjercicio;
        this.repeticiones = repeticiones;
        this.series = series;
        this.dia = dia;
        this.pesoMax = 0.0;
        this.repPesoMax = 0;
    }

    public TablaEjercicioRelacion(TablaLocal tabla, Ejercicio ejercicio, int repeticiones, int series, int dia) {

        this.tabla = tabla;
        this.idTabla = tabla.getIdTabla();
        this.ejercicio = ejercicio;
        this.idEjercicio = ejercicio.getIdEjercicio();
        this.repeticiones = repeticiones;
        this.series = series;
        this.dia = dia;
        this.pesoMax = 0.0;
        this.repPesoMax = 0;
    }

    public int getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    public TablaLocal getTabla() {
        return tabla;
    }

    public void setTabla(TablaLocal tabla) {
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

    public int getIdTablaEjercico() {
        return idTablaEjercico;
    }

    public void setIdTablaEjercico(int idTablaEjercico) {
        this.idTablaEjercico = idTablaEjercico;
    }

    public double getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public int getRepPesoMax() {
        return repPesoMax;
    }

    public void setRepPesoMax(int repPesoMax) {
        this.repPesoMax = repPesoMax;
    }

    public void pintar(){
        System.out.println("IDTABLA: " + this.idTabla + " --- IDEJERCICIO " + this.idEjercicio + " --- REP: " + this.repeticiones + " --- SER: " + this.series + " --- DIA " + this.dia);
    }
}
