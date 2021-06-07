package com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaroEjercicioLocal;

@Database(entities = {TablaEjercicioRelacion.class},version = 1,exportSchema = false)
public abstract class TablaEjercicioRelacionRoomDatabase extends RoomDatabase {
    public abstract DaoTablaEjercicioRelacion tablaEjercicioDao();
    private static TablaEjercicioRelacionRoomDatabase INSTANCE;

    public static TablaEjercicioRelacionRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EjercicioRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TablaEjercicioRelacionRoomDatabase.class, "tabla_ejercicio_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
