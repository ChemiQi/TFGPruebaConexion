package com.example.tfg2.database.dataBaseOffline.infraestructure.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;

import java.util.List;

@Dao
public interface DaoTablaLocal {
    @Insert
    void insert(TablaLocal tablaLocal);

    @Delete
    void delete(TablaLocal tablaLocal);

    @Update
    void update(TablaLocal tablaLocal);

    @Query("SELECT * FROM tabla_user")
    LiveData<List<TablaLocal>> getAll();

    @Query("SELECT * FROM tabla_user WHERE nombre like :nombreTabla")
    List<TablaLocal> comprobarTablaPorNombre(String nombreTabla);

    @Query("SELECT * from tabla_user order by idTabla desc limit 1")
    TablaLocal conseguirUltimaTabla();
}
