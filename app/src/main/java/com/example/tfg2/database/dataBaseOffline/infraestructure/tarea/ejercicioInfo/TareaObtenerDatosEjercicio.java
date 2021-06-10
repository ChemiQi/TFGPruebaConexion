package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionInfoRepository;

import java.util.concurrent.Callable;

public class TareaObtenerDatosEjercicio implements Callable {
    private int idEjercicio;
    private int idTabla;
    public TareaObtenerDatosEjercicio(int idEjercicio, int idTabla) {
        this.idEjercicio = idEjercicio;
        this.idTabla = idTabla;
    }

    @Override
    public Object call() throws Exception {
        try{
            return TablaEjercicioRelacionInfoRepository.mTablaEjercicioRelacionInfoDao.getDatosPorEjercicioYtabla(idEjercicio,idTabla);
        }catch (Exception e){
            return null;
        }
    }
}
