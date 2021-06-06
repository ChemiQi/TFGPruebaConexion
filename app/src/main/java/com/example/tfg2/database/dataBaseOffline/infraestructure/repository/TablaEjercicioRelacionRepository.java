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
import com.example.tfg2.ejercicios.clases.EjercicioInfo;

import java.util.ArrayList;
import java.util.List;

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

    public boolean saveDataTablaEjercicio(TablaLocal tablaLocal, ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio) {
    }
}
