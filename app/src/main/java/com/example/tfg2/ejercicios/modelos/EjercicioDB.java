package com.example.tfg2.ejercicios.modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static ArrayList<Ejercicio> obtenerEjerciciosPorParteDelCuerpo(PartesDelCuerpo partesDelCuerpo) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        //---------------------------------
        ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
        try {
            String ordensql = "select e.* , m.idmusculos, m.nombre as nombremusculo from ejercicio as e INNER JOIN  musculos as m ON  e.idmusculos  = m.idmusculos WHERE m.zonas_cuerpo_id like ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1,partesDelCuerpo.getId());
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while(resultadosql.next())
            {
                int idJercicio = resultadosql.getInt("idejercicio");
                String nombre = resultadosql.getString("nombre");
                String descripcion = resultadosql.getString("descripcion");
                String nombreMusculo = resultadosql.getString("nombremusculo");
                int idMusculo = resultadosql.getInt("idmusculos");
                System.out.println(idMusculo + nombreMusculo);
                listaEjercicios.add(new Ejercicio(idJercicio,new Musculo(idMusculo,nombreMusculo),nombre,descripcion));
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return listaEjercicios;
        } catch (SQLException e) {
            return null;
        }
    }

    public static ArrayList<Ejercicio> obtenerEjerciciosPorMusculo(Musculo musculoSeleccionado) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        //---------------------------------
        ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
        try {
            String ordensql = "select *  from ejercicio WHERE idmusculos like ? ;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1,musculoSeleccionado.getIdMusculo());
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while(resultadosql.next())
            {
                int idJercicio = resultadosql.getInt("idejercicio");
                String nombre = resultadosql.getString("nombre");
                String descripcion = resultadosql.getString("descripcion");

                listaEjercicios.add(new Ejercicio(idJercicio,musculoSeleccionado,nombre,descripcion));
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return listaEjercicios;
        } catch (SQLException e) {
            return null;
        }
    }
}
