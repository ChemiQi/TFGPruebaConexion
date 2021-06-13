package com.example.tfg2;

import android.content.Context;
import android.content.DialogInterface;
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

import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicioRelacionViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;
import com.example.tfg2.utilidades.SpacingItemDecorator;

import java.util.List;
import java.util.stream.Collectors;


public class TablasTablasLocales extends Fragment {
    View vista;
    RecyclerView rv_verTablasDescargadas_TablasLocales;
    TablaViewModel tablaViewModel;
    ListaTablaLocalAdapter listaTablaLocalAdapter;
    private List<TablaLocal> tablaLocales;
    private TablaEjercicioRelacionViewModel tr;
    Context context;
    private LiveData<List<TablaLocal>> tablasLive;

    public TablasTablasLocales() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista =  inflater.inflate(R.layout.fragment_third, container, false);
        context = vista.getContext();
        rv_verTablasDescargadas_TablasLocales = vista.findViewById(R.id.rv_verTablasDescargadas_TablasLocales);
        tr =  ViewModelProviders.of(this).get(TablaEjercicioRelacionViewModel.class);
        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);
        tablasLive  = tablaViewModel.obtenerTablas();
        Context context = vista.getContext();
        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
        rv_verTablasDescargadas_TablasLocales.addItemDecoration(spacingItemDecorator);
        listaTablaLocalAdapter = new ListaTablaLocalAdapter(context,tablasLive);
        rv_verTablasDescargadas_TablasLocales.setAdapter(listaTablaLocalAdapter);
        rv_verTablasDescargadas_TablasLocales.setLayoutManager(new LinearLayoutManager(context));

        actualizarListaTablas();
        funcionArrastrar();
        return vista;
    }


    private void actualizarListaTablas(){
         tablasLive  = tablaViewModel.obtenerTablas();
        if(tablasLive != null) {
            tablasLive.observe(this, new Observer<List<TablaLocal>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onChanged(List<TablaLocal> tablaLocals) {
                    tablaLocales = tablaLocals;
                    tablaLocales = tablaLocales.stream().filter(e -> !e.getCreated()).collect(Collectors.toList());
                    listaTablaLocalAdapter.setListaTablasLocales(tablaLocales);
                }
            });
        }
    }
    private void funcionArrastrar(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback( ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
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
        helper.attachToRecyclerView(rv_verTablasDescargadas_TablasLocales);
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

            }
        });
        alerta.show();
    }

}