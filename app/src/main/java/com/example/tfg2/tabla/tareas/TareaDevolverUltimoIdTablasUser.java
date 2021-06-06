package com.example.tfg2.tabla.tareas;

import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.modelos.TablaDB;

import java.util.concurrent.Callable;

public class TareaDevolverUltimoIdTablasUser implements Callable {
    private Tabla tabla;
    public TareaDevolverUltimoIdTablasUser(Tabla tabla) {
        this.tabla = tabla;

    }

    @Override
    public Object call() throws Exception {
        return TablaDB.prueba(tabla);
    }
}
