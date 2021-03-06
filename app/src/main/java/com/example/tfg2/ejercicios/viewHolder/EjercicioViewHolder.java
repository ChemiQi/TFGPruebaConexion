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
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

public class EjercicioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String EXTRA_OBJETO_EJERCICIO = "";
    public static final String EXTRA_IMAGEN_EJERCICIO = "chema.martinez/imagenEjercicio";
    public TextView txt_nombre_itemEjercicio;
    public TextView txt_musculo_itemEjercicio;
    public ImageView img_ejercicio_rv_Ejercicio;
    public ListaEjerciciosAdapter eAdapter;

    public EjercicioViewHolder(@NonNull View mItemView, ListaEjerciciosAdapter listaEjerciciosAdapter) {
        super(mItemView);
        txt_nombre_itemEjercicio = (TextView) mItemView.findViewById(R.id.txt_nombre_itemEjercicioLocal);
        txt_musculo_itemEjercicio = (TextView) mItemView.findViewById(R.id.txt_musculo_itemEjercicioLocal);
        this.eAdapter = listaEjerciciosAdapter;
        img_ejercicio_rv_Ejercicio = (ImageView) mItemView.findViewById(R.id.img_ejercicio_rv_EjercicioLocal);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Ejercicio ejercicio =eAdapter.getListaEjercicios().get(mPosition);
        byte [] imagen;

        Intent intent = new Intent(eAdapter.getC(), PopUpAnadirEjercicio.class);
        Bitmap imagenGuardada = ejercicio.getImageMusculo();
        if(ejercicio.getImageMusculo() != null) {
            imagen = transformarImagen(imagenGuardada);
            ejercicio.setImageMusculo(null);
            intent.putExtra(EXTRA_IMAGEN_EJERCICIO,imagen);
        }
        intent.putExtra(EXTRA_OBJETO_EJERCICIO,ejercicio);
        eAdapter.getC().startActivity(intent);
        ejercicio.setImageMusculo(imagenGuardada);
    }

    private byte[] transformarImagen(Bitmap bmFoto){
        return ImagenesBlobBitmap.bitmap_to_bytes(bmFoto);
    }
}
