package com.example.tfg2.ejercicios.modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.musculos.controladores.MusculoController;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.user.clases.CurrentUser;
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
            String ordensql = "SELECT eu.*, m.nombre as nombremusculo, zc.id as idpc FROM ejercicio_user as eu INNER JOIN  musculos as m ON  eu.idmusculos  = m.idmusculos INNER JOIN zonas_cuerpo as zc ON m.zonas_cuerpo_id = zc.id WHERE eu.iduser like ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1,user);
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while(resultadosql.next())
            {
                int idJercicio = resultadosql.getInt("idejercicio_user");
                String nombre = resultadosql.getString("nombre");
                String descripcion = resultadosql.getString("descripcion");
                String nombreMusculo = resultadosql.getString("nombremusculo");
                int idMusculo = resultadosql.getInt("idmusculos");
                Blob imagen = resultadosql.getBlob("imagenEjercicio");
                Bitmap bmImage = ImagenesBlobBitmap.blob_to_bitmap(imagen,200,200);
                int idParteDelCuerpo = resultadosql.getInt("idpc");

                listaEjercicios.add(new Ejercicio(idJercicio,new Musculo(idMusculo,nombreMusculo),new PartesDelCuerpo(idParteDelCuerpo),nombre,descripcion,bmImage));
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return listaEjercicios;
        } catch (SQLException e) {
            System.out.println("ERROR COGER EJERCICIO USER");
            return null;

        }
    }
    public static boolean addEjercicioUsuario(EjercicioLocal j) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "INSERT INTO ejercicio_user ( iduser ,idmusculos, nombre,descripcion,imagenEjercicio) VALUES (?,?,?,?,?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            //pst.setInt(1, CurrentUser.getUser().getId());
            pst.setInt(1, 1);
            pst.setInt(2, MusculoController.obtenerMusculosPorNombre(j.getNombreMusculo()).getIdMusculo());
            pst.setString(3,j.getNombre());
            pst.setString(4,j.getDescripcion());

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap foto = ImagenesBlobBitmap.bytes_to_bitmap(j.getImagenEjercicio());
                foto.compress(Bitmap.CompressFormat.PNG, 0, baos);
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                pst.setBinaryStream(5, bais);


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
            System.out.println("ERROR AÑADIR");
            System.out.println(e2);
            return false;
        }
    }
    public static boolean comprobarEjercicioUser(EjercicioLocal j, int user){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        boolean resultado = false;
        if(conexion == null)
        {
            return resultado;
        }
        //---------------------------------
        ArrayList<Ejercicio> listaEjercicios = new ArrayList<>();
        try {
            String ordensql = "Select * from ejercicio_user where nombre like ? and iduser like ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1,j.getNombre());
            pst.setInt(2,user);
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while(resultadosql.next())
            {
             resultado = true;
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return resultado;
        } catch (SQLException e) {
            System.out.println("ERROR COGER EJERCICIO USER");
            return resultado;

        }
    }

    public static Ejercicio obtenerEjercicioPorId(int idEjercicio) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }

        Ejercicio ejercicio = null;
        try {
            String ordensql = "Select e.*, m.nombre as nombremusculo, zc.id as idpc FROM ejercicio as e INNER JOIN musculos as m ON  e.idmusculos  = m.idmusculos INNER JOIN zonas_cuerpo as zc ON m.zonas_cuerpo_id = zc.id  where idEjercicio like ?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, idEjercicio);
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while (resultadosql.next()) {
                int idJercicio = resultadosql.getInt("idejercicio");
                String nombre = resultadosql.getString("nombre");
                String descripcion = resultadosql.getString("descripcion");
                String nombreMusculo = resultadosql.getString("nombremusculo");
                int idMusculo = resultadosql.getInt("idmusculos");
                Blob imagen = resultadosql.getBlob("imagenEjercicio");
                Bitmap bmImage = ImagenesBlobBitmap.blob_to_bitmap(imagen, 200, 200);
                int idParteDelCuerpo = resultadosql.getInt("idpc");

                ejercicio = new Ejercicio(idJercicio,new Musculo(idMusculo, nombreMusculo), new PartesDelCuerpo(idParteDelCuerpo), nombre, descripcion, bmImage);
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return ejercicio;
        } catch (SQLException e) {
            System.out.println("ERROR COGER EJERCICIO DB GLOBAL");
            return null;

        }
    }


    public static boolean borrarEjercicioUsuarioPorId(int id) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return false;
        }

        Boolean ok = false;
        try {
            String ordensql = "DELETE FROM ejercicio_user WHERE idejercicio_user = ?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, id);
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("ERROR COGER EJERCICIO DB GLOBAL");
            return false;

        }
    }
}
