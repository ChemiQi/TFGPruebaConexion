package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

import java.util.concurrent.Callable;

public class TareaDeleteTabla implements Callable {
    private TablaLocal tablaLocal;
    public TareaDeleteTabla(TablaLocal tablaLocal) {
        this.tablaLocal = tablaLocal;
    }

    @Override
    public Object call() throws Exception {
        try{
            TablaRepository.daoTablaLocal.delete(tablaLocal);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
