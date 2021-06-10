package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicioInfo;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionRepository;

import java.util.concurrent.Callable;

public class TareaActualizarDatosTablaInfo implements Callable {
    private  TablaEjercicioRelacion tablaEjercicioRelacion;
    public TareaActualizarDatosTablaInfo(TablaEjercicioRelacion tablaEjercicioRelacion) {
        this.tablaEjercicioRelacion = tablaEjercicioRelacion;
    }

    @Override
    public Object call() throws Exception {
        try{
            TablaEjercicioRelacionRepository.mEjercicioTablaDao.update(tablaEjercicioRelacion);
            return true;
        }catch (Exception e){
            return null;
        }
    }
}
