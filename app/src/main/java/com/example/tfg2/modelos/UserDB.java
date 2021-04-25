package com.example.tfg2.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDB {
    public static boolean loginUser(String usuario, String password){
        boolean loginOk = false;
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            System.out.println("Error de conexion");
            return false;
        }
        try{
            String ordenSQL = "Select * from user where usuario like ? and password like ?";
            PreparedStatement pst = conexion.prepareStatement(ordenSQL);
            pst.setString(1,usuario);
            pst.setString(2,password);
            ResultSet resultado = pst.executeQuery();
            while(resultado.next())
            {

                loginOk =  true;
            }
            resultado.close();
            pst.close();
            conexion.close();
            return loginOk;
        }catch (Exception e){
            System.out.println("Login false");
            return false;
        }
    }
}
