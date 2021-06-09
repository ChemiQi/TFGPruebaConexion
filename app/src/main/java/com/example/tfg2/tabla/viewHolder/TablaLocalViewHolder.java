package com.example.tfg2.tabla.viewHolder;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.CrearTablaActivity;
import com.example.tfg2.PopUpActivarTabla;
import com.example.tfg2.R;
import com.example.tfg2.TablasTablasLocales;
import com.example.tfg2.VerTablaActivity;
import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.tabla.adapter.ListaTablaLocalAdapter;

public class TablaLocalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TABLA_A_CREARTABLAACTIVITY = "chema.martinez/tablaACrearTablaActivity";
    public static final String TABLA_A_ACTIVAR ="chema.martinez/tablaAActivar" ;
    ListaTablaLocalAdapter listaTablaLocalAdapter;
    public Button item_btn_activarTablaActiva_tablaLocal;

    private View viewLocal;
    private TablaViewModel tablaViewModel;

    public TextView txt_nombreTabla_itemTablaLocal;
    public TablaLocalViewHolder(@NonNull View itemView, ListaTablaLocalAdapter listaTablaLocalAdapter) {
        super(itemView);
        txt_nombreTabla_itemTablaLocal = (TextView) itemView.findViewById(R.id.txt_nombreTabla_itemTablaLocal);
        item_btn_activarTablaActiva_tablaLocal = (Button) itemView.findViewById(R.id.item_btn_activarTablaActiva_tablaLocal);
        viewLocal = itemView;
        this.listaTablaLocalAdapter = listaTablaLocalAdapter;
        itemView.setOnClickListener(this);


        item_btn_activarTablaActiva_tablaLocal.setOnClickListener(new View.OnClickListener() {
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
