package com.example.tfg2.tabla.tareas;

import com.example.tfg2.tabla.modelos.TablaDB;

public class TareaObtenerTablasTFG implements java.util.concurrent.Callable {
    @Override
    public Object call() throws Exception {
        return TablaDB.obtenerTablasTFG();
    }
}
