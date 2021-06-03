package com.example.tfg2.ejercicios.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;

public class ListaEjerciciosLocalesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_nombre_itemEjercicioLocal;
    public TextView txt_musculo_itemEjercicioLocal;
    public ImageView img_ejercicio_rv_EjercicioLocal;
    public ListaEjerciciosLocalesAdapter eAdapter;
    public Button btn_subirArchivos_itmeEjerciciosLocales;

    public ListaEjerciciosLocalesViewHolder(@NonNull View mItemView, ListaEjerciciosLocalesAdapter listaEjerciciosAdapter) {
        super(mItemView);
        txt_nombre_itemEjercicioLocal = (TextView) mItemView.findViewById(R.id.txt_nombre_itemEjercicioLocal);
        txt_musculo_itemEjercicioLocal = (TextView) mItemView.findViewById(R.id.txt_musculo_itemEjercicioLocal);
        this.eAdapter = listaEjerciciosAdapter;
        img_ejercicio_rv_EjercicioLocal = (ImageView) mItemView.findViewById(R.id.img_ejercicio_rv_EjercicioLocal);
        btn_subirArchivos_itmeEjerciciosLocales = (Button) mItemView.findViewById(R.id.btn_subirArchivos_itmeEjerciciosLocales);
        itemView.setOnClickListener(this);

        btn_subirArchivos_itmeEjerciciosLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("PRUEBA BOTON CLICK");
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
