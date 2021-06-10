package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionInfoRepository;

public class TareaGetAllInfoEjercicios implements java.util.concurrent.Callable {
    @Override
    public Object call() throws Exception {
        try{
            return TablaEjercicioRelacionInfoRepository.mTablaEjercicioRelacionInfoDao.getAllNoLiveData();
        }catch (Exception e){
            return null;
        }
    }
}
