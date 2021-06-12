package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EntrenamientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenamiento);
    }

    public void irTablas(View view) {
        Intent intent = new Intent(this,TablasMenu.class);
        startActivity(intent);
    }

    public void irEjercicios(View view) {
        Intent intent = new Intent(this,TablasEjercicios.class);
        startActivity(intent);
    }
}