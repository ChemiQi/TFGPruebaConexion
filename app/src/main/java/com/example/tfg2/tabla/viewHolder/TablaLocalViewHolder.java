package com.example.tfg2.tabla.viewHolder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.PopUpActivarTabla;
import com.example.tfg2.R;
import com.example.tfg2.VerTablaActivity;
import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;

public class TablaLocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TABLA_A_CREARTABLAACTIVITY = "chema.martinez/tablaACrearTablaActivity";
    public static final String TABLA_A_ACTIVAR ="chema.martinez/tablaAActivar" ;
    ListaTablaLocalAdapter listaTablaLocalAdapter;
    public ImageButton btn_imagen;

    private View viewLocal;
    private TablaViewModel tablaViewModel;

    public TextView txt_nombreTabla_itemTablaLocal;
    @SuppressLint("WrongViewCast")
    public TablaLocalViewHolder(@NonNull View itemView, ListaTablaLocalAdapter listaTablaLocalAdapter) {
        super(itemView);
        txt_nombreTabla_itemTablaLocal = (TextView) itemView.findViewById(R.id.txt_nombreTabla_itemTablaEntrenar);
        btn_imagen = (ImageButton) itemView.findViewById(R.id.btn_imagen);
        btn_imagen.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        viewLocal = itemView;
        this.listaTablaLocalAdapter = listaTablaLocalAdapter;
        itemView.setOnClickListener(this);


        btn_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TablaLocal tablaLocal = listaTablaLocalAdapter.getTablaLocals().get(getLayoutPosition());
                    Intent intent2 = new Intent(listaTablaLocalAdapter.getC(), PopUpActivarTabla.class);
                    intent2.putExtra(TABLA_A_ACTIVAR,tablaLocal);
                    listaTablaLocalAdapter.getC().startActivity(intent2);
            }
        }); // FIN CLICK LISTENER

    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Intent intent = new Intent(listaTablaLocalAdapter.getC(), VerTablaActivity.class);
        intent.putExtra(TABLA_A_CREARTABLAACTIVITY,listaTablaLocalAdapter.getTablaLocals().get(mPosition));
        listaTablaLocalAdapter.getC().startActivity(intent);
    }
}
