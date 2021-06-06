package com.example.tfg2.database.dataBaseOffline.infraestructure.repository.roomDB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;



import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaoTablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.dao.DaroEjercicioLocal;

@Database(entities = {TablaLocal.class},version = 1,exportSchema = false)
public abstract class TablaRoomDatabase extends RoomDatabase {
    public abstract DaoTablaLocal daoTablaLocal();
    private static TablaRoomDatabase INSTANCE;

    public static TablaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EjercicioRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TablaRoomDatabase.class, "tabla_database")
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
