package com.example.tfg2.musculos.clases;

public class Musculo {
    private int idMusculo;
    private String nombreMusculo;
    private int idZonaCuerpo;

    public Musculo(int idMusculo, String nombreMusculo, int idZonaCuerpo) {
        this.idMusculo = idMusculo;
        this.nombreMusculo = nombreMusculo;
        this.idZonaCuerpo = idZonaCuerpo;
    }

    public Musculo(String nombreMusculo, int idZonaCuerpo) {
        this.nombreMusculo = nombreMusculo;
        this.idZonaCuerpo = idZonaCuerpo;
    }

    public Musculo() {
    }

    public int getIdMusculo() {
        return idMusculo;
    }

    public void setIdMusculo(int idMusculo) {
        this.idMusculo = idMusculo;
    }

    public String getNombreMusculo() {
        return nombreMusculo;
    }

    public void setNombreMusculo(String nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }

    public int getIdZonaCuerpo() {
        return idZonaCuerpo;
    }

    public void setIdZonaCuerpo(int idZonaCuerpo) {
        this.idZonaCuerpo = idZonaCuerpo;
    }

    @Override
    public String toString() {
        return "Musculo{" +
                "idMusculo=" + idMusculo +
                ", nombreMusculo='" + nombreMusculo + '\'' +
                ", idZonaCuerpo=" + idZonaCuerpo +
                '}';
    }
}
