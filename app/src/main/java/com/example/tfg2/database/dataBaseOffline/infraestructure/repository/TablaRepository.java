package com.example.tfg2.database.dataBaseOffline.infraestructure.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB.TablaRoomDatabase;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.TareaAddEjercicio;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.TareaAddTablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.TareaComprobarTablaLocal;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TablaRepository {
    public static DaoTablaLocal daoTablaLocal;
    private LiveData<List<TablaLocal>> mAllTablas;

    public TablaRepository(Application application) {
        TablaRoomDatabase db = TablaRoomDatabase.getDatabase(application);
        daoTablaLocal = db.daoTablaLocal();
        mAllTablas = daoTablaLocal.getAll();
    }
    public static  List<TablaLocal>  comprobarTablaPorNombre(String nombre) {
        List<TablaLocal> tablaLocal = null;
        FutureTask t = new FutureTask(new TareaComprobarTablaLocal(nombre));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            tablaLocal = (List<TablaLocal>) t.get();
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
            return tablaLocal;
        }
    }
    public LiveData<List<TablaLocal>> getAllTablas() {
        return mAllTablas;
    }

    public boolean insertarTabla(TablaLocal tablaLocal) {
        FutureTask t = new FutureTask(new TareaAddTablaLocal(tablaLocal));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
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
            return insercionOK;
        }
    }

}
