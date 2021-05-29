package com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaroEjercicioLocal;

@Database(entities = {EjercicioLocal.class},version = 1,exportSchema = false)
public abstract class EjercicioRoomDatabase extends RoomDatabase {
    public abstract DaroEjercicioLocal ejercicioDao();
    private static EjercicioRoomDatabase INSTANCE;

    public static EjercicioRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EjercicioRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EjercicioRoomDatabase.class, "ejercicio_database")
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
