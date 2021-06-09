package com.example.tfg2.tabla.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.tabla.adapter.ListaTablaEntrenarAdapter;

import org.w3c.dom.Text;

public class TablaEntrenarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_nombreTabla_itemTablaEntrenar;
    public TablaEntrenarViewHolder(@NonNull View itemView, ListaTablaEntrenarAdapter listaTablaEntrenarAdapter) {
        super(itemView);
        txt_nombreTabla_itemTablaEntrenar = (TextView) itemView.findViewById(R.id.txt_nombreTabla_itemTablaEntrenar);
    }

    @Override
    public void onClick(View v) {

    }
}
