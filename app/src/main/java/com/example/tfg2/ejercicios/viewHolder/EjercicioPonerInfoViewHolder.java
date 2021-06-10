package com.example.tfg2.ejercicios.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.ejercicios.adapter.ListaEjercicioPonerInfoAdapter;

public class EjercicioPonerInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView img_ejercicio_itemEjercicioInfo;
    public TextView txt_nuRep_itemTablaEjercicioInfo;
    public TextView txt_nuRep_itemTablaEjercicioInfo2;
    public TextView txt_nombreEJercicio_itemEjercicioInfo;
    public EjercicioPonerInfoViewHolder(@NonNull View itemView, ListaEjercicioPonerInfoAdapter listaEjercicioPonerInfoAdapter) {
        super(itemView);
        img_ejercicio_itemEjercicioInfo =(ImageView) itemView.findViewById(R.id.img_ejercicio_itemEjercicioInfo);
        txt_nuRep_itemTablaEjercicioInfo =(TextView) itemView.findViewById(R.id.txt_nuRep_itemTablaEjercicioInfo);
        txt_nuRep_itemTablaEjercicioInfo2 = (TextView) itemView.findViewById(R.id.txt_nuRep_itemTablaEjercicioInfo2);
        txt_nombreEJercicio_itemEjercicioInfo =(TextView)  itemView.findViewById(R.id.txt_nombreEJercicio_itemEjercicioInfo);
    }

    @Override
    public void onClick(View v) {
        System.out.println("PRUEBA 2");
    }
}
