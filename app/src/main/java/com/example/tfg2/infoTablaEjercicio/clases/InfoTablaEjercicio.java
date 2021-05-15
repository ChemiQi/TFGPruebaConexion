package com.example.tfg2.infoTablaEjercicio.clases;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.user.clases.User;

import java.util.List;

public class InfoTablaEjercicio {
    User user;
    int idTabla;
    List<EjercicioInfo> listaEjercicios;
    int dia;

    public InfoTablaEjercicio(User user, int idTabla, List<EjercicioInfo> listaEjercicios, int dia) {
        this.user = user;
        this.idTabla = idTabla;
        this.listaEjercicios = listaEjercicios;
        this.dia = dia;
    }

    public InfoTablaEjercicio() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    public List<EjercicioInfo> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<EjercicioInfo> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}
