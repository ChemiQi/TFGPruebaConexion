package com.example.tfg2.tabla.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;

public class TablaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_nombreTabla_itemTablaLocal;

    public TablaViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_nombreTabla_itemTablaLocal = itemView.findViewById(R.id.txt_nombreTabla_itemTablaLocal);
    }

    @Override
    public void onClick(View v) {

    }
}
