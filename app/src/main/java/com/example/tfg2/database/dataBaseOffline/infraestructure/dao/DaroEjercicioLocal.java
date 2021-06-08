package com.example.tfg2.database.dataBaseOffline.infraestructure.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;

import java.util.List;

@Dao
public interface DaroEjercicioLocal {
    @Insert
    void insert(EjercicioLocal ejercicioLocal);

    @Delete
    void delete(EjercicioLocal ejercicioLocal);

    @Update
    void update(EjercicioLocal ejercicioLocal);

    @Query("SELECT * FROM ejercicio_user")
    LiveData<List<EjercicioLocal>> getAll();

    @Query("SELECT MAX(idEjercicio) FROM ejercicio_user")
    int getIdEjercicio();

    @Query("SELECT * FROM ejercicio_user")
    List<EjercicioLocal> getEjercicios();

    @Query("Select * FROM ejercicio_user WHERE idEjercicio = :idEjercicio")
    EjercicioLocal getEjercicioPorId(int idEjercicio);
}
