package com.example.tfg2.database.dataBaseOffline.infraestructure.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;

import java.util.List;

@Dao
public interface DaoTablaEjercicioRelacion {
    @Query("SELECT * FROM tabla_has_ejercicios")
    LiveData<List<TablaEjercicioRelacion>> getAll();

    @Insert
    void inserts(List<TablaEjercicioRelacion> tablaEjercicioRelacions);

    @Insert
    void insert(TablaEjercicioRelacion tablaEjercicioRelacion);

    @Query("SELECT * FROM tabla_has_ejercicios WHERE idTabla = :idTabla")
    List<TablaEjercicioRelacion> conseguirTablasPorIdTabla(int idTabla);
    @Query("DELETE FROM tabla_has_ejercicios WHERE idTabla = :idTabla")
    void deletePorIdTabla(int idTabla);

    @Query("SELECT * FROM tabla_has_ejercicios WHERE idEjercicio = :idEjercicio")
    List<TablaEjercicioRelacion> buscarEjercicioEnUso(int idEjercicio);
    @Update
    void update(TablaEjercicioRelacion tablaEjercicioRelacion);
}
