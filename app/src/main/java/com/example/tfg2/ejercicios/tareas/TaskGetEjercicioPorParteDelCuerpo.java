package com.example.tfg2.ejercicios.tareas;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.modelos.EjercicioDB;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TaskGetEjercicioPorParteDelCuerpo implements Callable {
    PartesDelCuerpo partesDelCuerpo;

    public TaskGetEjercicioPorParteDelCuerpo(PartesDelCuerpo partesDelCuerpo) {
        this.partesDelCuerpo = partesDelCuerpo;
    }

    @Override
    public ArrayList<Ejercicio> call() throws Exception {
        return EjercicioDB.obtenerEjerciciosPorParteDelCuerpo(partesDelCuerpo);
    }
}
