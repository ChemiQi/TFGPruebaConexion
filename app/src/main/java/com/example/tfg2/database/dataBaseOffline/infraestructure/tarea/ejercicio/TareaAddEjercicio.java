package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.ejercicio;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;

import java.util.concurrent.Callable;

public class TareaAddEjercicio implements Callable {
    EjercicioLocal ejercicioLocal;
    public TareaAddEjercicio(EjercicioLocal p) {
        this.ejercicioLocal = p;
    }

    @Override
    public Object call() throws Exception {
        try{
            EjercicioRepository.mEjercicioDao.insert(ejercicioLocal);
            return true;
        }catch (Exception e){
            System.out.println("ERROR AL INSERTAR EJERCICIO");
            return false;
        }
    }
}
