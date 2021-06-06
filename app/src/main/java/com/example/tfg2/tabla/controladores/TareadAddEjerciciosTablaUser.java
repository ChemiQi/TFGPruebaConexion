package com.example.tfg2.tabla.controladores;

import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.modelos.TablaDB;

import java.util.concurrent.Callable;

public class TareadAddEjerciciosTablaUser implements Callable {
    private Tabla tabla;
    public TareadAddEjerciciosTablaUser(Tabla tablaCreada) {
        this.tabla = tablaCreada;
    }

    @Override
    public Object call() throws Exception {
        return TablaDB.addEjerciciosTablaDB(tabla);
    }
}
