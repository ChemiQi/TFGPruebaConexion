package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

import java.util.List;
import java.util.concurrent.Callable;

public class AddTablaEjercicioRelacion implements Callable {
    List<TablaEjercicioRelacion> tablaEjercicioRelacions;
    public AddTablaEjercicioRelacion(List<TablaEjercicioRelacion> tablaEjercicioRelacion) {
        this.tablaEjercicioRelacions = tablaEjercicioRelacion;
    }

    @Override
    public Object call() throws Exception {
        try{
            TablaEjercicioRelacionRepository.mEjercicioTablaDao.inserts(tablaEjercicioRelacions);
            return true;
        }catch (Exception e){

            return false;
        }
    }
}
