package com.example.tfg2.tabla.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.VerTablaActivity;
import com.example.tfg2.tabla.adapter.ListaTablaAdapter;

public class TablaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public static final String TABLADESCARGADA_A_CREARTABLAACTIVITY = "chema.martinez/tablaOnlineVer";
    private ListaTablaAdapter listaTablaAdapter;
    public TextView txt_nombreTabla_itemTablaLocal;
    public TextView txt_diasTablas_itemTablaEntrenar;

    public TablaViewHolder(@NonNull View itemView,ListaTablaAdapter listaTablaAdapter) {
        super(itemView);
        txt_nombreTabla_itemTablaLocal = itemView.findViewById(R.id.txt_diaEjercicios_elegirTabla);
        txt_diasTablas_itemTablaEntrenar = itemView.findViewById(R.id.txt_diasTablas_itemTablaEntrenar);
        this.listaTablaAdapter = listaTablaAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int mPosition = getLayoutPosition();
        Intent intent = new Intent(listaTablaAdapter.getC(), VerTablaActivity.class);
        intent.putExtra(TABLADESCARGADA_A_CREARTABLAACTIVITY,listaTablaAdapter.getTablas().get(mPosition));
        listaTablaAdapter.getC().startActivity(intent);
    }
}
