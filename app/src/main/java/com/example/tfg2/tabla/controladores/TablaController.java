package com.example.tfg2.tabla.controladores;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.tareas.TaskGetEjercicioPorParteDelCuerpo;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.tareas.TareaDevolverUltimoIdTablasUser;
import com.example.tfg2.tabla.tareas.TareaObtenerTablasPorId;
import com.example.tfg2.tabla.tareas.TareaObtenerTablasTFG;
import com.example.tfg2.tabla.tareas.TareadAddEjerciciosTablaUser;

import java.util.ArrayList;
import java.util.List;
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

    public static ArrayList<Tabla> getTablasTFG() {
        FutureTask t = new FutureTask(new TareaObtenerTablasTFG());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        ArrayList<Tabla> tablasDeVuelta = null;
        try {
            tablasDeVuelta = (ArrayList<Tabla>) t.get();
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
        } finally {
            return tablasDeVuelta;
        }
    }

    public static List<TablaEjercicioRelacion> obtenerTablaPorId(int id) {
        FutureTask t = new FutureTask(new TareaObtenerTablasPorId(id));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        List<TablaEjercicioRelacion> tablasDeVuelta = null;
        try {
            tablasDeVuelta = (List<TablaEjercicioRelacion>) t.get();
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
        } finally {
            return tablasDeVuelta;
        }

    }
}
