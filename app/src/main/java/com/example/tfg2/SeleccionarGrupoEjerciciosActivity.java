package com.example.tfg2;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.ejercicios.adapter.ListaEjercicoInfoEnTablaAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.controladores.TablaController;
import com.example.tfg2.tabla.viewHolder.TablaEntrenarViewHolder;
import com.example.tfg2.tabla.viewHolder.TablaLocalViewHolder;
import com.example.tfg2.tabla.viewHolder.TablaViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SeleccionarGrupoEjerciciosActivity extends AppCompatActivity {
    public static final String EXTRA_EJERCICIOAENVIARAVEREJERCICIOS = "chema.martinez/diaParaVerEjercicio";
    public static final String EXTRA_TABLA_PONERDATOS = "chema.martinez/datosTablaAgregarDatos";


    private LinearLayout ly_contenedorFilas_verGrupoEjercicio;
    private TextView txt_titulo_verGrupoEjercicios;

    private Button btn_cancerlar_verGrupoEjercicio;

    private TablaEjercicioRelacionViewModel tr;
    private EjercicioViewModel ejercicioViewModel;
    private List<EjercicioLocal> ejercicioLocals2;
    private TablaViewModel tablaViewModel;
    private List<TablaEjercicioRelacion> datosTabla;




    private TablaLocal tabla;
    private Tabla tablaOnline;

    int dias = 0;

    ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio = new ArrayList<>(); //-- cadad dia tendra una lista distinta de ejercicios


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_grupo_ejercicios);


        txt_titulo_verGrupoEjercicios = (TextView) findViewById(R.id.txt_titulo_verGrupoEjercicios);

        ly_contenedorFilas_verGrupoEjercicio = (LinearLayout) findViewById(R.id.ly_contenedorFilas_verGrupoEjercicio);

        btn_cancerlar_verGrupoEjercicio = (Button) findViewById(R.id.btn_cancerlar_verGrupoEjercicio);

        //Crear repositorios
        tr =  ViewModelProviders.of(this).get(TablaEjercicioRelacionViewModel.class);
        ejercicioViewModel = ViewModelProviders.of(this).get(EjercicioViewModel.class);
        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);

        // ---------------------------------- RECIBIR DATOS
        Intent intent = getIntent();
         tabla = (TablaLocal) intent.getSerializableExtra(TablaEntrenarViewHolder.TABLA_A_VERGRUPOEJERCICIO);
        System.out.println(tabla.getNombre());
        System.out.println(tabla.getIdTabla());
        datosTabla = tr.tablaPorIdTabla(tabla.getIdTabla());
        txt_titulo_verGrupoEjercicios.setText(tabla.getNombre());

        if(datosTabla != null) {
            diaMaximoEjerciico(datosTabla);
            if(ponerDatosTabla(datosTabla)){
                putsLinearLayouts(dias);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("No tienes internet ni ejercicios descargados, no se mostrarán los ejercicios");
                builder.show();
            }
        }
    }



    public void atras(View view) {
        finish();
    }




    private boolean ponerDatosTabla(List<TablaEjercicioRelacion> datosTabla) {
        for(TablaEjercicioRelacion e :datosTabla){
            try {
                EjercicioInfo ejercicioYPosicion = ejercicioPosicionDesdeTablaEjercicioRelacion(e);
                if(ejercicioYPosicion==null){
                    return false;
                }
                listaDiasEjercicio.get(e.getDia()).add(ejercicioYPosicion);
            }catch (Exception e2){

                return false;
            }
        }
        return true;
    }

    private EjercicioInfo ejercicioPosicionDesdeTablaEjercicioRelacion(TablaEjercicioRelacion e) {
        EjercicioLocal ejercicioLocal = null;
        ejercicioLocal = ejercicioViewModel.obtenerejercicioPorId(e.getIdEjercicio());
        if(ejercicioLocal == null) {
            Ejercicio ejercicio = EjercicioController.getEjercicioPorId(e.getIdEjercicio());
            if(ejercicio != null) {
                ejercicioLocal = new EjercicioLocal(ejercicio);
            }else{
                return null;
            }
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
        ly_contenedorFilas_verGrupoEjercicio.removeAllViewsInLayout();
        for (int i = 0; i < numeroFilas; i++) {
            HorizontalScrollView hsv = new HorizontalScrollView(getApplicationContext());
            hsv.setPadding(0, 25, 0, 5);

            LinearLayout ly = new LinearLayout(getApplicationContext()); // recipiente por linea
            ly.setOrientation(LinearLayout.HORIZONTAL);



            if (!listaDiasEjercicio.get(i).isEmpty()) {

                ListaEjercicoInfoEnTablaAdapter adapter = new ListaEjercicoInfoEnTablaAdapter(this, listaDiasEjercicio.get(i));

                RecyclerView rv = new RecyclerView(getApplicationContext());
                rv.setAdapter(adapter);


                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rv.setLayoutManager(linearLayoutManager);
                rv.setBackgroundColor(ponerColoresFondo(i));

                ly.addView(rv);

                hsv.addView(ly);


                Button btn = new Button(getApplicationContext());
                btn.setHeight(100);
                btn.setWidth(100);
                btn.setText("ENTRENAR");
                btn.setTag(i);
                btn.setAlpha(0.8f);

                btn.setBackgroundColor(ponerColoresFondo(i));


                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),PonerDatosActivity.class);
                        intent.putExtra(EXTRA_EJERCICIOAENVIARAVEREJERCICIOS,(Integer) btn.getTag());
                        intent.putExtra(EXTRA_TABLA_PONERDATOS,tabla);
                        System.out.println(tabla.getIdTabla() + tabla.getNombre());
                        startActivity(intent);
                    }
                });

                this.ly_contenedorFilas_verGrupoEjercicio.addView(hsv);
                this.ly_contenedorFilas_verGrupoEjercicio.addView(btn);

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
                return Color.parseColor("#CCFF8700"); // NARANJA
            case 5:
                return Color.parseColor("#CC00FFFF");
            case 6:
                return Color.parseColor("#CCF000FF");
            default:
                return Color.parseColor("#CCFFFFFF");
        }
    }



}