package com.example.tfg2.ejercicios.clases;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.io.Serializable;

public class EjercicioInfo  implements Serializable {
    Ejercicio ejercicio;
    int series;
    int repeticiones;
    Boolean created ;

    public EjercicioInfo(Ejercicio ejercicio, int series, int repeticiones) {
        this.ejercicio = ejercicio;
        this.series = series;
        this.repeticiones = repeticiones;
        //IMAGEN EJERCIICO
    }

    public EjercicioInfo() {
    }

    public EjercicioInfo(EjercicioLocal ejercicioLocal, TablaEjercicioRelacion ejercicioRelacion) {

        this.ejercicio = new Ejercicio(ejercicioLocal.getIdEjercicio(),new Musculo(ejercicioLocal.getNombreMusculo()),ejercicioLocal.getNombre(),ejercicioLocal.getDescripcion(), ImagenesBlobBitmap.bytes_to_bitmap(ejercicioLocal.getImagenEjercicio()));
        this.series = ejercicioRelacion.getSeries();
        this.repeticiones = ejercicioRelacion.getRepeticiones();
        this.created = ejercicioLocal.getCreated();
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

    public Boolean getCreated() {
        return created;
    }

    public void setCreated(Boolean created) {
        this.created = created;
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
