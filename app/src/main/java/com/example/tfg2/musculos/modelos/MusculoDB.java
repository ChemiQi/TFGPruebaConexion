package com.example.tfg2.musculos.modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.musculos.clases.Musculo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MusculoDB {

    public static List<Musculo> obtenerMusculosDB() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            System.out.println("Error obtener ligas, conexion = null");
            return null;
        }
        List<Musculo> listaMusculos = new ArrayList<>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from musculos";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                int idMusculo = resultado.getInt("idmusculos");
                String nombre = resultado.getString("nombre");
                int idZonaCuerpo = resultado.getInt("zonas_cuerpo_id");

                Musculo l = new Musculo(idMusculo, nombre,idZonaCuerpo);
                listaMusculos.add(l);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return listaMusculos;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }

    public static Musculo obtenerMusculoPorNombre(String nombre){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            System.out.println("Error obtener ligas, conexion = null");
            return null;
        }
       Musculo musculo = null;
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from musculos where nombre like ?";
            PreparedStatement pst = conexion.prepareStatement(ordenSQL);
            pst.setString(1,nombre);
            ResultSet resultado = pst.executeQuery();
            while(resultado.next())
            {
                int idMusculo = resultado.getInt("idmusculos");
                String nombreMusculo = resultado.getString("nombre");
                int idZonaCuerpo = resultado.getInt("zonas_cuerpo_id");

                 musculo = new Musculo(idMusculo, nombreMusculo,idZonaCuerpo);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return musculo;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }
}
