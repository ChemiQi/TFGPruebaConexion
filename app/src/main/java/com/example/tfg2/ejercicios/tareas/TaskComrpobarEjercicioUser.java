package com.example.tfg2.ejercicios.tareas;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.modelos.EjercicioDB;

import java.util.concurrent.Callable;

public class TaskComrpobarEjercicioUser implements Callable {
    EjercicioLocal e;
    int idUser;
    public TaskComrpobarEjercicioUser(EjercicioLocal ejercicioLocal, int idUser) {
        this.e = ejercicioLocal;
        this.idUser = idUser;
    }

    @Override
    public Object call() throws Exception {
        return EjercicioDB.comprobarEjercicioUser(e,idUser);
    }
}
