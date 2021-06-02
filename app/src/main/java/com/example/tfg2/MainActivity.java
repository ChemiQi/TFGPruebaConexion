package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.user.clases.CurrentUser;
import com.example.tfg2.user.clases.User;
import com.example.tfg2.user.modelos.UserDB;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME_STRING ="" ;
    EditText edt_username_login;
    EditText edt_pass_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_username_login = (EditText) findViewById(R.id.edt_username_login);
        edt_pass_login = (EditText) findViewById(R.id.edt_pass_login);
        if(BaseDB.conectarConBaseDeDatos() != null){
            System.out.println("CONECTADO CORRECTAMENTE");
        }else {
            System.out.println("ERROR");
        }


    }

    public void goRegisterActivity(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void irContrasenaOlvidada(View view) {
    }

    public void iniciarSesion(View view) {
        if(BaseDB.conectarConBaseDeDatos() != null){
           User user=  UserDB.loginUser(edt_username_login.getText().toString(), edt_pass_login.getText().toString());
            if(user != null) {
                CurrentUser.setUser(user);
                System.out.println(user.getNameUser() + " " + user.getEmail());
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Hubo un error en la base de datos", Toast.LENGTH_SHORT).show();
        }


    }
}