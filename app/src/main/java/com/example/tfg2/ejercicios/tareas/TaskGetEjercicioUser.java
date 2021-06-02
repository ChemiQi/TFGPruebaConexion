package com.example.tfg2.ejercicios.tareas;

import com.example.tfg2.ejercicios.modelos.EjercicioDB;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.user.clases.User;


public class TaskGetEjercicioUser implements java.util.concurrent.Callable {
    int id ;
    public TaskGetEjercicioUser( int id) {
        this.id = id;
    }

    @Override
    public Object call() throws Exception {
        return EjercicioDB.obtenerEjerciciosUsuario(id);
    }
}
