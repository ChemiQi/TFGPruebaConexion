package com.example.tfg2.ejercicios.viewHolder;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg2.CrearEjercicio;
import com.example.tfg2.EjerciciosLocalesFr;
import com.example.tfg2.R;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosLocalesAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.user.clases.CurrentUser;

import static com.example.tfg2.ejercicios.viewHolder.EjercicioViewHolder.EXTRA_OBJETO_EJERCICIO;

public class ListaEjerciciosLocalesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public  final String EXTRA_OBJETO_EJERCICIOLOCAL = "chema.martinez_objetoEjercicioLocal";
    public TextView txt_nombre_itemEjercicioLocal;
    public TextView txt_musculo_itemEjercicioLocal;
    public ImageView img_ejercicio_rv_EjercicioLocal;
    public ListaEjerciciosLocalesAdapter eAdapter;
    public ImageButton btn_subirArchivos_itmeEjerciciosLocales;

    View viewLocal;

    public ListaEjerciciosLocalesViewHolder(@NonNull View mItemView, ListaEjerciciosLocalesAdapter listaEjerciciosAdapter) {
        super(mItemView);
        txt_nombre_itemEjercicioLocal = (TextView) mItemView.findViewById(R.id.txt_nombre_itemEjercicioLocal);
        viewLocal = mItemView;
        txt_musculo_itemEjercicioLocal = (TextView) mItemView.findViewById(R.id.txt_musculo_itemEjercicioLocal);
        this.eAdapter = listaEjerciciosAdapter;
        img_ejercicio_rv_EjercicioLocal = (ImageView) mItemView.findViewById(R.id.img_ejercicio_rv_EjercicioLocal);
        btn_subirArchivos_itmeEjerciciosLocales = (ImageButton) mItemView.findViewById(R.id.btn_subirArchivos_itmeEjerciciosLocales);
        btn_subirArchivos_itmeEjerciciosLocales.setBackgroundColor(Color.parseColor("#4D2E040A"));

        itemView.setOnClickListener(this);

        btn_subirArchivos_itmeEjerciciosLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BaseDB.isInternet() == true) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(viewLocal.getContext());
                    EjercicioLocal ejercicioLocal2 = listaEjerciciosAdapter.getListaEjerciciosLocales().get(getLayoutPosition());
                    alerta.setTitle("Subir ejercicio");
                    alerta.setMessage("¿Quíeres añadir este ejercicio? \n" + ejercicioLocal2.getNombre());
                    alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!EjercicioController.comprobarEjercicioUser(ejercicioLocal2, 1)) {
                                EjercicioController.addEjercicioUser(ejercicioLocal2);
                            } else {
                                AlertDialog.Builder alertaEjercicioRepetido = new AlertDialog.Builder(viewLocal.getContext());
                                alerta.setTitle("Ejercicio repetido");
                                alertaEjercicioRepetido.setMessage("Ejercicio con el mismo nombre. ¿Quieres subirlo igualmente?");
                                alertaEjercicioRepetido.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EjercicioController.addEjercicioUser(ejercicioLocal2);
                                    }
                                });
                                alertaEjercicioRepetido.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alertaEjercicioRepetido.show();
                            }
                        }

                    });


                    alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();
                }else{
                    AlertDialog.Builder alertaNoInterneto = new AlertDialog.Builder(viewLocal.getContext());
                    alertaNoInterneto.setTitle("Conexion");
                    alertaNoInterneto.setMessage("No hay conexion a internet");
                    alertaNoInterneto.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertaNoInterneto.show();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {

    }

}
