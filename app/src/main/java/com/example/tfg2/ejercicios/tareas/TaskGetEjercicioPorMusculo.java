package com.example.tfg2.ejercicios.tareas;

import com.example.tfg2.ejercicios.modelos.EjercicioDB;
import com.example.tfg2.musculos.clases.Musculo;

import java.util.concurrent.Callable;

public class TaskGetEjercicioPorMusculo implements Callable {
    Musculo musculo;
    public TaskGetEjercicioPorMusculo(Musculo musculoSeleccionado) {
        this.musculo = musculoSeleccionado;
    }

    @Override
    public Object call() throws Exception {
        return EjercicioDB.obtenerEjerciciosPorMusculo(musculo);
    }
}
