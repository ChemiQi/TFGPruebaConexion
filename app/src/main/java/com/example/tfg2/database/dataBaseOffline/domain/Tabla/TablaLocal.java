package com.example.tfg2.database.dataBaseOffline.domain.Tabla;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tabla_user")
public class TablaLocal implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int idTabla;
    @NonNull
    String nombre;

    Boolean created;

    public TablaLocal() {
    }

    public TablaLocal(@NonNull String nombre, Boolean created) {
        this.nombre = nombre;
        this.created = created;
    }

    public TablaLocal(int idTabla, @NonNull String nombre, Boolean created) {
        this.idTabla = idTabla;
        this.nombre = nombre;
        this.created = created;
    }

    public int getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public Boolean getCreated() {
        return created;
    }

    public void setCreated(Boolean created) {
        this.created = created;
    }
}
