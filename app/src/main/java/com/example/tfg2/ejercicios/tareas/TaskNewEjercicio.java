package com.example.tfg2.ejercicios.tareas;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.modelos.EjercicioDB;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.musculos.modelos.MusculoDB;

import java.util.concurrent.Callable;

public class TaskNewEjercicio implements Callable {
    Ejercicio ejercicio;
    public TaskNewEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
    }

    @Override
    public Object call() throws Exception {
        return EjercicioDB.addEjercicio(ejercicio);
    }
}
