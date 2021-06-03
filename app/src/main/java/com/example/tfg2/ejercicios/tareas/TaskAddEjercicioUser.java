package com.example.tfg2.ejercicios.tareas;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.modelos.EjercicioDB;


import java.util.concurrent.Callable;

public class TaskAddEjercicioUser implements Callable {
    EjercicioLocal ejercicioLocal = null;
    public TaskAddEjercicioUser(EjercicioLocal ejercicioLocal) {
        this.ejercicioLocal = ejercicioLocal;
    }

    @Override
    public Object call() throws Exception {
       return EjercicioDB.addEjercicioUsuario(ejercicioLocal);
    }
}
