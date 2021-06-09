package com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;

import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaEjercicioRelacionInfo;

@Database(entities = {TablaEjercicioRelacionInfo.class},version = 1,exportSchema = false)
public abstract class TablaEjercicioRelacionInfoRoomDatabase extends RoomDatabase {

    public abstract DaoTablaEjercicioRelacionInfo daoTablaEjercicioRelacionInfoDao();
    private static TablaEjercicioRelacionInfoRoomDatabase INSTANCE;

    public static TablaEjercicioRelacionInfoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TablaEjercicioRelacionInfoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TablaEjercicioRelacionInfoRoomDatabase.class, "tabla_ejercicio_info")
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
