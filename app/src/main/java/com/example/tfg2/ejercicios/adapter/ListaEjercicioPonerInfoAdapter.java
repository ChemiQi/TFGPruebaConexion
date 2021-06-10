package com.example.tfg2.ejercicios.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.ejercicios.viewHolder.EjercicioPonerInfoViewHolder;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListaEjercicioPonerInfoAdapter extends RecyclerView.Adapter<EjercicioPonerInfoViewHolder> {
    private Context c;
    private List<TablaEjercicioRelacion> listaTablaEjercicioRelacionInfo ;
    private LayoutInflater mInflater;
    private List<EjercicioLocal> ejercicioLocals;

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public List<TablaEjercicioRelacion> getListaTablaEjercicioRelacionInfo() {
        return listaTablaEjercicioRelacionInfo;
    }

    public void setListaTablaEjercicioRelacionInfo(List<TablaEjercicioRelacion> listaTablaEjercicioRelacionInfo) {
        this.listaTablaEjercicioRelacionInfo = listaTablaEjercicioRelacionInfo;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public ListaEjercicioPonerInfoAdapter(Context c, List<TablaEjercicioRelacion> listaTablaEjercicioRelacionInfo, List<EjercicioLocal> ejercicioLocals) {
        this.c = c;
        this.listaTablaEjercicioRelacionInfo = listaTablaEjercicioRelacionInfo;
        this.mInflater = LayoutInflater.from(c);
        this.ejercicioLocals = ejercicioLocals;
    }

    @NonNull
    @Override
    public EjercicioPonerInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EjercicioPonerInfoViewHolder(mInflater.inflate(R.layout.item_rv_tablaejercicioinfo,parent,false),this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull EjercicioPonerInfoViewHolder holder, int position) {
        TablaEjercicioRelacion ejercicioInfo = listaTablaEjercicioRelacionInfo.get(position);
        EjercicioLocal elegido = null;
        Optional<EjercicioLocal> e = ejercicioLocals.stream().filter(e2 -> e2.getIdEjercicio() == ejercicioInfo.getIdEjercicio()).findFirst();
        if(e.isPresent()) {
             elegido = e.get();
             String nombre = elegido.getNombre();
             if(nombre.length() > 18)
                 holder.txt_nombreEJercicio_itemEjercicioInfo.setTextSize(10);
             if(nombre.length() < 30)
                holder.txt_nombreEJercicio_itemEjercicioInfo.setText(String.valueOf(nombre));
            holder.img_ejercicio_itemEjercicioInfo.setImageBitmap(ImagenesBlobBitmap.bytes_to_bitmap(elegido.getImagenEjercicio()));
        }else{
            holder.txt_nombreEJercicio_itemEjercicioInfo.setText("");
        }
        int pesoMax = (int)Math.round(ejercicioInfo.getRepPesoMax());
        holder.txt_nuRep_itemTablaEjercicioInfo.setText(String.valueOf(pesoMax));
        holder.txt_nuRep_itemTablaEjercicioInfo2.setText(String.valueOf(ejercicioInfo.getPesoMax()));
    }

    @Override
    public int getItemCount() {
        //System.out.println(listaTablaEjercicioRelacionInfo.size());
        try{
            if(listaTablaEjercicioRelacionInfo.size() != 0){
                return listaTablaEjercicioRelacionInfo.size();
            }
            else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }
}
