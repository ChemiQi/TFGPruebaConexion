package com.example.tfg2.ejercicios.tareas;

import com.example.tfg2.ejercicios.modelos.EjercicioDB;

import java.util.concurrent.Callable;

public class TareaBorrarEjercicioPorIdSubido implements Callable {
    private  int id;
    public TareaBorrarEjercicioPorIdSubido(int id) {
        this.id = id;
    }

    @Override
    public Object call() throws Exception {
        try{
            EjercicioDB.borrarEjercicioUsuarioPorId( id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
