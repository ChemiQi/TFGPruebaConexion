package com.example.tfg2.ejercicios.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.PopUpAnadirEjercicio;
import com.example.tfg2.R;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.FotoEjercicio;

public class EjercicioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String EXTRA_OBJETO_EJERCICIO = "";
    public TextView txt_nombre_itemEjercicio;
    public TextView txt_musculo_itemEjercicio;
    public ImageView img_ejercicio_rv_Ejercicio;
    public ListaEjerciciosAdapter eAdapter;

    public EjercicioViewHolder(@NonNull View mItemView, ListaEjerciciosAdapter listaEjerciciosAdapter) {
        super(mItemView);
        txt_nombre_itemEjercicio = (TextView) mItemView.findViewById(R.id.txt_nombre_itemEjercicio);
        txt_musculo_itemEjercicio = (TextView) mItemView.findViewById(R.id.txt_musculo_itemEjercicio);
        this.eAdapter = listaEjerciciosAdapter;
        //img_ejercicio_rv_Ejercicio = (ImageView) mItemView.findViewById(R.id.img_ejercicio_rv_Ejercicio);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Ejercicio ejercicio =eAdapter.getListaEjercicios().get(mPosition);


        Intent intent = new Intent(eAdapter.getC(), PopUpAnadirEjercicio.class);
        intent.putExtra(EXTRA_OBJETO_EJERCICIO,ejercicio);
        eAdapter.getC().startActivity(intent);
    }
}
