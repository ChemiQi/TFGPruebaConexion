package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicio;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;
import com.example.tfg2.utilidades.Exceptions.ExceptionNoEncontrado;

import java.util.concurrent.Callable;

public class TareaGetEjercicioLocalPorId implements Callable {
    private int idEjercicio;
    public TareaGetEjercicioLocalPorId(int id) {
        this.idEjercicio = id;
    }

    @Override
    public Object call() throws Exception {
        try {
            return EjercicioRepository.mEjercicioDao.getEjercicioPorId(idEjercicio);
        }catch (Exception e) {
            return null;
        }
    }
}
