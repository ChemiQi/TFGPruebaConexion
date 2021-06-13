package com.example.tfg2.tabla.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.viewHolder.TablaViewHolder;

import java.util.List;

public class ListaTablaAdapter extends RecyclerView.Adapter<TablaViewHolder> {
    private Context c;
    private List<Tabla> tablas;
    private LayoutInflater mInflater;

    public ListaTablaAdapter(Context c, List<Tabla> tablas) {
        this.c = c;
        this.tablas = tablas;
        this.mInflater = LayoutInflater.from(c);
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public List<Tabla> getTablas() {
        return tablas;
    }

    public void setTablas(List<Tabla> tablas) {
        this.tablas = tablas;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @NonNull
    @Override
    public TablaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TablaViewHolder(mInflater.inflate(R.layout.item_rv_tablas,parent,false),this);
    }

    @Override
    public void onBindViewHolder(@NonNull TablaViewHolder holder, int position) {
        Tabla tabla = tablas.get(position);
        holder.txt_nombreTabla_itemTablaLocal.setText(String.valueOf(tabla.getNombre()));
        holder.txt_diasTablas_itemTablaEntrenar.setText(String.valueOf(tabla.getDias()));
    }

    @Override
    public int getItemCount() {
        try{
            if(tablas.size() != 0){
                return tablas.size();
            }
            else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }
}
