package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicio;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;

import java.util.concurrent.Callable;

public class TareaBorrarEjercicioPorId implements Callable {
    private int idEjercicio;
    public TareaBorrarEjercicioPorId(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    @Override
    public Object call() throws Exception {
        try{
            EjercicioRepository.mEjercicioDao.deletePorId(idEjercicio);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
