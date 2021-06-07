package com.example.tfg2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.tabla.adapter.ListaTablaAdapter;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.controladores.TablaController;

import java.util.List;


public class TablasTablasTFG extends Fragment {
    private View vista;
    private ListaTablaAdapter listaTablaAdapter;
    private List<Tabla> tablasTFG;
    private RecyclerView rv_verTablasTFG_TablasTFG;
    Context context;
    public TablasTablasTFG() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista =  inflater.inflate(R.layout.fragment_second, container, false);
        rv_verTablasTFG_TablasTFG = vista.findViewById(R.id.rv_verTablasTFG_TablasTFG);
        context = vista.getContext();


        tablasTFG = TablaController.getTablasTFG();
        añadirTablasTFG();
        return vista;
    }

    private void añadirTablasTFG(){
        listaTablaAdapter = new ListaTablaAdapter(context,tablasTFG);
        rv_verTablasTFG_TablasTFG.setAdapter(listaTablaAdapter);
        rv_verTablasTFG_TablasTFG.setLayoutManager(new LinearLayoutManager(context));

    }
}