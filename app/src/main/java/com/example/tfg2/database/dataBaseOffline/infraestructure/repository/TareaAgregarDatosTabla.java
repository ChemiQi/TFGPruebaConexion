package com.example.tfg2.database.dataBaseOffline.infraestructure.repository;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;

import java.util.List;
import java.util.concurrent.Callable;

public class TareaAgregarDatosTabla implements Callable {
    private List<TablaEjercicioRelacionInfo> tablaEjercicioRelacionInfos;
    public TareaAgregarDatosTabla(List<TablaEjercicioRelacionInfo> tablaEjercicioRelacionInfos) {
        this.tablaEjercicioRelacionInfos = tablaEjercicioRelacionInfos;
    }

    @Override
    public Object call() throws Exception {
        try{
            TablaEjercicioRelacionInfoRepository.mTablaEjercicioRelacionInfoDao.inserts(tablaEjercicioRelacionInfos);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
