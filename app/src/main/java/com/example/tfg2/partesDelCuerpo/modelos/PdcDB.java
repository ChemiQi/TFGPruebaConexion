package com.example.tfg2.partesDelCuerpo.modelos;

import android.util.Log;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class PdcDB {
    public static List<PartesDelCuerpo> obtenerPartes() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            System.out.println("Error obtener partesDelCuerpo, conexion = null");
            return null;
        }
        List<PartesDelCuerpo> partesDelCuerpos = new ArrayList<PartesDelCuerpo>();
        try {
            Statement sentencia = conexion.createStatement();
            System.out.println("1");
            String ordenSQL = "SELECT * FROM zonas_cuerpo;";
            System.out.println("2");
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            System.out.println("3");
            while(resultado.next())
            {
                System.out.println("4");
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");

                PartesDelCuerpo l = new PartesDelCuerpo(id, nombre);
                partesDelCuerpos.add(l);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return partesDelCuerpos;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }
}
