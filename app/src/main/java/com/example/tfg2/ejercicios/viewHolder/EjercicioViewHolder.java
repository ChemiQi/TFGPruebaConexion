package com.example.tfg2.ejercicios.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.FotoEjercicio;

public class EjercicioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_nombre_itemEjercicio;
    public TextView txt_musculo_itemEjercicio;
    public ImageView img_ejercicio_rv_Ejercicio;
    public ListaEjerciciosAdapter eAdapter;

    public EjercicioViewHolder(@NonNull View mItemView, ListaEjerciciosAdapter listaEjerciciosAdapter) {
        super(mItemView);
        txt_nombre_itemEjercicio = (TextView) mItemView.findViewById(R.id.txt_nombre_itemEjercicio);
        txt_musculo_itemEjercicio = (TextView) mItemView.findViewById(R.id.txt_musculo_itemEjercicio);
        //img_ejercicio_rv_Ejercicio = (ImageView) mItemView.findViewById(R.id.img_ejercicio_rv_Ejercicio);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Ejercicio ejercicio =eAdapter.getListaEjercicios().get(mPosition);
        System.out.println(ejercicio.getIdEjercicio() + " " + ejercicio.getNombreEjercicio());
        FotoEjercicio fEjercicio = eAdapter.getListaFotosEjercicio().get(mPosition);
        System.out.println(fEjercicio.getIdLiga());


    }
}
