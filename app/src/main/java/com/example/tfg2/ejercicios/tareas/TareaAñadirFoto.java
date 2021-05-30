package com.example.tfg2.ejercicios.tareas;

import android.graphics.Bitmap;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.modelos.EjercicioDB;

import java.util.concurrent.Callable;

public class TareaAñadirFoto implements Callable {

    Bitmap foto;
    private int id;
    public TareaAñadirFoto(Bitmap foto, int id) {
        this.foto = foto;
        this.id = id;
    }

    @Override
    public Object call() throws Exception {
        return EjercicioDB.ponerFoto(foto,id);
    }
}
