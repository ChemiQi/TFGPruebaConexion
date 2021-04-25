package com.example.tfg2.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDB {
    public static Connection conectarConBaseDeDatos() {
        try {
            Connection conexion = DriverManager.getConnection(ConfigurationDB.URLMYSQL, ConfigurationDB.USUARIODB,ConfigurationDB.CLAVEDB);
            return conexion;
        } catch (SQLException e) {
            System.out.println("no se pudo establecer la conexion con la base de datos");
            System.out.println(e);
            return null;
        }
    }
}
