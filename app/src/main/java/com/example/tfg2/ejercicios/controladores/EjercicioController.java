package com.example.tfg2.ejercicios.controladores;

import android.graphics.Bitmap;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.tareas.TareaAñadirFoto;
import com.example.tfg2.ejercicios.tareas.TaskGetEjercicioUser;
import com.example.tfg2.ejercicios.tareas.TaskNewEjercicio;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.ejercicios.tareas.TaskGetEjercicioPorMusculo;
import com.example.tfg2.ejercicios.tareas.TaskGetEjercicioPorParteDelCuerpo;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.user.clases.CurrentUser;
import com.example.tfg2.user.clases.User;


import java.util.ArrayList;
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

    public static ArrayList<Ejercicio> ejerciciosPorParteDelCuerpo(PartesDelCuerpo partesDelCuerpo){
        FutureTask t = new FutureTask(new TaskGetEjercicioPorParteDelCuerpo(partesDelCuerpo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        ArrayList<Ejercicio> listaDeVueltaEjerciciosPorParteDelCuerpo = null;
        try {
            listaDeVueltaEjerciciosPorParteDelCuerpo = (ArrayList<Ejercicio>) t.get();
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
            return listaDeVueltaEjerciciosPorParteDelCuerpo;
        }

    }

    public static ArrayList<Ejercicio> obtenerEjerciciosPorMusculo(Musculo musculoSeleccionado) {
        FutureTask t = new FutureTask(new TaskGetEjercicioPorMusculo(musculoSeleccionado));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        ArrayList<Ejercicio> listaDeVueltaEjerciciosPorParteDelCuerpo = null;
        try {
            listaDeVueltaEjerciciosPorParteDelCuerpo = (ArrayList<Ejercicio>) t.get();
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
            return listaDeVueltaEjerciciosPorParteDelCuerpo;
        }
    }
    public static boolean ponerFotoEjercicio( Bitmap foto, int id) {
        FutureTask t = new FutureTask(new TareaAñadirFoto(foto, id));
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

    public static ArrayList<Ejercicio> obtenerEjerciciosUsuario(int id ) {

            FutureTask t = new FutureTask(new TaskGetEjercicioUser(id));
            ExecutorService es = Executors.newSingleThreadExecutor();
            es.submit(t);
            ArrayList<Ejercicio> listaDeVueltaEjerciciosPorParteDelCuerpo = new ArrayList<>();
            try {
                listaDeVueltaEjerciciosPorParteDelCuerpo = (ArrayList<Ejercicio>) t.get();
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
                return listaDeVueltaEjerciciosPorParteDelCuerpo;
            }

    }
}
