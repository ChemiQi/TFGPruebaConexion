package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionInfoRepository;

import java.util.concurrent.Callable;

public class TareaActualizarDatos implements Callable {
    private TablaEjercicioRelacionInfo tablaEjercicioRelacionInfo;
    public TareaActualizarDatos(TablaEjercicioRelacionInfo tablaEjercicioRelacionInfo) {
        this.tablaEjercicioRelacionInfo = tablaEjercicioRelacionInfo;
    }

    @Override
    public Object call() throws Exception {
        try {
            TablaEjercicioRelacionInfoRepository.mTablaEjercicioRelacionInfoDao.update(tablaEjercicioRelacionInfo);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
