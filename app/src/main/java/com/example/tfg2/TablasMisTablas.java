package com.example.tfg2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicioRelacionViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;
import com.example.tfg2.utilidades.SpacingItemDecorator;

import java.util.List;
import java.util.stream.Collectors;

public class TablasMisTablas extends Fragment {
    private ImageButton btn_anadirTabla_MisTablas;
    private View vista;
    private RecyclerView rv_verMisTablas_TablasMisTablas;
    private TablaViewModel tablaViewModel;
    private ListaTablaLocalAdapter listaTablaLocalAdapter;
    private List<TablaLocal> tablaLocales;
    private static final int PETICION1 = 1;
    private TablaEjercicioRelacionViewModel tr;
    private LiveData<List<TablaLocal>> tablasLive;

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

        tr =  ViewModelProviders.of(this).get(TablaEjercicioRelacionViewModel.class);
        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);
         tablasLive  = tablaViewModel.obtenerTablas();

        Context context = vista.getContext();
        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
        rv_verMisTablas_TablasMisTablas.addItemDecoration(spacingItemDecorator);
        listaTablaLocalAdapter = new ListaTablaLocalAdapter(context,tablasLive);
        rv_verMisTablas_TablasMisTablas.setAdapter(listaTablaLocalAdapter);
        rv_verMisTablas_TablasMisTablas.setLayoutManager(new LinearLayoutManager(context));

        actualizarListaTablas();
        funcionArrastrar();

        btn_anadirTabla_MisTablas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vista.getContext(),CrearTablaActivity.class);
                startActivityForResult(intent,PETICION1);
            }
        });

        return vista;

    }

    private void actualizarListaTablas(){
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
    private void funcionArrastrar(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback( ItemTouchHelper.RIGHT, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                listaTablaLocalAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.RIGHT)
                {
                    mostrarAlerta(viewHolder,"Borrar tabla",2);
                }
            }
        });
        helper.attachToRecyclerView(rv_verMisTablas_TablasMisTablas);
    }
    private void mostrarAlerta(RecyclerView.ViewHolder viewHolder, String mensaje,int i) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(vista.getContext());
        TablaLocal tablaLocal = tablaLocales.get(viewHolder.getAdapterPosition());
        alerta.setTitle(mensaje);
        alerta.setMessage("¿Quieres borrar esta tabla? \n" + tablaLocal.getNombre());
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(i == 1){
                }else if(i == 2){

                    if(tablaLocal.getActive()){
                        AlertDialog.Builder alerta2 = new AlertDialog.Builder(vista.getContext());
                        alerta2.setTitle("Aviso");
                        alerta2.setMessage("Tabla activa, no se puede borrar");
                        alerta2.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }) ;
                        alerta2.show();
                    }else{
                        tr.borrarDatosTabla(tablaLocal.getIdTabla());
                        tablaViewModel.borrarTabla(tablaLocal);
                    }
                    actualizarListaTablas();
                }

            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                actualizarListaTablas();
            }
        });
        alerta.show();
    }
}