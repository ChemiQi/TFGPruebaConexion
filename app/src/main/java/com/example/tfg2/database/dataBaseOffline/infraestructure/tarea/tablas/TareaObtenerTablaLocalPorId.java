package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

public class TareaObtenerTablaLocalPorId implements java.util.concurrent.Callable {
    int id;
    public TareaObtenerTablaLocalPorId(int id) {
        this.id = id;
    }

    @Override
    public Object call() throws Exception {
        return TablaRepository.daoTablaLocal.obtenerTablaPorId(id);
    }
}
