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
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjerciciosLocalesFr#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjerciciosLocalesFr extends Fragment {
    private View vista;
    private Button btn_anadirEjercicio_ejerciciosLocalesFr;
    private List<EjercicioLocal> ejercicioLocales;
    private RecyclerView rv_verEjerciciosLocales_ejerciciosLocalesFr;
    ListaEjerciciosLocalesAdapter listaEjerciciosLocalesAdapter;

    EjercicioViewModel ejercicioViewModel;

    private static final int PETICION3 = 1;
    public EjerciciosLocalesFr() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_ejercicios_locales, container, false);
        btn_anadirEjercicio_ejerciciosLocalesFr = vista.findViewById(R.id.btn_anadirEjercicio_ejerciciosLocalesFr);
        rv_verEjerciciosLocales_ejerciciosLocalesFr = vista.findViewById(R.id.rv_verEjerciciosLocales_ejerciciosLocalesFr);

        ejercicioViewModel = ViewModelProviders.of(this).get(EjercicioViewModel.class);
        LiveData<List<EjercicioLocal>> ejerciciosLive  = ejercicioViewModel.obtenerEjercicios();

        Context context = vista.getContext();
        listaEjerciciosLocalesAdapter = new ListaEjerciciosLocalesAdapter(context);
        rv_verEjerciciosLocales_ejerciciosLocalesFr.setAdapter(listaEjerciciosLocalesAdapter);
        rv_verEjerciciosLocales_ejerciciosLocalesFr.setLayoutManager(new LinearLayoutManager(context));

        if(ejerciciosLive != null){
            ejerciciosLive.observe(this, new Observer<List<EjercicioLocal>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onChanged(List<EjercicioLocal> ejercicioLocals) {
                    ejercicioLocales = ejercicioLocals;
                    ejercicioLocales = ejercicioLocales.stream().filter(e-> e.getCreated()).collect(Collectors.toList());
                    listaEjerciciosLocalesAdapter.setListaEjerciciosLocales(ejercicioLocales);
                }
            });
        }


        btn_anadirEjercicio_ejerciciosLocalesFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vista.getContext(),CrearEjercicio.class);
                startActivityForResult(intent,PETICION3);
            }
        });

        return vista;

    }
}