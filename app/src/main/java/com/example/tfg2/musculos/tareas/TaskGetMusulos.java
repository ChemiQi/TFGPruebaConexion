package com.example.tfg2.musculos.tareas;


import com.example.tfg2.musculos.modelos.MusculoDB;

import java.util.List;

public class TaskGetMusulos implements java.util.concurrent.Callable {
    @Override
    public Object call() throws Exception {
        return MusculoDB.obtenerMusculosDB();
    }
}
