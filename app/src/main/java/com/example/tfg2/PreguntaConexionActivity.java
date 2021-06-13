package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.user.clases.CurrentUser;

public class PreguntaConexionActivity extends AppCompatActivity {
    private ImageView imageView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_conexion);
        imageView5 = findViewById(R.id.imageView5);
        try {
            imageView5.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo_demop));
        }catch (Exception e){

        }
    }


    public void sinConexion(View view) {
        CurrentUser.setUser(null);
        BaseDB.setInternet(false);
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    public void irIniciarSesion(View view) {
        BaseDB.setInternet(true);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}