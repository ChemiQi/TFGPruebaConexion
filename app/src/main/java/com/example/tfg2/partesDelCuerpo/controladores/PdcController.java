package com.example.tfg2.partesDelCuerpo.controladores;

import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.partesDelCuerpo.tareas.TaskGetBody;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class PdcController {
    public static List<PartesDelCuerpo> obtenerPartes() {
        List<PartesDelCuerpo> ligasDeVuelta = null;
        FutureTask t = new FutureTask(new TaskGetBody());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);

        try {
            ligasDeVuelta = (List<PartesDelCuerpo>) t.get();
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
            return ligasDeVuelta;
        }
    }
}
