package com.example.tfg2.ejercicios.clases;

import android.graphics.Bitmap;

public class FotoEjercicio {
    private Bitmap foto;
    private int idEjercicio;

    public FotoEjercicio( Bitmap foto, int idLiga) {
        this.foto = foto;
        this.idEjercicio = idLiga;
    }


    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public int getIdLiga() {
        return idEjercicio;
    }

    public void setIdLiga(int idLiga) {
        this.idEjercicio = idLiga;
    }
}
