package com.example.tfg2.database.dataBaseOffline.infraestructure.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaroEjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB.EjercicioRoomDatabase;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.TareaAddEjercicio;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EjercicioRepository {

    public static DaroEjercicioLocal mEjercicioDao;
    private LiveData<List<EjercicioLocal>> mAllEjercicios;

    public EjercicioRepository(Application application){
        EjercicioRoomDatabase db = EjercicioRoomDatabase.getDatabase(application);
        mEjercicioDao = db.ejercicioDao();
        mAllEjercicios = mEjercicioDao.getAll();
    }

    public static boolean insertarEjercicio(EjercicioLocal p) {
        FutureTask t = new FutureTask(new TareaAddEjercicio(p));
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

    public LiveData<List<EjercicioLocal>> getAllEjercicios() {
        return mAllEjercicios;
    }
}