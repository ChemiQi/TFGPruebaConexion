package com.example.tfg2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TablasMenu extends AppCompatActivity {
    TablasMisTablas tablasMisTablas = new TablasMisTablas();
    TablasTablasTFG tablasTablasTFG = new TablasTablasTFG();
    TablasTablasLocales tablasTablasLocales = new TablasTablasLocales();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablas_menu);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(tablasMisTablas);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(tablasMisTablas);
                    return true;
                case R.id.secondFragment:
                    loadFragment(tablasTablasTFG);
                    return true;
                case R.id.thirdFragment:
                    loadFragment(tablasTablasLocales);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor_menuTablas,fragment);
        transaction.commit();
    }
}
