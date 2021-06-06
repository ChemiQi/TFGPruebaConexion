package com.example.tfg2.tabla.controladores;

import android.graphics.Bitmap;

import com.example.tfg2.ejercicios.tareas.TareaAñadirFoto;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.tareas.TareaDevolverUltimoIdTablasUser;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TablaController {
    public static Integer obtenerUltimoIdTablaUsuario(Tabla tabla) {
        FutureTask t = new FutureTask(new TareaDevolverUltimoIdTablasUser(tabla));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        int numero = 0;
        try {
            numero = (Integer) t.get();
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
            return numero;
        }
    }

    public static boolean addEjerciciosTablaUser(Tabla tablaCreada) {
        FutureTask t = new FutureTask(new TareadAddEjerciciosTablaUser(tablaCreada));
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
