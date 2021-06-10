package com.example.tfg2.ejercicioDatos.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicoRelacionInfoViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;
import com.example.tfg2.ejercicioDatos.viewHolder.EjercicioDatosViewHolder;
import com.example.tfg2.ejercicios.viewHolder.EjercicioPonerInfoViewHolder;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.util.List;
import java.util.Optional;

public class ListaEjercicioDatosAdapter extends RecyclerView.Adapter<EjercicioDatosViewHolder>{
    private List<TablaEjercicioRelacionInfo> tablaEjercicioRelacionInfoList;
    private TablaEjercicioRelacion tablaEjercicioRelacions;
    private Context c;
    private LayoutInflater mInflater;
    private EjercicioDatosViewHolder a;
    private TablaEjercicoRelacionInfoViewModel te;

    public List<TablaEjercicioRelacionInfo> getTablaEjercicioRelacionInfoList() {
        return tablaEjercicioRelacionInfoList;
    }

    public void setTablaEjercicioRelacionInfoList(List<TablaEjercicioRelacionInfo> tablaEjercicioRelacionInfoList) {
        this.tablaEjercicioRelacionInfoList = tablaEjercicioRelacionInfoList;
    }

    public TablaEjercicioRelacion getTablaEjercicioRelacions() {
        return tablaEjercicioRelacions;
    }

    public void setTablaEjercicioRelacions(TablaEjercicioRelacion tablaEjercicioRelacions) {
        this.tablaEjercicioRelacions = tablaEjercicioRelacions;
    }

    public EjercicioDatosViewHolder getA() {
        return a;
    }

    public void setA(EjercicioDatosViewHolder a) {
        this.a = a;
    }

    public TablaEjercicoRelacionInfoViewModel getTe() {
        return te;
    }

    public void setTe(TablaEjercicoRelacionInfoViewModel te) {
        this.te = te;
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public ListaEjercicioDatosAdapter(List<TablaEjercicioRelacionInfo> tablaEjercicioRelacionInfoList, TablaEjercicioRelacion tablaEjercicioRelacions, Context c, TablaEjercicoRelacionInfoViewModel te) {
        this.te = te;
        this.tablaEjercicioRelacionInfoList = tablaEjercicioRelacionInfoList;
        this.tablaEjercicioRelacions = tablaEjercicioRelacions;
        this.c = c;
        this.mInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public EjercicioDatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new EjercicioDatosViewHolder(mInflater.inflate(R.layout.item_rv_datosejercicio,parent,false),this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull EjercicioDatosViewHolder holder, int position) {
        a = holder;
        TablaEjercicioRelacionInfo tablaEjercicioRelacionInfo = tablaEjercicioRelacionInfoList.get(position);

        if(tablaEjercicioRelacions != null){
            holder.txt_repEjercicio_itemDatosEjercicio.setText(String.valueOf(tablaEjercicioRelacions.getRepeticiones()));
            holder.edt_repHecho_itemDatosEjercicio.setHint(String.valueOf(tablaEjercicioRelacionInfo.getPeso()));
            holder.edt_repHechas_itemDatosEjercicio.setHint(String.valueOf(tablaEjercicioRelacionInfo.getRepeticiones()));


        }

    }

    @Override
    public int getItemCount() {
        try{
            if(tablaEjercicioRelacionInfoList.size() != 0){
                return tablaEjercicioRelacionInfoList.size();
            }
            else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }

}
