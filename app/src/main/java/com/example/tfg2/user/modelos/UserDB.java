package com.example.tfg2.user.modelos;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.user.clases.CurrentUser;
import com.example.tfg2.user.clases.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                int idUser = resultado.getInt("iduser");
                String userName = resultado.getString("usuario");
                String email = resultado.getString("email");
                String pass = resultado.getString("password");

                CurrentUser.setUser(new User(idUser,userName,email,pass));
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

    public static Boolean createNewUser(User user) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "INSERT INTO user ( usuario ,email, password) VALUES (?, ?, ?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, user.getNameUser());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());

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
