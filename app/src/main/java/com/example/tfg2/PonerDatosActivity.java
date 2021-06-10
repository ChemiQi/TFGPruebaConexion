package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicioRelacionViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.ejercicios.adapter.ListaEjercicioPonerInfoAdapter;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PonerDatosActivity extends AppCompatActivity {
    private TablaEjercicioRelacionViewModel  tr;
    private EjercicioViewModel ejercicioViewModel;
    private RecyclerView rv_verEjerciciosParaDatos;
    private ListaEjercicioPonerInfoAdapter listaEjercicioPonerInfoAdapter;
    List<TablaEjercicioRelacion> tablaEjercicio;
    List<EjercicioLocal> ejercicioLocals;
    Integer dia;
    TextView txt_nombreTabla_ponerDatos;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poner_datos);

        txt_nombreTabla_ponerDatos =(TextView) findViewById(R.id.txt_nombreTabla_ponerDatos);

        tr =  ViewModelProviders.of(this).get(TablaEjercicioRelacionViewModel.class);
        ejercicioViewModel = ViewModelProviders.of(this).get(EjercicioViewModel.class);
        rv_verEjerciciosParaDatos = findViewById(R.id.rv_verEjerciciosParaDatos);

        Intent intent = getIntent();
       dia = (Integer) intent.getSerializableExtra(SeleccionarGrupoEjerciciosActivity.EXTRA_EJERCICIOAENVIARAVEREJERCICIOS);
       TablaLocal tabla =(TablaLocal) intent.getSerializableExtra(SeleccionarGrupoEjerciciosActivity.EXTRA_TABLA_PONERDATOS);
       System.out.println("-----------------------" + dia);

       if(tabla != null){
           txt_nombreTabla_ponerDatos.setText(tabla.getNombre());
       }
        List<TablaEjercicioRelacion> ejercicioRelacion  = tr.tablaPorIdTabla(200);
        tablaEjercicio = ejercicioRelacion.stream().filter(e -> e.getDia() == dia).collect(Collectors.toList());

        ejercicioLocals = ejercicioViewModel.allEjercicios();


        listaEjercicioPonerInfoAdapter = new ListaEjercicioPonerInfoAdapter(this,tablaEjercicio,ejercicioLocals);
        rv_verEjerciciosParaDatos.setAdapter(listaEjercicioPonerInfoAdapter);
        rv_verEjerciciosParaDatos.setLayoutManager(new LinearLayoutManager(this));

    }
}