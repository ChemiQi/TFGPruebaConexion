package com.example.tfg2.ejercicios.viewHolder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.PopUpAnadirEjercicio;
import com.example.tfg2.R;
import com.example.tfg2.ejercicios.adapter.ListaEjercicoInfoEnTablaAdapter;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.util.ArrayList;

public class EjercicioInfoVIewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public static final String EXTRA_OBJETO_EJERCICIOINFO = "chema.martinez/objetoEjercicioInfo";
    public static final String EXTRA_IMAGEN_EJERCICIO_EJERCICIOINFOHOWLDER = "chema.martinez/imagenEjercicioInfoHolder";

    public TextView txt_repeticiones_itemEjercicioEnTabla;
    public TextView txt_series_itemEjercicioEnTabla;
    public ImageView img_imagenEjercicio_imteEjercicioEnTabla;
    private ListaEjercicoInfoEnTablaAdapter listaEjercicoInfoEnTablaAdapter;


    public EjercicioInfoVIewHolder(@NonNull View etemView, ListaEjercicoInfoEnTablaAdapter listaEjercicoInfoEnTablaAdapter) {
        super(etemView);
        txt_repeticiones_itemEjercicioEnTabla  = (TextView) etemView.findViewById(R.id.txt_repeticiones_itemEjercicioEnTabla);
        txt_series_itemEjercicioEnTabla  = (TextView) etemView.findViewById(R.id.txt_series_itemEjercicioEnTabla);
        img_imagenEjercicio_imteEjercicioEnTabla  = (ImageView) etemView.findViewById(R.id.img_imagenEjercicio_imteEjercicioEnTabla);
        this.listaEjercicoInfoEnTablaAdapter = listaEjercicoInfoEnTablaAdapter;

        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();

        EjercicioInfo ejercicioInfo = listaEjercicoInfoEnTablaAdapter.getListaEjercicioInfo().get(mPosition);
        Intent intent = new Intent(listaEjercicoInfoEnTablaAdapter.getC(), PopUpAnadirEjercicio.class);
        Bitmap imagenGuardada = null;
        try {
            if (ejercicioInfo.getEjercicio().getImageMusculo() != null) {
                imagenGuardada = ejercicioInfo.getEjercicio().getImageMusculo();
                byte[] imagen = transformarImagen(imagenGuardada);
                ejercicioInfo.getEjercicio().setImageMusculo(null);
                intent.putExtra(EXTRA_IMAGEN_EJERCICIO_EJERCICIOINFOHOWLDER, imagen);
            }
        }catch (Exception e)
        {

        }
        intent.putExtra(EXTRA_OBJETO_EJERCICIOINFO,ejercicioInfo);
        listaEjercicoInfoEnTablaAdapter.getC().startActivity(intent);
        if(imagenGuardada != null){
            ejercicioInfo.getEjercicio().setImageMusculo(imagenGuardada);
        }
    }

    private byte[] transformarImagen(Bitmap bmFoto){
        return ImagenesBlobBitmap.bitmap_to_bytes(bmFoto);
    }
}
