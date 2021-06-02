package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.tfg2.database.modelos.BaseDB;
import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.ejercicios.clases.EjercicioYPosicion;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.musculos.controladores.MusculoController;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.partesDelCuerpo.controladores.PdcController;
import com.example.tfg2.user.clases.CurrentUser;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnadirEjercicio extends AppCompatActivity {

    public static final String EJERCICIO_CREADO = "";
    public  static final String EXTRA_IMAGEN_EJERCICIO_ANADIREJERCIICO = "chema.martinez/imagenAEnviar";
    Spinner sp_grupoMuscular_anadirEjercicio;
    Spinner sp_musculos_anadirEjercicio;
    RecyclerView rv_ejercicios_anadirEjercicio;

    private int posicion;
    private static EjercicioInfo ejercicioInfoBase;
    Intent intent;
    public static Boolean esperaActivacion = false;

    List<String> listaPartesCuerpo;
    List<PartesDelCuerpo> partes = new ArrayList<>();
    List<Musculo> listaMusculos;

    ArrayList<Ejercicio> listaEjercicios;
    List<Musculo> musculosPorParteDelCuerpo;

    PartesDelCuerpo parteDelCuerpo ;
    Musculo musculoSeleccionado;


    ListaEjerciciosAdapter eAdapter;

    //-------------------------------------------------------------------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ejercicio);

        sp_musculos_anadirEjercicio =  (Spinner) findViewById(R.id.sp_musculos_anadirEjercicio);
        sp_grupoMuscular_anadirEjercicio = (Spinner) findViewById(R.id.sp_grupoMuscular_anadirEjercicio);
        rv_ejercicios_anadirEjercicio = (RecyclerView) findViewById(R.id.rv_ejercicios_anadirEjercicio);

        intent = getIntent();
        posicion = intent.getIntExtra(CrearTablaActivity.EXTRA_POSITIONDIA, -1);

        if(BaseDB.conectarConBaseDeDatos() != null){
            listaMusculos = MusculoController.obtenerMusculos();
        }

        try {
            esperarRespuesta();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        obtenerPartesDelCuerpo();


        if(CurrentUser.getUser() != null) {
            System.out.println(CurrentUser.getUser().getNameUser());
            listaEjercicios = EjercicioController.obtenerEjerciciosUsuario(CurrentUser.getUser().getId());

        }else {
            listaEjercicios  = new ArrayList<>();
        }

        sp_grupoMuscular_anadirEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  // <--- Para dependiendo que musculo haya seleccionado, salgan los musculos.
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //spiner de partes del cuerpo
                if(position != 0){
                    if(partes != null) {
                        parteDelCuerpo = partes.get(position - 1);
                        listaEjercicios = EjercicioController.ejerciciosPorParteDelCuerpo(parteDelCuerpo);
                                //TODO AÑADIR LOS EJERCICIOS RECIBIDOS a la array
                        //EjercicioController.ejerciciosPorParteDelCuerpo(parteDelCuerpo);
                        añadirEjerciciosTabla();
                        datosSpinnerMusculos(parteDelCuerpo.getId());
                        sp_musculos_anadirEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                musculoSeleccionado = musculosPorParteDelCuerpo.get(position);
                                listaEjercicios = EjercicioController.obtenerEjerciciosPorMusculo(musculoSeleccionado);
                                añadirEjerciciosTabla();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    //--------------------------METODOS-------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void obtenerPartesDelCuerpo(){
        if(BaseDB.conectarConBaseDeDatos() != null) {
            partes = PdcController.obtenerPartes();
            this.listaPartesCuerpo = new ArrayList<String>();
            listaPartesCuerpo.add("Seleccione..");
            partes.forEach(a -> listaPartesCuerpo.add(a.getNombre()));
            ArrayAdapter<CharSequence> adapterPartes = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPartesCuerpo);
            sp_grupoMuscular_anadirEjercicio.setAdapter(adapterPartes);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void datosSpinnerMusculos(int idParteDelCuerpo){
        musculosPorParteDelCuerpo = listaMusculos.stream().filter(a-> a.getIdZonaCuerpo() == idParteDelCuerpo).collect(Collectors.toList());

        List<String> nombreMusculos = new ArrayList<>();
        musculosPorParteDelCuerpo.forEach(musculo -> nombreMusculos.add(musculo.getNombreMusculo()));
        ArrayAdapter<CharSequence> adapterMusculos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nombreMusculos);
        sp_musculos_anadirEjercicio.setAdapter(adapterMusculos);

    }

    private void añadirEjerciciosTabla(){
        eAdapter = new ListaEjerciciosAdapter(this,listaEjercicios);
        rv_ejercicios_anadirEjercicio.setAdapter(eAdapter);
        rv_ejercicios_anadirEjercicio.setLayoutManager(new LinearLayoutManager(this));

    }

    public static void addEjercicioLista(EjercicioInfo ejercicioInfo){
        ejercicioInfoBase = ejercicioInfo;
        esperaActivacion = true;
    }

    private void esperarRespuesta() throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!esperaActivacion){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                ponerResultado();
                esperaActivacion = false;
            }
        }).start();

    }
    private void ponerResultado(){
        Intent inten2t = new Intent(this,CrearTablaActivity.class);
        EjercicioYPosicion ejercicioYPosicion = new EjercicioYPosicion(posicion,ejercicioInfoBase);
        if(ejercicioInfoBase != null) {
            if (ejercicioInfoBase.getEjercicio().getImageMusculo() != null) {
                byte[] imagenn = transformarImagen(ejercicioInfoBase.getEjercicio().getImageMusculo());
                ejercicioInfoBase.getEjercicio().setImageMusculo(null);
                inten2t.putExtra(EXTRA_IMAGEN_EJERCICIO_ANADIREJERCIICO, imagenn);
            }
        }
        inten2t.putExtra(EJERCICIO_CREADO,ejercicioYPosicion);
        setResult(RESULT_OK,inten2t);
        finish();
}

    private byte[] transformarImagen(Bitmap bmFoto){
        return ImagenesBlobBitmap.bitmap_to_bytes(bmFoto);
    }

    public void cancelarElegirEjerccio(View view) {
        finish();
    }
}