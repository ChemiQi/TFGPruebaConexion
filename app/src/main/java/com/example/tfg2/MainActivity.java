package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
    }

    public void goRegisterActivity(View view) {
    }

    public void irContrasenaOlvidada(View view) {
    }

    public void iniciarSesion(View view) {
        if(UserDB.loginUser(edt_username_login.getText().toString(),edt_pass_login.getText().toString())){
            System.out.println("LOGIN BUENA");
        }else {
            System.out.println("Error clogin");
        }
    }
}