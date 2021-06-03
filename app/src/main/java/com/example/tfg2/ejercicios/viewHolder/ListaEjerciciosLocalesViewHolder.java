package com.example.tfg2.ejercicios.viewHolder;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.EjerciciosLocalesFr;
import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.controladores.EjercicioController;

public class ListaEjerciciosLocalesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_nombre_itemEjercicioLocal;
    public TextView txt_musculo_itemEjercicioLocal;
    public ImageView img_ejercicio_rv_EjercicioLocal;
    public ListaEjerciciosLocalesAdapter eAdapter;
    public Button btn_subirArchivos_itmeEjerciciosLocales;

    View viewLocal;

    public ListaEjerciciosLocalesViewHolder(@NonNull View mItemView, ListaEjerciciosLocalesAdapter listaEjerciciosAdapter) {
        super(mItemView);
        txt_nombre_itemEjercicioLocal = (TextView) mItemView.findViewById(R.id.txt_nombre_itemEjercicioLocal);
        viewLocal = mItemView;
        txt_musculo_itemEjercicioLocal = (TextView) mItemView.findViewById(R.id.txt_musculo_itemEjercicioLocal);
        this.eAdapter = listaEjerciciosAdapter;
        img_ejercicio_rv_EjercicioLocal = (ImageView) mItemView.findViewById(R.id.img_ejercicio_rv_EjercicioLocal);
        btn_subirArchivos_itmeEjerciciosLocales = (Button) mItemView.findViewById(R.id.btn_subirArchivos_itmeEjerciciosLocales);
        itemView.setOnClickListener(this);

        btn_subirArchivos_itmeEjerciciosLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(viewLocal.getContext());
                EjercicioLocal ejercicioLocal2 = listaEjerciciosAdapter.getListaEjerciciosLocales().get(getLayoutPosition());
                alerta.setTitle("¿Quíeres añadir este ejercicio? \n" +  ejercicioLocal2.getNombre());
                alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EjercicioController.addEjercicioUser(ejercicioLocal2);
                    }
                });
                alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alerta.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        EjercicioLocal ejercicio =eAdapter.getListaEjerciciosLocales().get(mPosition);

        System.out.println(ejercicio.getNombre());

    }

}
