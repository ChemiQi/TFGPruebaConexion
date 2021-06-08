package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

import java.util.concurrent.Callable;

public class TareaObtenerTablaInfoPorIdTabla implements Callable {
    private int idTabla;
    public TareaObtenerTablaInfoPorIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    @Override
    public Object call() throws Exception {
        try{

            return TablaEjercicioRelacionRepository.mEjercicioTablaDao.conseguirTablasPorIdTabla(idTabla);
        }catch (Exception e){

            return null;
        }
    }
}
