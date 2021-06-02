package com.example.tfg2.ejercicios.modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.user.clases.User;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
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
            String ordensql = "select e.* , m.idmusculos, m.nombre as nombremusculo,zc.id as idPC from ejercicio as e INNER JOIN  musculos as m ON  e.idmusculos  = m.idmusculos INNER JOIN zonas_cuerpo as zc ON m.zonas_cuerpo_id = zc.id WHERE m.zonas_cuerpo_id like ?;";
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
                Blob imagen = resultadosql.getBlob("imagenEjercicio");
                int idParteDelCuerpo = resultadosql.getInt("idPC");
                Bitmap bmImage = ImagenesBlobBitmap.blob_to_bitmap(imagen,200,200);
                System.out.println(idMusculo + nombreMusculo);
                listaEjercicios.add(new Ejercicio(idJercicio,new Musculo(idMusculo,nombreMusculo),new PartesDelCuerpo(idParteDelCuerpo),nombre,descripcion,bmImage));
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
                Blob imagen = resultadosql.getBlob("imagenEjercicio");
                Bitmap bmImage = ImagenesBlobBitmap.blob_to_bitmap(imagen,200,200);

                listaEjercicios.add(new Ejercicio(idJercicio,musculoSeleccionado,nombre,descripcion,bmImage));
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return listaEjercicios;
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean ponerFoto( Bitmap foto, int id){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "UPDATE ejercicio SET imagenEjercicio = ? WHERE  idejercicio = ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(2,id);
            if(foto != null){
                ByteArrayOutputStream baos =new ByteArrayOutputStream();
                foto.compress(Bitmap.CompressFormat.PNG,0,baos);
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                pst.setBinaryStream(1,bais);
            }else{
                pst.setBinaryStream(1,null);
            }
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
        } catch (SQLException e) {
            return false;
        }
    }

    public static ArrayList<Ejercicio> obtenerEjerciciosUsuario(int user) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        //---------------------------------
        ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
        try {
            String ordensql = "SELECT eu.*, m.nombre, id as nombremusculo, zc.id as idpc FROM ejercicio_user as eu INNER JOIN  musculos as m ON  eu.idmusculos  = m.idmusculos INNER JOIN zonas_cuerpo as zc ON m.zonas_cuerpo_id = zc.id WHERE eu.idejercicio_user like ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1,user);
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while(resultadosql.next())
            {
                //int idJercicio = resultadosql.getInt("idejercicio_user");
                String nombre = resultadosql.getString("nombre");
                String descripcion = resultadosql.getString("descripcion");
                String nombreMusculo = resultadosql.getString("nombremusculo");
                int idMusculo = resultadosql.getInt("idmusculos");
                Blob imagen = resultadosql.getBlob("imagenEjercicio");
                Bitmap bmImage = ImagenesBlobBitmap.blob_to_bitmap(imagen,200,200);
                int idParteDelCuerpo = resultadosql.getInt("idpc");

                listaEjercicios.add(new Ejercicio(new Musculo(idMusculo,nombreMusculo),new PartesDelCuerpo(idParteDelCuerpo),nombre,descripcion,bmImage));
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return listaEjercicios;
        } catch (SQLException e) {
            System.out.println("ERRORrrrrr");
            return null;

        }
    }
}
