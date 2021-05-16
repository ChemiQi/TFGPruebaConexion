package com.example.tfg2.ejercicios.clases;

import java.io.Serializable;

public class EjercicioYPosicion  implements Serializable {
    int posicion;
    EjercicioInfo ejercicioInfo;

    public EjercicioYPosicion(int posicion, EjercicioInfo ejercicioInfo) {
        this.posicion = posicion;
        this.ejercicioInfo = ejercicioInfo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public EjercicioInfo getEjercicioInfo() {
        return ejercicioInfo;
    }

    public void setEjercicioInfo(EjercicioInfo ejercicioInfo) {
        this.ejercicioInfo = ejercicioInfo;
    }
}
