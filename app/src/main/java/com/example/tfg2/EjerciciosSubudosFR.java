package com.example.tfg2;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.ejercicios.modelos.EjercicioDB;
import com.example.tfg2.user.clases.CurrentUser;

import java.util.ArrayList;
import java.util.List;

public class EjerciciosSubudosFR extends Fragment {

    private View vista;

    private RecyclerView rv_verEjerciciosSubidos_ejerciciosSubidosFr;
   private ListaEjerciciosAdapter eAdapter;
    private Context context;

    private static final int PETICION4 = 1;
    public EjerciciosSubudosFR() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_ejercicios_subudos_f_r, container, false);
        context = vista.getContext();
        rv_verEjerciciosSubidos_ejerciciosSubidosFr = vista.findViewById(R.id.rv_verEjerciciosSubidos_ejerciciosSubidosFr);
        añadirEjerciciosTabla();
        return vista;
    }


    private void añadirEjerciciosTabla(){
        ArrayList<Ejercicio> ejerciciosSubidos = EjercicioController.obtenerEjerciciosUsuario(1);
        eAdapter = new ListaEjerciciosAdapter(context,ejerciciosSubidos);
        rv_verEjerciciosSubidos_ejerciciosSubidosFr.setAdapter(eAdapter);
        rv_verEjerciciosSubidos_ejerciciosSubidosFr.setLayoutManager(new LinearLayoutManager(context));

    }
}