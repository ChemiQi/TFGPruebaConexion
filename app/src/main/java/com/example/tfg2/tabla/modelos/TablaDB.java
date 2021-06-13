package com.example.tfg2.tabla.modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.user.clases.CurrentUser;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TablaDB {
    public static Integer obtenerIdTablaUser()
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            System.out.println("Error obtener ligas, conexion = null");
            return null;
        }
        int resultadoInt = 0;
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT MAX(idtabla_usuario) as m from tabla_usuario;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                resultadoInt = resultado.getInt("idLiga");

            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return resultadoInt;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    } // FIN OBTENER LIGAS _____________

    public static Integer prueba(Tabla tabla){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        Integer numeroResultado = null;

        if(conexion == null) {
            return null;
        }
        try {
            conexion.setAutoCommit(false);
            Statement sentencia = conexion.createStatement();
            //String ordensql1 = "INSERT into factura (idempleado,idcliente,precio) values (?,?,?);";
            String ordenSQL = "SELECT MAX(idtabla_usuario) as m from tabla_usuario;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                numeroResultado = resultado.getInt("m");

            }

            //-------------------------------------------------------------------------------------------

            if(numeroResultado != null){
                numeroResultado++;
            }
            System.out.println("----" + numeroResultado);
            String ordenSql2 = "INSERT INTO tabla_usuario (idtabla_usuario, user_iduser, descripcion) VALUES (?, ?, ?);";
            PreparedStatement pst = conexion.prepareStatement(ordenSql2);
            pst.setInt(1,numeroResultado);
            pst.setInt(2, CurrentUser.getUser().getId());
            pst.setString(3,tabla.getNombre());

            int filasafectadas3 = pst.executeUpdate();

            conexion.commit();
            return numeroResultado;
        } catch (SQLException ex) {
            System.out.println("fallo en el setAutocomit");
            try {
                System.out.println("rollback");
                conexion.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error rollback");
                return null;
            }
            return null;
        }
    }

    public static boolean addEjerciciosTablaDB(Tabla tabla) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            conexion.setAutoCommit(false);
            Statement sentencia = conexion.createStatement();
            for (int i = 0; i < tabla.getDias() ; i++) {
                ArrayList<EjercicioInfo> ejercicioInfos = tabla.getListaDiasEjercicio().get(i);
                for (EjercicioInfo e:ejercicioInfos) {


                }
            }
            sentencia.close();
            conexion.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static ArrayList<Tabla> obtenerTablasTFG() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        //---------------------------------
        ArrayList<Tabla> tablaTfg = new ArrayList<>();
        try {
            String ordensql = "select *  from tabla_ejercicios;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while(resultadosql.next())
            {
                int idTabla = resultadosql.getInt("idtabla_ejercicios");
                String nombre = resultadosql.getString("nombre");
                String descripcion = resultadosql.getString("descripcion");
                int dias = resultadosql.getInt("dias");


                tablaTfg.add(new Tabla(idTabla,nombre,descripcion,dias));
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return tablaTfg;
        } catch (SQLException e) {
            return null;
        }
    }

    public static List<TablaEjercicioRelacion> obtenerTablaPorIdTabla(int idTabla) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        //---------------------------------
        List<TablaEjercicioRelacion> tablaTfg = new ArrayList<>();
        try {
            String ordensql = "select *  from info_tabla_ejercicios where idtabla_ejercicios = ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1,idTabla);
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while(resultadosql.next())
            {
                int idejercicio = resultadosql.getInt("idejercicio");
                int serie = resultadosql.getInt("series");
                int repeticiones = resultadosql.getInt("repeticiones");
                int dia = resultadosql.getInt("dia");


                tablaTfg.add(new TablaEjercicioRelacion(idTabla,idejercicio,repeticiones,serie,dia));
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return tablaTfg;
        } catch (SQLException e) {
            return null;
        }
    }
}
