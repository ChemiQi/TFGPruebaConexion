package com.example.tfg2.ejercicios.clases;

import java.io.Serializable;

public class EjercicioInfo  implements Serializable {
    Ejercicio ejercicio;
    int series;
    int repeticiones;

    public EjercicioInfo(Ejercicio ejercicio, int series, int repeticiones) {
        this.ejercicio = ejercicio;
        this.series = series;
        this.repeticiones = repeticiones;
        //IMAGEN EJERCIICO
    }

    public EjercicioInfo() {
    }

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    @Override
    public String toString() {
        return "EjercicioInfo{" +
                "ejercicio=" + ejercicio +
                ", series=" + series +
                ", repeticiones=" + repeticiones +
                '}';
    }
}
