package com.example.tfg2.tabla.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.tabla.viewHolder.TablaEntrenarViewHolder;
import com.example.tfg2.tabla.viewHolder.TablaLocalViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListaTablaEntrenarAdapter extends RecyclerView.Adapter<TablaEntrenarViewHolder> {
    private Context c;
    private List<TablaLocal> tablaLocals;
    private LayoutInflater mInflater;

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public List<TablaLocal> getTablaLocals() {
        return tablaLocals;
    }

    public void setTablaLocals(List<TablaLocal> tablaLocals) {
        this.tablaLocals = tablaLocals;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public ListaTablaEntrenarAdapter(Context c, LiveData<List<TablaLocal>> listLivedata){
        this.c = c;
        this.tablaLocals = (ArrayList<TablaLocal>) listLivedata.getValue();
        mInflater = LayoutInflater.from(c);
    }

    public void setListaTablasLocales(List<TablaLocal> listaTablaslocales) {
        this.tablaLocals = listaTablaslocales;
        if(this.tablaLocals == null){
            {
                Log.i("tabla","la lista ciudades es nulo");
            }
        }else{
            for(TablaLocal c:tablaLocals)
            {
                Log.i("tablaLocal","tabla -> " + c.getNombre() + " " + c.getIdTabla());
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TablaEntrenarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TablaEntrenarViewHolder(mInflater.inflate(R.layout.item_rv_tablaentrenar,parent,false),this);
    }

    @Override
    public void onBindViewHolder(@NonNull TablaEntrenarViewHolder holder, int position) {
        TablaLocal tablaActual = tablaLocals.get(position);
        if(tablaActual != null){
            holder.txt_nombreTabla_itemTablaEntrenar3.setText(String.valueOf(tablaActual.getNombre()));
            holder.txt_diaEjercicios_elegirTabla.setText(String.valueOf(tablaActual.getDias()));
        }

    }

    @Override
    public int getItemCount() {
        try{
            if(tablaLocals.size() != 0){
                return tablaLocals.size();
            }
            else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }
}
