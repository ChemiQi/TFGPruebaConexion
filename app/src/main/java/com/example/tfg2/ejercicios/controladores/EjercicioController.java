package com.example.tfg2.ejercicios.controladores;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.tareas.TaskNewEjercicio;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EjercicioController {
    public static boolean newEjercicio(Ejercicio ejercicio) {
        FutureTask t = new FutureTask(new TaskNewEjercicio(ejercicio));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insertadoOK = false;
        try {
            insertadoOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return insertadoOK;
        }
    }
}
