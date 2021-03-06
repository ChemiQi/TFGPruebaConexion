package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

import java.util.concurrent.Callable;

public class TareaAddTablaLocal implements Callable {
    private TablaLocal tablaLocal;
    public TareaAddTablaLocal(TablaLocal tablaLocal) {
        this.tablaLocal = tablaLocal;
    }

    @Override
    public Object call() throws Exception {
        try{
            TablaRepository.daoTablaLocal.insert(tablaLocal);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
