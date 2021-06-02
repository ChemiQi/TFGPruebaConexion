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

        btn_aEntrenar_menu = (Button) findViewById(R.id.btn_aEntrenar_menu);
        btn_verCalenadrio_menu = (Button) findViewById(R.id.btn_verCalendario_menu);
        btn_crearTabla_menu = (Button) findViewById(R.id.btn_crearTabla_menu);
        btn_ajustes_menu = (Button) findViewById(R.id.bnt_ajustes_menu);

    }

    public void irVerTablas(View view) {
        Intent intent = new Intent(this,TablasMenu.class);
        startActivity(intent);
    }

    public void irEjercicios(View view) {
        Intent intent = new Intent(this,TablasEjercicios.class);
        startActivity(intent);
    }
}