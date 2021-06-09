package com.example.tfg2.ejercicios.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.EjerciciosLocalesFr;
import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.viewHolder.EjercicioViewHolder;
import com.example.tfg2.ejercicios.viewHolder.ListaEjerciciosLocalesViewHolder;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.util.ArrayList;
import java.util.List;

public class ListaEjerciciosLocalesAdapter extends RecyclerView.Adapter<ListaEjerciciosLocalesViewHolder> {
    private Context c;
    private List<EjercicioLocal> listaEjerciciosLocales;
    private LayoutInflater mInflater;

    public ListaEjerciciosLocalesAdapter(Context context) {
        this.c = context;
        mInflater = LayoutInflater.from(context);
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public List <EjercicioLocal> getListaEjerciciosLocales() {
        return listaEjerciciosLocales;
    }

    public void setListaEjerciciosLocales(List<EjercicioLocal> listaEjerciciosLocales) {
        this.listaEjerciciosLocales = listaEjerciciosLocales;
        if(this.listaEjerciciosLocales == null){
            {
                Log.i("ejercicios","la lista ciudades es nulo");
            }
        }else{
            for(EjercicioLocal c:listaEjerciciosLocales)
            {
                Log.i("ejercicio","ejercicio -> " + c.getNombre() + " " + c.getIdEjercicio());
            }
        }
        notifyDataSetChanged();
    }

    public ListaEjerciciosLocalesAdapter(Context c, LiveData<List<EjercicioLocal>> listaEjerciciosLocales){
        this.c = c;
        this.listaEjerciciosLocales = (ArrayList<EjercicioLocal>) listaEjerciciosLocales.getValue();
        mInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public ListaEjerciciosLocalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_ejercicioslocales,parent,false);
        return new ListaEjerciciosLocalesViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEjerciciosLocalesViewHolder holder, int position) {
        EjercicioLocal ejercicioActual = listaEjerciciosLocales.get(position);
        if (ejercicioActual.getNombre().length() > 18){
            holder.txt_nombre_itemEjercicioLocal.setTextSize(24);
            holder.txt_nombre_itemEjercicioLocal.setText(String.valueOf(ejercicioActual.getNombre()));
        }else{
            holder.txt_nombre_itemEjercicioLocal.setText(String.valueOf(ejercicioActual.getNombre()));
        }
        holder.txt_musculo_itemEjercicioLocal.setText(String.valueOf(ejercicioActual.getNombreMusculo()));

        if(ejercicioActual.getImagenEjercicio() == null){
            Bitmap noImage = BitmapFactory.decodeResource(c.getResources(),R.drawable.noimage);
            holder.img_ejercicio_rv_EjercicioLocal.setImageBitmap(noImage);
        }
        if(ejercicioActual != null){
            holder.img_ejercicio_rv_EjercicioLocal.setImageBitmap(ImagenesBlobBitmap.bytes_to_bitmap(ejercicioActual.getImagenEjercicio()));
        }

        if(ejercicioActual.getIdEjercicio() < 200){
            holder.btn_subirArchivos_itmeEjerciciosLocales.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        try{
            if(listaEjerciciosLocales.size() != 0){
                return listaEjerciciosLocales.size();
            }
            else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }
}
