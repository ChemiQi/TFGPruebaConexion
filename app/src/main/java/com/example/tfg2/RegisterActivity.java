package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfg2.user.clases.User;
import com.example.tfg2.user.controladores.UserController;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    //VARIABLES DRAWABLE
    private EditText edt_name_crearcuenta;
    private EditText edt_email_crearcuenta;
    private EditText edt_pass_crearcuenta;
    private EditText edt_passrepeat_crearcuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_name_crearcuenta = (EditText) findViewById(R.id.edt_name_crearcuenta);
        edt_email_crearcuenta = (EditText) findViewById(R.id.edt_email_crearcuenta);
        edt_pass_crearcuenta = (EditText) findViewById(R.id.edt_pass_crearcuenta);
        edt_passrepeat_crearcuenta = (EditText) findViewById(R.id.edt_passrepeat_crearcuenta);

        Intent intent = getIntent();
        String nombreUsuario = intent.getStringExtra(MainActivity.EXTRA_USERNAME_STRING);
        if(nombreUsuario != null)
        edt_name_crearcuenta.setText(nombreUsuario);

    }

    public void registerUserDB(View view) {

        String nameUser = edt_name_crearcuenta.getText().toString();
        String password = edt_pass_crearcuenta.getText().toString();
        String email = edt_email_crearcuenta.getText().toString();
        if(validate()) {
            User user = new User(nameUser, email, password);

            if (UserController.newUser(user)) {
                finish();
            } else {
                Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean validate(){

        boolean validate = true;
        String user = edt_name_crearcuenta.getText().toString();
        String password = edt_pass_crearcuenta.getText().toString();
        String passwordRepeat = edt_passrepeat_crearcuenta.getText().toString();
        String email = edt_email_crearcuenta.getText().toString();

        if(user.isEmpty()){
            edt_name_crearcuenta.setError("Usuario requerido");
            validate = false;
        }
        if(password.length() < 6){
            edt_pass_crearcuenta.setError("Min 6 car??cteres");
            validate = false;
        }
        if(password.isEmpty()){
            edt_pass_crearcuenta.setError("Contrase??a requerida");
            validate = false;
        }
        if(passwordRepeat.isEmpty()){
            edt_passrepeat_crearcuenta.setError("Contrase??a requerida");
            validate = false;
        }
        if(email.isEmpty()){
            edt_email_crearcuenta.setError("Correo electr??nico requerido");
            validate = false;
        }
        if(!password.equals(passwordRepeat)){
            edt_passrepeat_crearcuenta.setError("Las contrase??as no coinciden");
            validate = false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_email_crearcuenta.setError("Introduce un correo valido");
            validate = false;
        }
        return validate;
    }
}