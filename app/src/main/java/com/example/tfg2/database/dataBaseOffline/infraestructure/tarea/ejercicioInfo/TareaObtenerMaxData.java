package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionInfoRepository;

import java.util.concurrent.Callable;

public class TareaObtenerMaxData implements Callable {
    private int idEjercicio;
    private int idTabla;
    public TareaObtenerMaxData(int idEjercicio, int idTabla) {
        this.idEjercicio = idEjercicio;
        this.idTabla = idTabla;
    }

    @Override
    public Object call() throws Exception {
        try{
            return TablaEjercicioRelacionInfoRepository.mTablaEjercicioRelacionInfoDao.getMaxData(idEjercicio,idTabla);
        }catch (Exception e){
            return null;
        }
    }
}
