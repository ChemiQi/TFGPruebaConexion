package com.example.tfg2.ejercicioDatos.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.AnadirEjercicio;
import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicoRelacionInfoViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;
import com.example.tfg2.ejercicioDatos.adapter.ListaEjercicioDatosAdapter;

import java.util.List;

public class EjercicioDatosViewHolder extends RecyclerView.ViewHolder{

    public EditText edt_repHecho_itemDatosEjercicio;
    public EditText edt_repHechas_itemDatosEjercicio;
    public TextView txt_repEjercicio_itemDatosEjercicio;
    public Button bnt_subirDatos_datosEjercicios;
    private TablaEjercicoRelacionInfoViewModel te;
    public EjercicioDatosViewHolder(@NonNull View itemView, ListaEjercicioDatosAdapter listaEjercicioDatosAdapter) {
        super(itemView);
        edt_repHecho_itemDatosEjercicio = (EditText)itemView.findViewById(R.id.edt_repHecho_itemDatosEjercicio);
        edt_repHechas_itemDatosEjercicio = (EditText) itemView.findViewById(R.id.edt_repHechas_itemDatosEjercicio);
        txt_repEjercicio_itemDatosEjercicio = (TextView) itemView.findViewById(R.id.txt_repEjercicio_itemDatosEjercicio);
        bnt_subirDatos_datosEjercicios = (Button) itemView.findViewById(R.id.bnt_subirDatos_datosEjercicios);

        bnt_subirDatos_datosEjercicios.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(String.valueOf(edt_repHechas_itemDatosEjercicio.getText()).length() != 0 && String.valueOf(edt_repHecho_itemDatosEjercicio.getText()).length() != 0 ){
                    TablaEjercicioRelacionInfo tablaEjercicioRelacionInfo = listaEjercicioDatosAdapter.getTablaEjercicioRelacionInfoList().get(getLayoutPosition());
                    tablaEjercicioRelacionInfo.setPeso(Integer.valueOf(String.valueOf(edt_repHecho_itemDatosEjercicio.getText())));
                    tablaEjercicioRelacionInfo.setRepeticiones(Integer.valueOf(String.valueOf(edt_repHechas_itemDatosEjercicio.getText())));

                    if(listaEjercicioDatosAdapter.getTe().actualizarDatos(tablaEjercicioRelacionInfo)){
                        System.out.println("ACTUALIZADO OK");
                    }

                }
            }
        });
    }

}
