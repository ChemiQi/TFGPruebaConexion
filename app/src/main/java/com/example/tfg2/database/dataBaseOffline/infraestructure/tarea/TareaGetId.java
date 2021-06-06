package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

import java.util.concurrent.Callable;

public class TareaGetId implements Callable {
    @Override
    public Object call() throws Exception {
        try{
            System.out.println(EjercicioRepository.mEjercicioDao.getIdEjercicio());
            return EjercicioRepository.mEjercicioDao.getIdEjercicio();
        }catch (Exception e){
            System.out.println("ERROR AL INSERTAR EJERCICIO");
            return null;
        }
    }
}
