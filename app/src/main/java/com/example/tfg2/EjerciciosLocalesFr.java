package com.example.tfg2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.ImageButton;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicioRelacionViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;
import com.example.tfg2.utilidades.SpacingItemDecorator;

import java.util.List;
import java.util.stream.Collectors;



public class EjerciciosLocalesFr extends Fragment {
    private View vista;
    private ImageButton btn_anadirEjercicio_ejerciciosLocalesFr;
    private List<EjercicioLocal> ejercicioLocales;
    private RecyclerView rv_verEjerciciosLocales_ejerciciosLocalesFr;
    private ListaEjerciciosLocalesAdapter listaEjerciciosLocalesAdapter;
    private LiveData<List<EjercicioLocal>> ejerciciosLive;
    private TablaEjercicioRelacionViewModel tablaEjercicioRelacionViewModel;

    private EjercicioViewModel ejercicioViewModel;

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
        btn_anadirEjercicio_ejerciciosLocalesFr.setBackgroundColor(Color.parseColor("#00FFFFFF"));


        ejerciciosLive  = ejercicioViewModel.obtenerEjercicios();

        Context context = vista.getContext();
        listaEjerciciosLocalesAdapter = new ListaEjerciciosLocalesAdapter(context);
        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
        rv_verEjerciciosLocales_ejerciciosLocalesFr.addItemDecoration(spacingItemDecorator);
        rv_verEjerciciosLocales_ejerciciosLocalesFr.setAdapter(listaEjerciciosLocalesAdapter);
        rv_verEjerciciosLocales_ejerciciosLocalesFr.setLayoutManager(new LinearLayoutManager(context));

        if(ejerciciosLive != null){
            actualizarEjerciciosLocales();
        }


        btn_anadirEjercicio_ejerciciosLocalesFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vista.getContext(),CrearEjercicio.class);
                startActivityForResult(intent,PETICION3);
            }
        });
        funcionArrastrar();
        return vista;

    }

    private void actualizarEjerciciosLocales() {
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

    private void funcionArrastrar(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                listaEjerciciosLocalesAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.RIGHT)
                {
                    mostrarAlerta(viewHolder,"??Quieres borrar este ejercicio de tu movil?",2);
                }
            }
        });
        helper.attachToRecyclerView(rv_verEjerciciosLocales_ejerciciosLocalesFr);
    }

    private void mostrarAlerta(RecyclerView.ViewHolder viewHolder, String mensaje,int i) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(vista.getContext());
        tablaEjercicioRelacionViewModel = ViewModelProviders.of(this).get(TablaEjercicioRelacionViewModel.class);
        EjercicioLocal ejercicioLocal = ejercicioLocales.get(viewHolder.getAdapterPosition());
        alerta.setTitle("Aviso");
        alerta.setMessage(mensaje + "\n" + "  " + ejercicioLocal.getNombre() );
        alerta.setPositiveButton("S??", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(i == 1){
                    System.out.println("ACCION MOVER DERECHA");
                }else if(i == 2){

                    System.out.println("ACCION MOVER IZQ");
                    if(tablaEjercicioRelacionViewModel.comprobarEjercicioEnUso(ejercicioLocal.getIdEjercicio())){
                        AlertDialog.Builder alerta2 = new AlertDialog.Builder(vista.getContext());
                        alerta2.setTitle("Error al borrar");
                        alerta2.setMessage("Tabla en uso, desactivala para borrar");
                        alerta.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta2.show();
                    }else{
                        if(ejercicioViewModel.borrarEjercicioLocal(ejercicioLocal.getIdEjercicio())){
                            AlertDialog.Builder alerta2 = new AlertDialog.Builder(vista.getContext());
                            alerta.setTitle("Aviso");
                            alerta2.setMessage("Borrado correctamente");
                            alerta.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alerta2.show();
                        }else {
                            System.out.println("ERROR AL BORRAR");
                        }
                    }
                    actualizarEjerciciosLocales();
                }

            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                actualizarEjerciciosLocales();
            }
        });
        alerta.show();
    }
}