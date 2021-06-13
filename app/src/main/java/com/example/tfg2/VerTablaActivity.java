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
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.controladores.TablaController;
import com.example.tfg2.tabla.viewHolder.TablaLocalViewHolder;
import com.example.tfg2.tabla.viewHolder.TablaViewHolder;
import com.example.tfg2.utilidades.SpacingItemDecorator;
import com.example.tfg2.utilidades.SpacingLadosDecorator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VerTablaActivity extends AppCompatActivity {
    private static final int PETICION2VERTABLA = 2;
    public static final String EXTRA_TABLA_EDITAR = "chema.martinez/tablaAEditar";
    private LinearLayout ly_contenedorFilas_verTablas;
    private TextView txt_titulo_verTabla;
    private Button btn_editarguardar_verTabla;


    private TablaEjercicioRelacionViewModel  tr;
    private EjercicioViewModel ejercicioViewModel;
    private List<EjercicioLocal> ejercicioLocals2;
    private TablaViewModel tablaViewModel;
    private List<TablaEjercicioRelacion> datosTabla;

    boolean descargado = false;
    boolean editado = false;

    private TablaLocal tablaLocal;
    private Tabla tablaOnline;

    int dias = 0;


    ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio = new ArrayList<>(); //-- cadad dia tendra una lista distinta de ejercicios


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_tabla);

        txt_titulo_verTabla = (TextView) findViewById(R.id.txt_titulo_verGrupoEjercicios);
        ly_contenedorFilas_verTablas = (LinearLayout) findViewById(R.id.ly_contenedorFilas_verGrupoEjercicio);
        btn_editarguardar_verTabla = (Button) findViewById(R.id.btn_editarguardar_verTabla);

        //Crear repositorios
        tr =  ViewModelProviders.of(this).get(TablaEjercicioRelacionViewModel.class);
        ejercicioViewModel = ViewModelProviders.of(this).get(EjercicioViewModel.class);
        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);


        // ---------------------------------- RECIBIR DATOS
        Intent intent = getIntent();
        TablaLocal tabla = (TablaLocal) intent.getSerializableExtra(TablaLocalViewHolder.TABLA_A_CREARTABLAACTIVITY);
        if(tabla != null){
            if(tabla.getIdTabla() <= 199) {
                btn_editarguardar_verTabla.setEnabled(false);
                btn_editarguardar_verTabla.setBackgroundColor(Color.parseColor("#848484"));
                btn_editarguardar_verTabla.setTextColor(Color.parseColor("#6E6E6E"));
            }
            editado = true;
             datosTabla = tr.tablaPorIdTabla(tabla.getIdTabla());
            txt_titulo_verTabla.setText(tabla.getNombre());
            if(datosTabla != null) {
                diaMaximoEjerciico(datosTabla);
                ponerDatosTabla(datosTabla);
                putsLinearLayouts(dias);
            }
        }
         tablaOnline = (Tabla) intent.getSerializableExtra(TablaViewHolder.TABLADESCARGADA_A_CREARTABLAACTIVITY);
        if(tablaOnline != null){
            txt_titulo_verTabla.setText(tablaOnline.getNombre());
             datosTabla = tr.tablaPorIdTabla(tablaOnline.getId());

            if(datosTabla != null && !datosTabla.isEmpty()) {

                descargado = true;
                diaMaximoEjerciico(datosTabla);
                ponerDatosTabla(datosTabla);
                putsLinearLayouts(dias);
            }else{
                List<TablaEjercicioRelacion> datosTablaDescargado = TablaController.obtenerTablaPorId(tablaOnline.getId());
                if(datosTablaDescargado != null){
                    diaMaximoEjerciico(datosTablaDescargado);
                    ponerDatosTabla(datosTablaDescargado);
                    putsLinearLayouts(dias);
                }
            }

        }
        //_------------------------------------- ESTETICA
        ponerTextoBoton();

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void descargarEditar(View view) {
        if(editado){// ----------------------------------------------------- EDITAR
            Intent intent2 = new Intent(this,CrearTablaActivity.class);
            intent2.putExtra(EXTRA_TABLA_EDITAR, (Serializable) datosTabla);
            startActivityForResult(intent2,PETICION2VERTABLA);
        }else {//-------------------------------------- TABLA GLOBAL VER
            btn_editarguardar_verTabla.setText("DESCARGAR");
            if(!descargado){  // ------------------------------ DESCARGAR BOTON
                addTablaDatosLocal(tablaOnline);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PETICION2VERTABLA) {
            if(resultCode == RESULT_OK){
                finish();
            }else{
                finish();
            }
        }

    }

    public void atras(View view) {
        finish();
    }


    private void descargarEjerciciosALocal(List<TablaEjercicioRelacion> tablaRelacion){
        ejercicioLocals2 = ejercicioViewModel.allEjercicios();
        if(ejercicioLocals2.isEmpty()){
            for (TablaEjercicioRelacion tablaEjercicioRelacion : tablaRelacion) {
                if (ejercicioViewModel.insertarEjercicio(new EjercicioLocal(EjercicioController.getEjercicioPorId(tablaEjercicioRelacion.getIdEjercicio())))) {
                } else{

                }
            }

        }
        if(!ejercicioLocals2.isEmpty()) {
            for (TablaEjercicioRelacion tablaEjercicioRelacion : tablaRelacion) {
                for (EjercicioLocal ejercicioLocal : ejercicioLocals2) {
                    if (ejercicioLocal.getIdEjercicio() != tablaEjercicioRelacion.getIdEjercicio()) {
                        if (ejercicioViewModel.insertarEjercicio(new EjercicioLocal(EjercicioController.getEjercicioPorId(tablaEjercicioRelacion.getIdEjercicio())))) {
                        } else {
                        }
                        break;
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addTablaDatosLocal(Tabla tabla){
        tablaLocal = new TablaLocal(tabla.getId(),tabla.getNombre(),dias,false);
            if(tablaViewModel.addTablaLocal(tablaLocal)){
                List<TablaEjercicioRelacion> tablaRelacion =  transformarDatosAEjercicioTabla(tablaLocal,listaDiasEjercicio);
                if(comprobarEjerciciosLocales(tablaRelacion)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Hemos encontrado ejercicios no descargados. \n¿Quíeres descargar esos ejercicios para poder usarlo sin Internet?\nPodrás usarlo en otras tablas ")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    descargarEjerciciosALocal(tablaRelacion);
                                    adda(tablaRelacion);
                                }
                            })
                            .setNegativeButton("No, usaré internet", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    adda(tablaRelacion);
                                }
                            });
                    builder.show();
                }else {
                    adda(tablaRelacion);
                }
            }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean comprobarEjerciciosLocales(List<TablaEjercicioRelacion> tablaRelacion) {
        boolean comprobacion = false;
        ejercicioLocals2 = ejercicioViewModel.allEjercicios();
        for (TablaEjercicioRelacion tablaEjercicioRelacion : tablaRelacion) {
            if(!ejercicioLocals2.stream().anyMatch(ejercicioLocal -> ejercicioLocal.getIdEjercicio() == tablaEjercicioRelacion.getIdEjercicio())) {
                return true;
            }
        }
        return comprobacion;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<TablaEjercicioRelacion> transformarDatosAEjercicioTabla(TablaLocal tablaLocal, ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio) {
        List<TablaEjercicioRelacion> transformado = new ArrayList<TablaEjercicioRelacion>();
        for(int i  = 0 ; i<tablaLocal.getDias() ; i++){
            ArrayList<EjercicioInfo> ejercicioInfos = listaDiasEjercicio.get(i);
            for(EjercicioInfo ejercicioInfo : ejercicioInfos){
                transformado.add(new TablaEjercicioRelacion(tablaLocal,ejercicioInfo.getEjercicio(),ejercicioInfo.getRepeticiones(),ejercicioInfo.getSeries(),i));
            }
        }
        return transformado;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void adda(List<TablaEjercicioRelacion> tablaRelacion){

        if(tr.guardarDatosTablaEjercicio(tablaRelacion)){
            finish();
        }else{
            if(tablaViewModel.borrarTabla(tablaLocal)){
            }else {
            }
        }
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
                return Color.parseColor("#CCFF8700"); // NARANJA
            case 5:
                return Color.parseColor("#CC00FFFF");
            case 6:
                return Color.parseColor("#CCF000FF");
            default:
                return Color.parseColor("#CCFFFFFF");
        }
    }
    private void ponerTextoBoton() {
        if(editado){// ----------------------------------------------------- EDITAR
        }else {//-------------------------------------- TABLA GLOBAL VER
            btn_editarguardar_verTabla.setText("DESCARGAR");
            if(!descargado) {
                btn_editarguardar_verTabla.setEnabled(true);
            }else{
                btn_editarguardar_verTabla.setEnabled(false);
                btn_editarguardar_verTabla.setBackgroundColor(Color.parseColor("#848484"));
                btn_editarguardar_verTabla.setTextColor(Color.parseColor("#6E6E6E"));
            }
        }
    }


}