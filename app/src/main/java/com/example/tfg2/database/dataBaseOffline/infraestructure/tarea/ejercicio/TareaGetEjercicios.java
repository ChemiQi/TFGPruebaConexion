package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicio;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;

public class TareaGetEjercicios implements java.util.concurrent.Callable {
    @Override
    public Object call() throws Exception {
        return EjercicioRepository.mEjercicioDao.getEjercicios();
    }
}
