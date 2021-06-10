package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionInfoRepository;

import java.util.concurrent.Callable;

public class TareaComrpobarDatosEjercicio implements Callable {
    int idEjercicio;
    int idTabla;
    public TareaComrpobarDatosEjercicio(int idEjercicio, int idTabla) {
        this.idEjercicio =idEjercicio;
        this.idTabla = idTabla;
    }

    @Override
    public Object call() throws Exception {
        try{
            if(TablaEjercicioRelacionInfoRepository.mTablaEjercicioRelacionInfoDao.comprobarDatosExistentes(idEjercicio,idTabla).isEmpty()){
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }
}
