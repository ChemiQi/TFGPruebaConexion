package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Build;
import android.os.Bundle;


import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.tabla.adapter.ListaTablaEntrenarAdapter;
import com.example.tfg2.utilidades.SpacingItemDecorator;


import java.util.List;
import java.util.stream.Collectors;


public class EntrenarActivity extends AppCompatActivity {

    RecyclerView rv_verTablasActivas_aEntrenar;
    TablaViewModel tablaViewModel;
    ListaTablaEntrenarAdapter listaTablaEntrenarAdapter;
    private List<TablaLocal> tablaLocales;
    LiveData<List<TablaLocal>> tablasLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrenar);
        rv_verTablasActivas_aEntrenar = (RecyclerView)findViewById(R.id.rv_verTablasActivas_aEntrenara);


        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);
        tablasLive  = tablaViewModel.obtenerTablas();

        listaTablaEntrenarAdapter = new ListaTablaEntrenarAdapter(this,tablasLive);
        rv_verTablasActivas_aEntrenar.setAdapter(listaTablaEntrenarAdapter);
        rv_verTablasActivas_aEntrenar.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
        rv_verTablasActivas_aEntrenar.addItemDecoration(spacingItemDecorator);
        if(tablasLive != null){
            tablasLive.observe(this, new Observer<List<TablaLocal>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onChanged(List<TablaLocal> tablaLocals) {
                    tablaLocals = tablaLocals.stream().filter(e-> e.getActive()).collect(Collectors.toList());
                    listaTablaEntrenarAdapter.setListaTablasLocales(tablaLocals);
                }
            });
        }


    }
}