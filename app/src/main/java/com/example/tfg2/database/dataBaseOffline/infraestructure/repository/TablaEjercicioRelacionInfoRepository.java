package com.example.tfg2.database.dataBaseOffline.infraestructure.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaEjercicioRelacionInfo;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB.TablaEjercicioRelacionInfoRoomDatabase;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB.TablaEjercicioRelacionRoomDatabase;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicio.TareaAddEjercicio;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo.TareaActualizarDatos;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo.TareaBorrarInfoPorIdTabla;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo.TareaComrpobarDatosEjercicio;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo.TareaGetAllInfoEjercicios;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo.TareaObtenerDatosEjercicio;
import com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo.TareaObtenerMaxData;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TablaEjercicioRelacionInfoRepository {
    public static DaoTablaEjercicioRelacionInfo mTablaEjercicioRelacionInfoDao;
    private LiveData<List<TablaEjercicioRelacionInfo>> mAllTablaEjerciciosRelacionInfo;

    public TablaEjercicioRelacionInfoRepository(Application application){
        TablaEjercicioRelacionInfoRoomDatabase db = TablaEjercicioRelacionInfoRoomDatabase.getDatabase(application);
        mTablaEjercicioRelacionInfoDao = db.daoTablaEjercicioRelacionInfoDao();
        mAllTablaEjerciciosRelacionInfo = mTablaEjercicioRelacionInfoDao.getAll();
    }

    public LiveData<List<TablaEjercicioRelacionInfo>> getLiveDataAllEjercicioInfo(){
        return mAllTablaEjerciciosRelacionInfo;
    }


    public boolean comprobarDatosExistentes(int idEjercicio, int idTabla) {
        FutureTask t = new FutureTask(new TareaComrpobarDatosEjercicio(idEjercicio,idTabla));
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

    public boolean addListaInfoEjercicio(List<TablaEjercicioRelacionInfo> tablaEjercicioRelacionInfos) {
        FutureTask t = new FutureTask(new TareaAgregarDatosTabla(tablaEjercicioRelacionInfos));
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

    public List<TablaEjercicioRelacionInfo>  getDatosPorIdEjercicioTabla(int idEjercicio, int idTabla) {
        FutureTask t = new FutureTask(new TareaObtenerDatosEjercicio(idEjercicio,idTabla));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        List<TablaEjercicioRelacionInfo> insercionOK = null;
        try {
            insercionOK = (List<TablaEjercicioRelacionInfo> ) t.get();
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

    public boolean actualizarDatos(TablaEjercicioRelacionInfo tablaEjercicioRelacionInfo) {
        FutureTask t = new FutureTask(new TareaActualizarDatos(tablaEjercicioRelacionInfo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        Boolean insercionOK = null;
        try {
            insercionOK = (Boolean) t.get();
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

    public TablaEjercicioRelacionInfo getMaxData(int idTabla, int idEjercicio) {
        FutureTask t = new FutureTask(new TareaObtenerMaxData(idEjercicio,idTabla));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        TablaEjercicioRelacionInfo insercionOK = null;
        try {
            insercionOK = (TablaEjercicioRelacionInfo ) t.get();
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

    public void borrarDatosPorTabla(int idTabla) {
        FutureTask t = new FutureTask(new TareaBorrarInfoPorIdTabla(idTabla));
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

        }
    }

    public List<TablaEjercicioRelacionInfo> getAllData() {
        FutureTask t = new FutureTask(new TareaGetAllInfoEjercicios());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        List<TablaEjercicioRelacionInfo> insercionOK = null;
        try {
            insercionOK = (List<TablaEjercicioRelacionInfo>) t.get();
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
