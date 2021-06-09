package com.example.tfg2.database.dataBaseOffline.infraestructure.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;

import java.util.List;

@Dao
public interface DaoTablaEjercicioRelacionInfo {
    @Insert
    void inserts(List<TablaEjercicioRelacionInfo> tablaEjercicioRelacions);

    @Insert
    void insert(TablaEjercicioRelacionInfo tablaEjercicioRelacionInfo);

    @Query("SELECT * FROM tabla_ejercicio_info")
    LiveData<List<TablaEjercicioRelacionInfo>> getAll();
}
