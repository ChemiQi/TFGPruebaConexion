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
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicioRelacionViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.ejercicios.modelos.EjercicioDB;
import com.example.tfg2.user.clases.CurrentUser;
import com.example.tfg2.utilidades.SpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class EjerciciosSubudosFR extends Fragment {

    private View vista;

    private RecyclerView rv_verEjerciciosSubidos_ejerciciosSubidosFr;
   private ListaEjerciciosAdapter eAdapter;
   private ImageButton btn_actualizar_ejecicioSubidosFr2;
    private Context context;
    ArrayList<Ejercicio> ejerciciosSubidos;

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
        btn_actualizar_ejecicioSubidosFr2 =(ImageButton) vista.findViewById(R.id.btn_actualizar_ejecicioSubidosFr2);
        btn_actualizar_ejecicioSubidosFr2.setBackgroundColor(Color.parseColor("#00FFFFFF"));

        SpacingItemDecorator spacingItemDecorator = new SpacingItemDecorator(10);
        rv_verEjerciciosSubidos_ejerciciosSubidosFr.addItemDecoration(spacingItemDecorator);
        btn_actualizar_ejecicioSubidosFr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirEjerciciosTabla();
            }
        });
        añadirEjerciciosTabla();
        funcionArrastrar();

        return vista;
    }


    private void añadirEjerciciosTabla(){
            try {
                ejerciciosSubidos = EjercicioController.obtenerEjerciciosUsuario(CurrentUser.getUser().getId());
                for (Ejercicio e : ejerciciosSubidos)
                    eAdapter = new ListaEjerciciosAdapter(context, ejerciciosSubidos);

                rv_verEjerciciosSubidos_ejerciciosSubidosFr.setAdapter(eAdapter);
                rv_verEjerciciosSubidos_ejerciciosSubidosFr.setLayoutManager(new LinearLayoutManager(context));
            }catch (Exception e){

            }



    }

    private void funcionArrastrar(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                eAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.RIGHT)
                {
                    mostrarAlerta(viewHolder,"¿Quieres borrar este ejercicio de tu movil?",2);
                }
            }
        });
        helper.attachToRecyclerView(rv_verEjerciciosSubidos_ejerciciosSubidosFr);
    }

    private void mostrarAlerta(RecyclerView.ViewHolder viewHolder, String mensaje,int i) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(vista.getContext());
        Ejercicio ejercicioLocal = ejerciciosSubidos.get(viewHolder.getAdapterPosition());
        System.out.println(ejercicioLocal.getIdEjercicio() + "------------------------------------------------------");
        alerta.setTitle(mensaje + "\n" +ejercicioLocal.getNombreEjercicio() );
        alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(i == 1){
                    System.out.println("ACCION MOVER DERECHA");
                }else if(i == 2){
                    if(EjercicioController.borrarEjercicioPorId(ejercicioLocal.getIdEjercicio())){
                        System.out.println("MENSAJE BORRADO CORRECTAMENTE");

                    }else{
                        System.out.println("ERROR AL BORRAR");
                    }
                    añadirEjerciciosTabla();
                }
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                añadirEjerciciosTabla();
            }
        });
        alerta.show();
    }
}