package com.example.tfg2.ejercicios.viewHolder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.PonerDatosPorEjercicioActivity;
import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.ejercicios.adapter.ListaEjercicioPonerInfoAdapter;

import java.util.Optional;

public class EjercicioPonerInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public static final String EXTRA_TABLAEJERCICIORELACION_PARAPONERDATOS = "chema.martinez/larelacion";
    public static final String EXTRA_EJERCICIO_PARAEJERCICIOINFO = "chema.martinez/ejerciicoparaejericioinfo";
    public ImageView img_ejercicio_itemEjercicioInfo;
    public TextView txt_nuRep_itemTablaEjercicioInfo;
    public TextView txt_nuRep_itemTablaEjercicioInfo2;
    public TextView txt_nombreEJercicio_itemEjercicioInfo;
    ListaEjercicioPonerInfoAdapter listaEjercicioPonerInfoAdapter;
    public EjercicioPonerInfoViewHolder(@NonNull View itemView, ListaEjercicioPonerInfoAdapter listaEjercicioPonerInfoAdapter) {
        super(itemView);
        img_ejercicio_itemEjercicioInfo =(ImageView) itemView.findViewById(R.id.img_ejercicio_itemEjercicioInfo);
        txt_nuRep_itemTablaEjercicioInfo =(TextView) itemView.findViewById(R.id.txt_nuRep_itemTablaEjercicioInfo);
        txt_nuRep_itemTablaEjercicioInfo2 = (TextView) itemView.findViewById(R.id.txt_nuRep_itemTablaEjercicioInfo2);
        txt_nombreEJercicio_itemEjercicioInfo =(TextView)  itemView.findViewById(R.id.txt_nombreEJercicio_itemEjercicioInfo);
        this.listaEjercicioPonerInfoAdapter = listaEjercicioPonerInfoAdapter;
        itemView.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        TablaEjercicioRelacion tablaEjercicioRelacion = listaEjercicioPonerInfoAdapter.getListaTablaEjercicioRelacionInfo().get(mPosition);
        EjercicioLocal elegido = null;
        Optional<EjercicioLocal> e = listaEjercicioPonerInfoAdapter.getEjercicioLocals().stream().filter(e2 -> e2.getIdEjercicio() == tablaEjercicioRelacion.getIdEjercicio()).findFirst();
        if(e.isPresent()) {
            elegido = e.get();

        }
        Intent intent = new Intent(listaEjercicioPonerInfoAdapter.getC(), PonerDatosPorEjercicioActivity.class);
        intent.putExtra(EXTRA_TABLAEJERCICIORELACION_PARAPONERDATOS,tablaEjercicioRelacion);

        if(elegido != null) {
            intent.putExtra(EXTRA_EJERCICIO_PARAEJERCICIOINFO, elegido);

        }
        listaEjercicioPonerInfoAdapter.getC().startActivity(intent);

    }
}
