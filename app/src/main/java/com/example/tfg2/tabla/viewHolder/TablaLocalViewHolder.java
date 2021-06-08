package com.example.tfg2.tabla.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.CrearTablaActivity;
import com.example.tfg2.R;
import com.example.tfg2.VerTablaActivity;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;

public class TablaLocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TABLA_A_CREARTABLAACTIVITY = "chema.martinez/tablaACrearTablaActivity";
    ListaTablaLocalAdapter listaTablaLocalAdapter;

    public TextView txt_nombreTabla_itemTablaLocal;
    public TablaLocalViewHolder(@NonNull View itemView, ListaTablaLocalAdapter listaTablaLocalAdapter) {
        super(itemView);
        txt_nombreTabla_itemTablaLocal = (TextView) itemView.findViewById(R.id.txt_nombreTabla_itemTablaLocal);

        this.listaTablaLocalAdapter = listaTablaLocalAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Intent intent = new Intent(listaTablaLocalAdapter.getC(), VerTablaActivity.class);
        intent.putExtra(TABLA_A_CREARTABLAACTIVITY,listaTablaLocalAdapter.getTablaLocals().get(mPosition));
        listaTablaLocalAdapter.getC().startActivity(intent);
    }
}
