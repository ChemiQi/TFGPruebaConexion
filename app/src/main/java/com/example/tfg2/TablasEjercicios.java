package com.example.tfg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TablasEjercicios extends AppCompatActivity {
    EjerciciosDescargadosFr ejerciciosDescargadosFr = new EjerciciosDescargadosFr();
    EjerciciosLocalesFr ejerciciosLocalesFr = new EjerciciosLocalesFr();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablas_ejercicios);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_ejercicios);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(ejerciciosLocalesFr);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.ejercicios_descargados:
                    loadFragment(ejerciciosDescargadosFr);
                    return true;
                case R.id.ejercicios_locales:
                    loadFragment(ejerciciosLocalesFr);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_menuEjercicios,fragment);
        transaction.commit();
    }
}