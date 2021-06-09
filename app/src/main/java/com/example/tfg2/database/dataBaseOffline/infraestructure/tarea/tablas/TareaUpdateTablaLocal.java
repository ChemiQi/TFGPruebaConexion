package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;
import com.example.tfg2.tabla.clases.Tabla;

import java.util.concurrent.Callable;

public class TareaUpdateTablaLocal implements Callable {
    private TablaLocal tablaLocal;
    public TareaUpdateTablaLocal(TablaLocal tablaLocal) {
        this.tablaLocal = tablaLocal;
    }

    @Override
    public Object call() throws Exception {
        try{
            TablaRepository.daoTablaLocal.update(tablaLocal);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
