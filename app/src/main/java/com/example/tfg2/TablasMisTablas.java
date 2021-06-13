package com.example.tfg2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;
import com.example.tfg2.utilidades.SpacingItemDecorator;

import java.util.List;
import java.util.stream.Collectors;

public class TablasMisTablas extends Fragment {
    ImageButton btn_anadirTabla_MisTablas;
    View vista;
    RecyclerView rv_verMisTablas_TablasMisTablas;
    TablaViewModel tablaViewModel;
    ListaTablaLocalAdapter listaTablaLocalAdapter;
    private List<TablaLocal> tablaLocales;
    private static final int PETICION1 = 1;

    public TablasMisTablas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_first, container, false);
        btn_anadirTabla_MisTablas = vista.findViewById(R.id.btn_anadirTabla_MisTablas);
        rv_verMisTablas_TablasMisTablas = vista.findViewById(R.id.rv_verMisTablas_TablasMisTablas);
        btn_anadirTabla_MisTablas.setBackgroundColor(Color.parseColor("#00FFFFFF"));

        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);
        LiveData<List<TablaLocal>> tablasLive  = tablaViewModel.obtenerTablas();

        Context context = vista.getContext();
        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
        rv_verMisTablas_TablasMisTablas.addItemDecoration(spacingItemDecorator);
        listaTablaLocalAdapter = new ListaTablaLocalAdapter(context,tablasLive);
        rv_verMisTablas_TablasMisTablas.setAdapter(listaTablaLocalAdapter);
        rv_verMisTablas_TablasMisTablas.setLayoutManager(new LinearLayoutManager(context));

        if(tablasLive != null){
            tablasLive.observe(this, new Observer<List<TablaLocal>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onChanged(List<TablaLocal> tablaLocals) {
                    tablaLocales = tablaLocals;
                    if(tablaLocales != null) {
                        tablaLocales = tablaLocales.stream().filter(e -> e.getCreated()).collect(Collectors.toList());
                        listaTablaLocalAdapter.setListaTablasLocales(tablaLocales);
                    }
                }
            });
        }

        btn_anadirTabla_MisTablas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vista.getContext(),CrearTablaActivity.class);
                startActivityForResult(intent,PETICION1);
            }
        });

        return vista;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PETICION1){
            // LO QUE QUIERO; ACTUALIZAR
            if(resultCode == -1){ //OK

            }else if(resultCode == 0){ // CANCELADO

            }
        }
    }
}