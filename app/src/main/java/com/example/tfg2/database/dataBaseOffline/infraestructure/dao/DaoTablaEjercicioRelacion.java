package com.example.tfg2.database.dataBaseOffline.infraestructure.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;

import java.util.List;

@Dao
public interface DaoTablaEjercicioRelacion {
    @Query("SELECT * FROM tabla_has_ejercicios")
    LiveData<List<TablaEjercicioRelacion>> getAll();
}
