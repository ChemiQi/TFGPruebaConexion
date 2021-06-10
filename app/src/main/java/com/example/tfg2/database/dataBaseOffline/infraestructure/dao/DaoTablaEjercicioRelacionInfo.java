package com.example.tfg2.database.dataBaseOffline.infraestructure.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
    @Query("Select * FROM tabla_ejercicio_info WHERE idEjercicio = :idEjercicio and idTabla = :idTabla")
    List<TablaEjercicioRelacionInfo> comprobarDatosExistentes(int idEjercicio, int idTabla);
    @Query("SELECT * FROM tabla_ejercicio_info WHERE idEjercicio = :idEjercicio and idTabla = :idTabla")
    List<TablaEjercicioRelacionInfo> getDatosPorEjercicioYtabla(int idEjercicio, int idTabla);
    @Update
    void update(TablaEjercicioRelacionInfo tablaEjercicioRelacionInfo);

    @Query("SELECT * FROM tabla_ejercicio_info WHERE idEjercicio = :idEjercicio and idTabla = :idTabla ORDER BY peso DESC LIMIT 1" )
    TablaEjercicioRelacionInfo getMaxData(int idEjercicio, int idTabla);
    @Query("DELETE FROM tabla_ejercicio_info WHERE idTabla = :idTabla")
    void borrarPorIdTabla(int idTabla);
    @Query("Select * FROM tabla_ejercicio_info")
    List<TablaEjercicioRelacionInfo> getAllNoLiveData();
}
