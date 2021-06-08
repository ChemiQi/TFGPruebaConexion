package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaEjercicioRelacionViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.ejercicios.adapter.ListaEjercicoInfoEnTablaAdapter;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.ejercicios.clases.EjercicioYPosicion;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.controladores.TablaController;
import com.example.tfg2.tabla.viewHolder.TablaLocalViewHolder;
import com.example.tfg2.tabla.viewHolder.TablaViewHolder;
import com.example.tfg2.utilidades.Exceptions.ExceptionNoEncontrado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.tfg2.CrearTablaActivity.EXTRA_POSITIONDIA;

public class VerTablaActivity extends AppCompatActivity {
    private static final int PETICION2 = 2;
    LinearLayout ly_contenedorFilas_verTablas;
    Spinner sp_diasEntreno_verTabla;
    TextView txt_titulo_verTabla;
    TextView txt_textoPreguntaDias_verTabla;

    private TablaEjercicioRelacionViewModel  tr;
    private EjercicioViewModel ejercicioViewModel;

    int dias = 0;


    ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio = new ArrayList<>(); //-- cadad dia tendra una lista distinta de ejercicios


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla);



        sp_diasEntreno_verTabla = (Spinner) findViewById(R.id.sp_diasEntreno_verTabla);
        txt_titulo_verTabla = (TextView) findViewById(R.id.txt_titulo_verTabla);
        txt_textoPreguntaDias_verTabla = (TextView) findViewById(R.id.txt_textoPreguntaDias_verTabla);
        ly_contenedorFilas_verTablas = (LinearLayout) findViewById(R.id.ly_contenedorFilas_verTablas);
        txt_textoPreguntaDias_verTabla.setVisibility(View.INVISIBLE);
        sp_diasEntreno_verTabla.setVisibility(View.INVISIBLE);

        //Crear repositorios
        tr =  ViewModelProviders.of(this).get(TablaEjercicioRelacionViewModel.class);
        ejercicioViewModel = ViewModelProviders.of(this).get(EjercicioViewModel.class);

        // ---------------------------------- RECIBIR DATOS
        Intent intent = getIntent();
        TablaLocal tabla = (TablaLocal) intent.getSerializableExtra(TablaLocalViewHolder.TABLA_A_CREARTABLAACTIVITY);
        if(tabla != null){
            List<TablaEjercicioRelacion> datosTabla = tr.tablaPorIdTabla(tabla.getIdTabla());
            txt_titulo_verTabla.setText(tabla.getNombre());
            if(datosTabla != null) {
                diaMaximoEjerciico(datosTabla);

                ponerDatosTabla(datosTabla);

                putsLinearLayouts(dias);
            }
        }
        Tabla tablaOnline = (Tabla) intent.getSerializableExtra(TablaViewHolder.TABLADESCARGADA_A_CREARTABLAACTIVITY);
        if(tablaOnline != null){
            txt_titulo_verTabla.setText(tablaOnline.getNombre());
            List<TablaEjercicioRelacion> datosTabla = tr.tablaPorIdTabla(tablaOnline.getId());

            if(datosTabla != null && !datosTabla.isEmpty()) {
                diaMaximoEjerciico(datosTabla);
                ponerDatosTabla(datosTabla);
                putsLinearLayouts(dias);
            }else{
                System.out.println("--------------" + tablaOnline.getId());
                List<TablaEjercicioRelacion> datosTablaDescargado = TablaController.obtenerTablaPorId(tablaOnline.getId());
                if(datosTablaDescargado != null){
                    diaMaximoEjerciico(datosTablaDescargado);
                    ponerDatosTabla(datosTablaDescargado);
                    putsLinearLayouts(dias);
                }
            }

        }

        //_------------------------------------- ESTETICA




    }

    private void ponerDatosTabla(List<TablaEjercicioRelacion> datosTabla) {
        for(TablaEjercicioRelacion e :datosTabla){
            listaDiasEjercicio.get(e.getDia()).add(ejercicioPosicionDesdeTablaEjercicioRelacion(e));
        }
    }

    private EjercicioInfo ejercicioPosicionDesdeTablaEjercicioRelacion(TablaEjercicioRelacion e) {
        EjercicioLocal ejercicioLocal = null;


            ejercicioLocal = ejercicioViewModel.obtenerejercicioPorId(e.getIdEjercicio());
            if(ejercicioLocal == null) {
                ejercicioLocal = new EjercicioLocal(EjercicioController.getEjercicioPorId(e.getIdEjercicio()));
            }

        if(ejercicioLocal != null){
            return new EjercicioInfo(ejercicioLocal,e);
        }
        return new EjercicioInfo();
    }

    private void diaMaximoEjerciico(List<TablaEjercicioRelacion> datosTabla) {

        int max = 0;
        for(TablaEjercicioRelacion e : datosTabla){
            if(e.getDia() > max){
                max = e.getDia();
            }
        }
        dias = (max+1);
        for(int i = 0; i<dias; i++){
            ArrayList<EjercicioInfo> a = new ArrayList<EjercicioInfo>();
            listaDiasEjercicio.add(a);
        }

    }

    private void putsLinearLayouts(int numeroFilas) {
        ly_contenedorFilas_verTablas.removeAllViewsInLayout();
        for (int i = 0; i < numeroFilas; i++) {

            HorizontalScrollView hsv = new HorizontalScrollView(getApplicationContext());
            hsv.setPadding(0, 5, 0, 5);

            LinearLayout ly = new LinearLayout(getApplicationContext()); // recipiente por linea
            ly.setOrientation(LinearLayout.HORIZONTAL);
            ly.setBackgroundColor(ponerColoresFondo(i));

            if (!listaDiasEjercicio.get(i).isEmpty()) {

                ListaEjercicoInfoEnTablaAdapter adapter = new ListaEjercicoInfoEnTablaAdapter(this, listaDiasEjercicio.get(i));

                RecyclerView rv = new RecyclerView(getApplicationContext());
                rv.setAdapter(adapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rv.setLayoutManager(linearLayoutManager);


                ly.addView(rv);

                hsv.addView(ly);


                this.ly_contenedorFilas_verTablas.addView(hsv);
            }
        }
    }

    private int ponerColoresFondo(int i){
        switch (i){
            case 0:
                return Color.parseColor("#CCFF0000");//ROJO
            case 1:
                return Color.parseColor("#CC008BFF"); //AAZUL
            case 2:
                return Color.parseColor("#CC3D8E3D"); //VERDE
            case 3:
                return Color.parseColor("#CCC5C816");//AMARILLO
            case 4:
                return Color.parseColor("#CCFF0000");
            case 5:
                return Color.parseColor("#CCFF0000");
            case 6:
                return Color.parseColor("#CCFF0000");
            default:
                return Color.parseColor("#CCFFFFFF");
        }
    }
}