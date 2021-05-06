package com.example.tfg2.musculos.controladores;


import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.musculos.tareas.TaskGetMusulos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MusculoController {

    public static List<Musculo> obtenerMusculos() {
        ArrayList<Musculo> musculosDeVuelta = null;
        FutureTask t = new FutureTask(new TaskGetMusulos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);

        try {

            musculosDeVuelta = (ArrayList<Musculo>) t.get();
            System.out.println("Despues del get");
            es.shutdown();
            try{
                if(!es.awaitTermination(2000, TimeUnit.MILLISECONDS)){
                    es.shutdown();
                }
            }catch(InterruptedException e){

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return musculosDeVuelta;
        }
    }

}
