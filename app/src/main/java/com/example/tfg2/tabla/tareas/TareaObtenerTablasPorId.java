package com.example.tfg2.tabla.tareas;

import com.example.tfg2.tabla.modelos.TablaDB;

import java.util.concurrent.Callable;

public class TareaObtenerTablasPorId implements Callable {
    private int idTabla;
    public TareaObtenerTablasPorId(int id) {
        this.idTabla = id;
    }

    @Override
    public Object call() throws Exception {
        return TablaDB.obtenerTablaPorIdTabla(idTabla);
    }
}
