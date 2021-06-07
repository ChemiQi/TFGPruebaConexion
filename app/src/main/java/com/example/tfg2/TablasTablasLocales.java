package com.example.tfg2;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

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

import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;

import java.util.List;
import java.util.stream.Collectors;


public class TablasTablasLocales extends Fragment {
    View vista;
    RecyclerView rv_verTablasDescargadas_TablasLocales;
    TablaViewModel tablaViewModel;
    ListaTablaLocalAdapter listaTablaLocalAdapter;
    private List<TablaLocal> tablaLocales;
    Context context;

    public TablasTablasLocales() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista =  inflater.inflate(R.layout.fragment_third, container, false);
        context = vista.getContext();
        rv_verTablasDescargadas_TablasLocales = vista.findViewById(R.id.rv_verTablasDescargadas_TablasLocales);

        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);
        LiveData<List<TablaLocal>> tablasLive  = tablaViewModel.obtenerTablas();

        Context context = vista.getContext();
        listaTablaLocalAdapter = new ListaTablaLocalAdapter(context,tablasLive);
        rv_verTablasDescargadas_TablasLocales.setAdapter(listaTablaLocalAdapter);
        rv_verTablasDescargadas_TablasLocales.setLayoutManager(new LinearLayoutManager(context));

        if(tablasLive != null){
            tablasLive.observe(this, new Observer<List<TablaLocal>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onChanged(List<TablaLocal> tablaLocals) {
                    tablaLocales = tablaLocals;
                    tablaLocales = tablaLocales.stream().filter(e-> !e.getCreated()).collect(Collectors.toList());
                    listaTablaLocalAdapter.setListaTablasLocales(tablaLocales);
                }
            });
        }

        return vista;
    }
}