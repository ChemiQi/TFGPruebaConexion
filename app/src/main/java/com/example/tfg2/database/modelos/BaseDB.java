package com.example.tfg2.database.modelos;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDB {
    private static boolean internet = true;

    public static boolean isInternet() {
        return internet;
    }

    public static void setInternet(boolean interet) {
        BaseDB.internet = interet;
    }

    public static Connection conectarConBaseDeDatos() {
        if(internet) {
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Connection conexion = DriverManager.getConnection(ConfigurationDB.URLMYSQL, ConfigurationDB.USUARIODB, ConfigurationDB.CLAVEDB);
                return conexion;
            } catch (SQLException e) {
                System.out.println("no se pudo establecer la conexion con la base de datos");
                System.out.println(e);
                return null;
            }
        }else{
            return null;
        }
    }
}
