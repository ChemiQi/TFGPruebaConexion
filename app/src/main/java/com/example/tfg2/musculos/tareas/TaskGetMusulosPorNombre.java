package com.example.tfg2.musculos.tareas;

import com.example.tfg2.musculos.modelos.MusculoDB;

import java.util.concurrent.Callable;

public class TaskGetMusulosPorNombre implements Callable {
    private String nombreMusculo = "";
    public TaskGetMusulosPorNombre(String nombre) {
        this.nombreMusculo = nombre;
    }

    @Override
    public Object call() throws Exception {
        return MusculoDB.obtenerMusculoPorNombre(nombreMusculo);
    }
}
