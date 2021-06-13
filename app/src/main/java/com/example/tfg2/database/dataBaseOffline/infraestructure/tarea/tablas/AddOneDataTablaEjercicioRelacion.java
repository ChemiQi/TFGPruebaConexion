package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionRepository;

import java.util.concurrent.Callable;

public class AddOneDataTablaEjercicioRelacion implements Callable {
    private  TablaEjercicioRelacion tablaEjercicioRelacion;
    public AddOneDataTablaEjercicioRelacion(TablaEjercicioRelacion tablaEjercicioRelacion) {
        this.tablaEjercicioRelacion = tablaEjercicioRelacion;
    }

    @Override
    public Object call() throws Exception {
        try{
            TablaEjercicioRelacionRepository.mEjercicioTablaDao.insert(tablaEjercicioRelacion);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
