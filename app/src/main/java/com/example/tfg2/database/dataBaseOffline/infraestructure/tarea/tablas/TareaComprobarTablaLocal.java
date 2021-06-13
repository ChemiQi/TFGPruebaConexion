package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

import java.util.concurrent.Callable;

public class TareaComprobarTablaLocal implements Callable {
    private String nombre;
    public TareaComprobarTablaLocal(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Object call() throws Exception {
        try{

            return TablaRepository.daoTablaLocal.comprobarTablaPorNombre(nombre);
        }catch (Exception e){
            return null;
        }
    }
}
