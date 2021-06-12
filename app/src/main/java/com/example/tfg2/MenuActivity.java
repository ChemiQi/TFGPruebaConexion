package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tfg2.user.clases.CurrentUser;

public class MenuActivity extends AppCompatActivity {
    Button btn_aEntrenar_menu;
    Button btn_verCalenadrio_menu;
    Button btn_crearTabla_menu;
    Button btn_ajustes_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void irEntrenar(View view) {
        Intent intent = new Intent(this,EntrenarActivity.class);
        startActivity(intent);
    }

    public void cerrarSesion(View view) {
        CurrentUser.setUser(null);
        finish();
    }

    public void irEntrenamiento(View view) {
        Intent intent = new Intent(this,EntrenamientoActivity.class);
        startActivity(intent);
    }
}