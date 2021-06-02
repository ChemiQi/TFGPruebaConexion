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
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.viewHolder.EjercicioViewHolder;

import java.util.ArrayList;

public class ListaEjerciciosAdapter extends RecyclerView.Adapter<EjercicioViewHolder> {
    private Context c;
    private ArrayList<Ejercicio> listaEjercicios;
   // private ArrayList<FotoEjercicio> listaFotosEjercicio;
    private LayoutInflater mInflater;

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(ArrayList<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

   /* public ArrayList<FotoEjercicio> getListaFotosEjercicio() {
        return listaFotosEjercicio;
    }

    public void setListaFotosEjercicio(ArrayList<FotoEjercicio> listaFotosEjercicio) {
        this.listaFotosEjercicio = listaFotosEjercicio;
    }*/

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public ListaEjerciciosAdapter(Context c, ArrayList<Ejercicio> listaEjercicios) {
        this.c = c;
        this.listaEjercicios = listaEjercicios;
        mInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_ejercicios,parent,false);
        return new EjercicioViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioViewHolder holder, int position) {
        Ejercicio ejercicioActual = listaEjercicios.get(position);
        if (ejercicioActual.getNombreEjercicio().length() > 23){
            holder.txt_nombre_itemEjercicio.setTextSize(18);
            holder.txt_nombre_itemEjercicio.setText(String.valueOf(ejercicioActual.getNombreEjercicio()));
        }else{
            holder.txt_nombre_itemEjercicio.setText(String.valueOf(ejercicioActual.getNombreEjercicio()));
        }

        holder.txt_musculo_itemEjercicio.setText(String.valueOf(ejercicioActual.getMusculo().getNombreMusculo()));

        if(ejercicioActual.getImageMusculo() != null){
            holder.img_ejercicio_rv_Ejercicio.setImageBitmap(ejercicioActual.getImageMusculo());
        }
        if(ejercicioActual.getImageMusculo() == null){
            Bitmap noImage = BitmapFactory.decodeResource(c.getResources(),R.drawable.noimage);
            holder.img_ejercicio_rv_Ejercicio.setImageBitmap(noImage);
        }
       /* if(listaFotosEjercicio != null){
            for(FotoEjercicio fotoEjercicio : listaFotosEjercicio){
                if(ejercicioActual.getIdEjercicio() == fotoEjercicio.getIdLiga() && fotoEjercicio.getFoto() != null){
                    holder.img_ejercicio_rv_Ejercicio.setImageBitmap(fotoEjercicio.getFoto());
                    break;

                }
            }
        }*/

    }

    @Override
    public int getItemCount() {
        try{
            if(listaEjercicios.size() != 0){
                return listaEjercicios.size();
            }
            else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }

    }
}
