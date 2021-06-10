package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionInfoRepository;

import java.util.concurrent.Callable;

public class TareaBorrarInfoPorIdTabla implements Callable {
    private int idTabla;
    public TareaBorrarInfoPorIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    @Override
    public Object call() throws Exception {
        try{TablaEjercicioRelacionInfoRepository.mTablaEjercicioRelacionInfoDao.borrarPorIdTabla(idTabla);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
