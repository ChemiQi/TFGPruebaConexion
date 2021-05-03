package com.example.tfg2.ejercicios.modelos;

import android.graphics.Bitmap;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.ejercicios.clases.Ejercicio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EjercicioDB {
    public static boolean addEjercicio(Ejercicio j){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "INSERT INTO ejercicio ( idmusculo ,nombre, descripcion) VALUES (?,?,?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, j.getMusculo().getIdMusculo());
            pst.setString(2, j.getNombreEjercicio());
            pst.setString(3,j.getDescripcionEjercicio());

            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e2) {
            return false;
        }
    }
}
