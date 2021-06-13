package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

import java.util.concurrent.Callable;

public class TareaGetLastTabla implements Callable {
    @Override
    public Object call() throws Exception {
        try{

            return TablaRepository.daoTablaLocal.conseguirUltimaTabla();
        }catch (Exception e){
            return null;
        }
    }
}
