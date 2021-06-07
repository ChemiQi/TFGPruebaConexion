package com.example.tfg2.ejercicios.tareas;

import com.example.tfg2.ejercicios.modelos.EjercicioDB;

import java.util.concurrent.Callable;

public class TaskGetEjercicioPorId implements Callable {
    private int idEjercicio;
    public TaskGetEjercicioPorId(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    @Override
    public Object call() throws Exception {
        return EjercicioDB.obtenerEjercicioPorId(idEjercicio);
    }
}
