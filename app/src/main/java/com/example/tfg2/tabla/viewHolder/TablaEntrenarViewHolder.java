package com.example.tfg2.tabla.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.SeleccionarGrupoEjerciciosActivity;
import com.example.tfg2.tabla.adapter.ListaTablaEntrenarAdapter;

public class TablaEntrenarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TABLA_A_VERGRUPOEJERCICIO = "chema.martinez/tablaParaEtrenar";
    public TextView txt_nombreTabla_itemTablaEntrenar3;
    public TextView txt_diaEjercicios_elegirTabla;
    ListaTablaEntrenarAdapter listaTablaEntrenarAdapter;
    public TablaEntrenarViewHolder(@NonNull View itemView, ListaTablaEntrenarAdapter listaTablaEntrenarAdapter) {
        super(itemView);
        this.listaTablaEntrenarAdapter = listaTablaEntrenarAdapter;
        txt_nombreTabla_itemTablaEntrenar3 = (TextView) itemView.findViewById(R.id.txt_nombreTabla_itemTablaEntrenar3);
        txt_diaEjercicios_elegirTabla = (TextView) itemView.findViewById(R.id.txt_diaEjercicios_elegirTabla);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Intent intent = new Intent(listaTablaEntrenarAdapter.getC(), SeleccionarGrupoEjerciciosActivity.class);
        intent.putExtra(TABLA_A_VERGRUPOEJERCICIO,listaTablaEntrenarAdapter.getTablaLocals().get(mPosition));
        listaTablaEntrenarAdapter.getC().startActivity(intent);
    }
}
