package com.example.tfg2;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
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

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.application.TablaViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.ejercicios.adapter.ListaEjercicoInfoEnTablaAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.ejercicios.clases.EjercicioYPosicion;
import com.example.tfg2.ejercicios.viewHolder.EjercicioViewHolder;
import com.example.tfg2.tabla.clases.Tabla;
import com.example.tfg2.tabla.controladores.TablaController;
import com.example.tfg2.tabla.modelos.TablaDB;
import com.example.tfg2.user.clases.CurrentUser;
import com.example.tfg2.user.clases.User;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.util.ArrayList;
import java.util.List;

public class CrearTablaActivity extends AppCompatActivity {
    private static final int PETICION2 = 2;
    public static final String EXTRA_POSITIONDIA = "";
    public static final int PETICION4 = 3;
    LinearLayout ly_contenedorFilas_crearTabla;
    Spinner sp_diasEntreno_crearTabla;
    List<LinearLayout> listaDeFilas = new ArrayList<LinearLayout>();
    ArrayList<ArrayList<EjercicioInfo>> listaDiasEjercicio = new ArrayList<>(); //-- cadad dia tendra una lista distinta de ejercicios
    int diasSeleccionados = 0;
    private String nombreTabla = "";

    private TablaViewModel tablaViewModel;

    int position;

    // AL ENTRAR CREO UNA TABLA , CON UN OBJETO --- InfoTablaEjercicio, crear al dar al boton de guardar

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tabla);

        tablaViewModel = ViewModelProviders.of(this).get(TablaViewModel.class);

        ly_contenedorFilas_crearTabla = findViewById(R.id.ly_contenedorFilas_crearTabla);
        sp_diasEntreno_crearTabla = findViewById(R.id.sp_diasEntreno_crearTabla);

        crearListasPorDia();

//------------- Seleccion de dias //-----------------------------------------------
        sp_diasEntreno_crearTabla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 diasSeleccionados = position+1;

                putsLinearLayouts(diasSeleccionados);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//------------------------------------------------------------------------//
    }


    @SuppressLint("WrongConstant")
    private void putsLinearLayouts(int numeroFilas){
        ly_contenedorFilas_crearTabla.removeAllViewsInLayout();
        for(int i = 0; i<numeroFilas; i++) {

            HorizontalScrollView hsv = new HorizontalScrollView(getApplicationContext());
            hsv.setPadding(0,5,0,5);

            //int red = Color.parseColor("FF0000");



            LinearLayout ly = new LinearLayout(getApplicationContext()); // recipiente por linea
            ly.setOrientation(LinearLayout.HORIZONTAL);
            ly.setBackgroundColor(ponerColoresFondo(i));



            ListaEjercicoInfoEnTablaAdapter adapter = new ListaEjercicoInfoEnTablaAdapter(this,listaDiasEjercicio.get(i));

            RecyclerView rv = new RecyclerView(getApplicationContext());
            rv.setAdapter(adapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv.setLayoutManager(linearLayoutManager);

            Button btn = new Button(getApplicationContext());
            btn.setHeight(400);
            btn.setWidth(200);
            btn.setText("+");
            btn.setTag(i);
            btn.setAlpha(0.8f);

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),AnadirEjercicio.class);
                    intent.putExtra(EXTRA_POSITIONDIA,(Integer) btn.getTag());
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
            ArrayList<EjercicioInfo> a = new ArrayList<EjercicioInfo>();
            listaDiasEjercicio.add(a);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PETICION4){
            if(resultCode == RESULT_OK){

                nombreTabla = data.getStringExtra(PopUpAnadirTabla.EXTRA_NOMBRETABLA_POPUPNOMBRETALBA);
                CurrentUser.setUser(new User(1,"","",""));
                Tabla tablaCreada = new Tabla(nombreTabla,diasSeleccionados);
               /* Integer itTabla = 1;  ------------------------ TABLA ONLINE
                        //TablaController.obtenerUltimoIdTablaUsuario(tablaCreada);
                if(itTabla != null){
                    tablaCreada.setId(itTabla);
                    tablaCreada.setListaDiasEjercicio(listaDiasEjercicio);
                    if(TablaController.addEjerciciosTablaUser(tablaCreada)){
                        //finish();
                    }else{
                        System.out.println("ERROR CREAR TABLA");
                    }
                }
*/
                addTablaDatosLocal(tablaCreada);

            }
        }
        else if(requestCode == PETICION2){
            if (resultCode == RESULT_OK){
                byte [] imagenByte = data.getByteArrayExtra(AnadirEjercicio.EXTRA_IMAGEN_EJERCICIO_ANADIREJERCIICO);
                EjercicioYPosicion ejercicioYPosicion = (EjercicioYPosicion) data.getSerializableExtra(AnadirEjercicio.EJERCICIO_CREADO);

                if(imagenByte != null) {
                    ejercicioYPosicion.getEjercicioInfo().getEjercicio().setImageMusculo(ImagenesBlobBitmap.bytes_to_bitmap(imagenByte));
                }

                listaDiasEjercicio.get(ejercicioYPosicion.getPosicion()).add(ejercicioYPosicion.getEjercicioInfo());
                putsLinearLayouts(diasSeleccionados);
            }else if(requestCode == RESULT_CANCELED){
                System.out.println("ERROR");
            }else {
                AnadirEjercicio.esperaActivacion = true;
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

    public void cancelarCrearTabla(View view) {

    }

    public void guardarTabla(View view) {
        Intent intent  = new Intent(this,PopUpAnadirTabla.class);
        startActivityForResult(intent,PETICION4);
    }

    private boolean guardarDatosDB(){

        return false;
    }

    private void addTablaDatosLocal(Tabla tabla){
        TablaLocal tablaLocal = new TablaLocal(tabla.getNombre(),true);
        if(tablaViewModel.nombreTablaDisponible(tablaLocal.getNombre())){
            if(tablaViewModel.addTablaLocal(tablaLocal))
                System.out.println("AÑADIDO CORRECTAMENTE");
            else
                System.out.println("ERROR AL AÑADIR TABLA");
        }else{
            System.out.println("NOMBRE REPETIDO");
        }
    }
}
