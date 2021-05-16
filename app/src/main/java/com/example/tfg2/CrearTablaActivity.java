package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.ejercicios.adapter.ListaEjercicoInfoEnTablaAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;

import java.util.ArrayList;
import java.util.List;

public class CrearTablaActivity extends AppCompatActivity {
    private static final int PETICION2 = 2;
    LinearLayout ly_contenedorFilas_crearTabla;
    Spinner sp_diasEntreno_crearTabla;
    List<LinearLayout> listaDeFilas = new ArrayList<LinearLayout>();
    ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio = new ArrayList<>(); //-- cadad dia tendra una lista distinta de ejercicios

    // AL ENTRAR CREO UNA TABLA , CON UN OBJETO --- InfoTablaEjercicio, crear al dar al boton de guardar

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tabla);

        ly_contenedorFilas_crearTabla = findViewById(R.id.ly_contenedorFilas_crearTabla);
        sp_diasEntreno_crearTabla = findViewById(R.id.sp_diasEntreno_crearTabla);

        crearListasPorDia();

//------------- Seleccion de dias //-----------------------------------------------
        sp_diasEntreno_crearTabla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int numero = position+1;
              //putColumnsNumber(numero);
                putsLinearLayouts(numero);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//------------------------------------------------------------------------//
    }

    private void putColumnsNumber(int numeroFilas){
        ly_contenedorFilas_crearTabla.removeAllViewsInLayout();
        for(int i = 0; i<numeroFilas; i++){
            HorizontalScrollView hsv = new HorizontalScrollView(getApplicationContext()); // cada linea
                LinearLayout ly = new LinearLayout(getApplicationContext()); // recipiente por linea
                ly.setOrientation(LinearLayout.HORIZONTAL);
                listaDeFilas.add(ly);


                //---------------------------------------------------------------------------------------------------
            ArrayList<EjercicioInfo> diaEjercicios = listaDiasEjercicio.get(i);
            ListaEjercicoInfoEnTablaAdapter adapter = new ListaEjercicoInfoEnTablaAdapter(this,diaEjercicios);


                Button btn = new Button(getApplicationContext());
                btn.setHeight(400);
                btn.setWidth(200);

                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("JEEJ");
                    }
                });

                ly.addView(btn);
            hsv.addView(ly);
            this.ly_contenedorFilas_crearTabla.addView(hsv);
        }
    }

    @SuppressLint("WrongConstant")
    private void putsLinearLayouts(int numeroFilas){
        ly_contenedorFilas_crearTabla.removeAllViewsInLayout();
        for(int i = 0; i<numeroFilas; i++) {
            HorizontalScrollView hsv = new HorizontalScrollView(getApplicationContext());

            LinearLayout ly = new LinearLayout(getApplicationContext()); // recipiente por linea
            ly.setOrientation(LinearLayout.HORIZONTAL);

            ArrayList<EjercicioInfo> diaEjercicios = listaDiasEjercicio.get(i);
            ListaEjercicoInfoEnTablaAdapter adapter = new ListaEjercicoInfoEnTablaAdapter(this,diaEjercicios);

            RecyclerView rv = new RecyclerView(getApplicationContext());
            rv.setAdapter(adapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv.setLayoutManager(linearLayoutManager);

            Button btn = new Button(getApplicationContext());
            btn.setHeight(400);
            btn.setWidth(200);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),AnadirEjercicio.class);
                    startActivityForResult(intent,PETICION2);
                }
            });

            ly.addView(rv);
            ly.addView(btn);
            hsv.addView(ly);


            this.ly_contenedorFilas_crearTabla.addView(hsv);
        }
    }

    private void crearListasPorDia(){  // 7 por los dias de la semana de ejercicio

        for(int i = 0; i<7; i++){
            listaDiasEjercicio.add(new ArrayList<EjercicioInfo>());
        }
    }

}
