package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicioRelacionViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicoRelacionInfoViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;
import com.example.tfg2.ejercicioDatos.adapter.ListaEjercicioDatosAdapter;
import com.example.tfg2.ejercicioDatos.viewHolder.EjercicioDatosViewHolder;
import com.example.tfg2.ejercicios.viewHolder.EjercicioPonerInfoViewHolder;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;
import com.example.tfg2.utilidades.SpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class PonerDatosPorEjercicioActivity extends AppCompatActivity {
    private TextView txt_nombreEjercicio_ponerDatosPorEjercicio;
    private RecyclerView rv_datosEjercicio_ponerDatosPorEjercicio;
    private List<TablaEjercicioRelacionInfo> listaDeDatos;
    private ListaEjercicioDatosAdapter listaTablaLocalAdapter;
    private TablaEjercicoRelacionInfoViewModel te;
    private TablaEjercicioRelacionViewModel tablaEjercicioRelacionViewModel;
    private TablaEjercicioRelacion tablaEjercicioRelacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poner_datos_por_ejercicio);
        txt_nombreEjercicio_ponerDatosPorEjercicio = findViewById(R.id.txt_nombreEjercicio_ponerDatosPorEjercicio);
        rv_datosEjercicio_ponerDatosPorEjercicio = findViewById(R.id.rv_datosEjercicio_ponerDatosPorEjercicio);

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto*0.62));


        Intent intent = getIntent();
         tablaEjercicioRelacion = (TablaEjercicioRelacion)intent.getSerializableExtra(EjercicioPonerInfoViewHolder.EXTRA_TABLAEJERCICIORELACION_PARAPONERDATOS);
        EjercicioLocal ejercicioLocal = (EjercicioLocal) intent.getSerializableExtra(EjercicioPonerInfoViewHolder.EXTRA_EJERCICIO_PARAEJERCICIOINFO);
        if(ejercicioLocal != null){
            txt_nombreEjercicio_ponerDatosPorEjercicio.setText(String.valueOf(ejercicioLocal.getNombre()));
        }

        te = ViewModelProviders.of(this).get(TablaEjercicoRelacionInfoViewModel.class);
        tablaEjercicioRelacionViewModel= ViewModelProviders.of(this).get(TablaEjercicioRelacionViewModel.class);
        List<TablaEjercicioRelacionInfo> tablaEjercicioRelacionInfos = new ArrayList<>();
        if(!te.buscarDatos(tablaEjercicioRelacion.getIdEjercicio(),tablaEjercicioRelacion.getIdTabla())){
            for(int i = 1 ; i <= tablaEjercicioRelacion.getSeries();i++){
                tablaEjercicioRelacionInfos.add(new TablaEjercicioRelacionInfo(tablaEjercicioRelacion,i));
            }
            if(te.insertarDatos(tablaEjercicioRelacionInfos)){
                listaDeDatos = te.getDatosPorIdEjercicioEIdTabla(tablaEjercicioRelacion.getIdEjercicio(),tablaEjercicioRelacion.getIdTabla());
            }
        }else{
            listaDeDatos = te.getDatosPorIdEjercicioEIdTabla(tablaEjercicioRelacion.getIdEjercicio(),tablaEjercicioRelacion.getIdTabla());
        }
        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
        rv_datosEjercicio_ponerDatosPorEjercicio.addItemDecoration(spacingItemDecorator);

        listaTablaLocalAdapter = new ListaEjercicioDatosAdapter(listaDeDatos,tablaEjercicioRelacion,this,te);
        rv_datosEjercicio_ponerDatosPorEjercicio.setAdapter(listaTablaLocalAdapter);
        rv_datosEjercicio_ponerDatosPorEjercicio.setLayoutManager(new LinearLayoutManager(this));



    }


    public void guardatDatos(View view) {
        TablaEjercicioRelacionInfo maxData = te.getMaxDataPorIdEjercicioYTabla(tablaEjercicioRelacion.getIdTabla(),tablaEjercicioRelacion.getIdEjercicio());
        tablaEjercicioRelacion.setPesoMax(maxData.getPeso());
        tablaEjercicioRelacion.setRepPesoMax(maxData.getRepeticiones());
        tablaEjercicioRelacionViewModel.update(tablaEjercicioRelacion);
        finish();
    }

    public void volver(View view) {
        finish();
    }
}