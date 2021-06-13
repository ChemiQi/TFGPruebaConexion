package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicioRelacionViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicoRelacionInfoViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;
import com.example.tfg2.tabla.viewHolder.TablaLocalViewHolder;

public class PopUpActivarTabla extends AppCompatActivity {
    private TablaViewModel tablaViewModel;
    private TablaLocal tablaLocal;
    private TextView txt_texto_popupActivarTabla;
    private TablaEjercicoRelacionInfoViewModel tablaEjercicioRelacionInfoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_activar_tabla);
        txt_texto_popupActivarTabla = findViewById(R.id.txt_texto_ponerDatosPorEjercicio);
        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.60),(int)(alto*0.40));


        Intent intent = getIntent();
        tablaLocal = (TablaLocal) intent.getSerializableExtra(TablaLocalViewHolder.TABLA_A_ACTIVAR);

        if(tablaLocal.getActive())
            txt_texto_popupActivarTabla.setText("¿Quieres dejar de usar esta tabla? \nSe borraran los datos guardados");
    }

    public void noHacerNada(View view) {
        finish();
    }

    public void activarTabla(View view) {
        if(!tablaLocal.getActive()) {
            tablaLocal.setActive(true);
            tablaViewModel.updateTablaLocal(tablaLocal);
            finish();
        }else{
            tablaEjercicioRelacionInfoViewModel= ViewModelProviders.of(this).get(TablaEjercicoRelacionInfoViewModel.class);
            tablaLocal.setActive(false);
            tablaEjercicioRelacionInfoViewModel.borrarDatosPorTabla(tablaLocal.getIdTabla());
            tablaViewModel.updateTablaLocal(tablaLocal);
            finish();
        }
    }
}