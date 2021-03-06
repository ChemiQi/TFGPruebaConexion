package com.example.tfg2.ejercicios.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tfg2.R;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;

import com.example.tfg2.ejercicios.viewHolder.EjercicioInfoVIewHolder;
import com.example.tfg2.ejercicios.viewHolder.EjercicioViewHolder;

import java.util.ArrayList;


public class ListaEjercicoInfoEnTablaAdapter extends RecyclerView.Adapter<EjercicioInfoVIewHolder> {

    private Context c;
    private ArrayList<EjercicioInfo> listaEjercicioInfo ;
    private LayoutInflater mInflater;

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<EjercicioInfo> getListaEjercicioInfo() {
        return listaEjercicioInfo;
    }

    public void setListaEjercicioInfo(ArrayList<EjercicioInfo> listaEjercicioInfo) {
        this.listaEjercicioInfo = listaEjercicioInfo;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public ListaEjercicoInfoEnTablaAdapter(Context c, ArrayList<EjercicioInfo> listaEjercicioInfo) {
        this.c = c;
        this.listaEjercicioInfo = listaEjercicioInfo;
        this.mInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public EjercicioInfoVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_ejercicioentabla,parent,false);
        return new EjercicioInfoVIewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioInfoVIewHolder holder, int position) {

        EjercicioInfo ejercicioInfo = listaEjercicioInfo.get(position);
        holder.txt_repeticiones_itemEjercicioEnTabla.setText(String.valueOf(ejercicioInfo.getSeries()));
        holder.txt_series_itemEjercicioEnTabla.setText(String.valueOf(ejercicioInfo.getRepeticiones()));
        try {
            if (ejercicioInfo.getEjercicio().getImageMusculo() != null) {
                holder.img_imagenEjercicio_imteEjercicioEnTabla.setImageBitmap(ejercicioInfo.getEjercicio().getImageMusculo());


            }
            if (ejercicioInfo.getEjercicio().getImageMusculo() == null) {
                Bitmap noImage = BitmapFactory.decodeResource(c.getResources(), R.drawable.noimage);
                holder.img_imagenEjercicio_imteEjercicioEnTabla.setImageBitmap(noImage);

            }
        }catch (Exception e) {
                Bitmap noImage = BitmapFactory.decodeResource(c.getResources(), R.drawable.noimage);
                holder.img_imagenEjercicio_imteEjercicioEnTabla.setImageBitmap(noImage);

        }

    }

    @Override
    public int getItemCount() {
        try{
            if(listaEjercicioInfo.size() != 0){
                return listaEjercicioInfo.size();
            }
            else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }
}
