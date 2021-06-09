package com.example.tfg2.database.dataBaseOffline.infraestructure.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaEjercicioRelacionInfo;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB.TablaEjercicioRelacionInfoRoomDatabase;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB.TablaEjercicioRelacionRoomDatabase;

import java.util.List;

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


}
