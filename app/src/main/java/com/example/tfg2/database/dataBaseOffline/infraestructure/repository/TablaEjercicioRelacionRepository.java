package com.example.tfg2.database.dataBaseOffline.infraestructure.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaroEjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB.EjercicioRoomDatabase;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB.TablaEjercicioRelacionRoomDatabase;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas.AddOneDataTablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas.AddTablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas.TareaAddTablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas.TareaComprobarEjercicioEnUso;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas.TareaObtenerTablaInfoPorIdTabla;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TablaEjercicioRelacionRepository {
    public static DaoTablaEjercicioRelacion mEjercicioTablaDao;
    private LiveData<List<TablaEjercicioRelacion>> mAllTablaEjercicio;

    public TablaEjercicioRelacionRepository(Application application){
        TablaEjercicioRelacionRoomDatabase db = TablaEjercicioRelacionRoomDatabase.getDatabase(application);
        mEjercicioTablaDao = db.tablaEjercicioDao();
        mAllTablaEjercicio = mEjercicioTablaDao.getAll();
    }

    public LiveData<List<TablaEjercicioRelacion>> getAllTablasEjercicios() {
        return mAllTablaEjercicio;
    }

    public boolean saveDataTablaEjercicio(List<TablaEjercicioRelacion> tablaEjercicioRelacion) {
        FutureTask t = new FutureTask(new AddTablaEjercicioRelacion(tablaEjercicioRelacion));
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

    public boolean saveDataTablaEjercicio(TablaEjercicioRelacion tablaEjercicioRelacion){
        FutureTask t = new FutureTask(new AddOneDataTablaEjercicioRelacion(tablaEjercicioRelacion));
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

    public LiveData<List<TablaEjercicioRelacion>> getAllInfo() {
        return mAllTablaEjercicio;
    }

    public List<TablaEjercicioRelacion> getTablaPorIdTabla(int idTabla) {
        FutureTask t = new FutureTask(new TareaObtenerTablaInfoPorIdTabla(idTabla));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        List<TablaEjercicioRelacion> tablaEjercicioRelacions = null;
        try {
            tablaEjercicioRelacions = (List<TablaEjercicioRelacion>) t.get();
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
            return tablaEjercicioRelacions;
        }
    }

    public boolean deleteDatosTabla(int idTabla) {
        FutureTask t = new FutureTask(new TareaBorrarDatosTablaPorId(idTabla));
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

    public boolean comprobarEjercicioEnUso(int idEjercicio) {
        FutureTask t = new FutureTask(new TareaComprobarEjercicioEnUso(idEjercicio));
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
