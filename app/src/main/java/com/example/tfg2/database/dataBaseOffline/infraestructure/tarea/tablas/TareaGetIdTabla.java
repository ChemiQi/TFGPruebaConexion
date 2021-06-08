package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

public class TareaGetIdTabla implements java.util.concurrent.Callable {
    @Override
    public Object call() throws Exception {
        return TablaRepository.daoTablaLocal.getIdMax();
    }
}
