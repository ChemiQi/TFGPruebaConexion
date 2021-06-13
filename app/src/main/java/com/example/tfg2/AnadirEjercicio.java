package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
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
    private View vista;

    public static final String EJERCICIO_CREADO = "";
    public  static final String EXTRA_IMAGEN_EJERCICIO_ANADIREJERCIICO = "chema.martinez/imagenAEnviar";
    private Spinner sp_grupoMuscular_anadirEjercicio;
    private Spinner sp_musculos_anadirEjercicio;
    private RecyclerView rv_ejercicios_anadirEjercicio;
    private String parteDelCuerpoSeleccionado;
    private CheckBox checkBox_ejerciciosLocales_crearEjercicio;
    private int posicion;
    private static EjercicioInfo ejercicioInfoBase;
    private Intent intent;
    public static Boolean esperaActivacion = false;

    private List<String> listaPartesCuerpo;
    private List<PartesDelCuerpo> partes = new ArrayList<>();
    private List<Musculo> listaMusculos;

    private ArrayList<Ejercicio> listaEjercicios;
    private List<Musculo> musculosPorParteDelCuerpo;

    private PartesDelCuerpo parteDelCuerpo ;
    private Musculo musculoSeleccionado;


    private EjercicioViewModel ejercicioViewModel;
    private ListaEjerciciosAdapter eAdapter;

    //-------------------------------------------------------------------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ejercicio);

        sp_musculos_anadirEjercicio =  (Spinner) findViewById(R.id.sp_musculos_anadirEjercicio);
        sp_grupoMuscular_anadirEjercicio = (Spinner) findViewById(R.id.sp_grupoMuscular_anadirEjercicio);
        rv_ejercicios_anadirEjercicio = (RecyclerView) findViewById(R.id.rv_ejercicios_anadirEjercicio);
        checkBox_ejerciciosLocales_crearEjercicio= (CheckBox) findViewById(R.id.checkBox_ejerciciosLocales_crearEjercicio);

        intent = getIntent();
        posicion = intent.getIntExtra(CrearTablaActivity.EXTRA_POSITIONDIA, -1);
        ejercicioViewModel = ViewModelProviders.of(this).get(EjercicioViewModel.class);
        try {
            esperarRespuesta();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(BaseDB.isInternet() == true) {
            conConexion();

        }else{
            checkBox_ejerciciosLocales_crearEjercicio.setChecked(true);
            checkBox_ejerciciosLocales_crearEjercicio.setEnabled(false);
            obtenerPartesDelCuerpo();
            sinConexion();
        }

    }


    //--------------------------METODOS-------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void obtenerPartesDelCuerpo(){
        if(BaseDB.isInternet() == true) {
            partes = PdcController.obtenerPartes();
            this.listaPartesCuerpo = new ArrayList<String>();
            listaPartesCuerpo.add("Seleccione..");
            partes.forEach(a -> listaPartesCuerpo.add(a.getNombre()));
            ArrayAdapter<CharSequence> adapterPartes = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPartesCuerpo);
            sp_grupoMuscular_anadirEjercicio.setAdapter(adapterPartes);
        }else{
            ArrayAdapter<CharSequence> adapterPartes = new ArrayAdapter(this, android.R.layout.simple_spinner_item, obtenerPartesDelCuerpoOffline());
            sp_grupoMuscular_anadirEjercicio.setAdapter(adapterPartes);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void conConexion(){
        if (BaseDB.isInternet() == true) {
            listaMusculos = MusculoController.obtenerMusculos();
        } else {

        }
        obtenerPartesDelCuerpo();
        if (CurrentUser.getUser() != null) {
            System.out.println(CurrentUser.getUser().getNameUser());
            listaEjercicios = EjercicioController.obtenerEjerciciosUsuario(CurrentUser.getUser().getId());

        } else {
            listaEjercicios = new ArrayList<>();
        }

        sp_grupoMuscular_anadirEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  // <--- Para dependiendo que musculo haya seleccionado, salgan los musculos.
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { //spiner de partes del cuerpo
                if (position != 0) {
                    if (partes != null) {
                        parteDelCuerpo = partes.get(position - 1);
                        listaEjercicios = EjercicioController.ejerciciosPorParteDelCuerpo(parteDelCuerpo);
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
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sinConexion(){
        obtenerPartesDelCuerpo();
        ArrayList<Ejercicio> prueba = new ArrayList<>();
        ejercicioViewModel.allEjercicios().forEach(e-> prueba.add(new Ejercicio(e)));
        sp_grupoMuscular_anadirEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    if(partes != null){
                        parteDelCuerpoSeleccionado = (String) sp_grupoMuscular_anadirEjercicio.getSelectedItem();



                        datosSpinnerMusculosOffline();
                        sp_musculos_anadirEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position != 0) {
                                    String musculo = (String)sp_musculos_anadirEjercicio.getSelectedItem();
                                    List<Ejercicio> filtrado = prueba.stream().filter(ejercicio -> ejercicio.getMusculo().getNombreMusculo().equalsIgnoreCase(musculo)).collect(Collectors.toList());
                                    listaEjercicios = new ArrayList<>(filtrado);
                                    añadirEjerciciosTabla();
                                }

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

    private String [] obtenerPartesDelCuerpoOffline(){
        return new String[] {"Selecciona..", "Brazos","Piernas","Torso","Cardio"};

    }


   private void datosSpinnerMusculosOffline(){
        String[] n = {};
        ArrayAdapter<CharSequence> adapterMusculos  = new ArrayAdapter(this, android.R.layout.simple_spinner_item,n);;
        if(parteDelCuerpoSeleccionado.equalsIgnoreCase("Brazos")) {
            String[] brazos = {"Selecciona..", "Biceps", "Triceps", "Antebrazo", "Deltoides"};
            adapterMusculos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, brazos);
        }
        if(parteDelCuerpoSeleccionado.equalsIgnoreCase("Piernas")) {
            String [] piernas = {"Selecciona..", "Cuadriceps","Gluteos","Gemelos","Isquiotibiales"};
            adapterMusculos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, piernas);
        }
        if(parteDelCuerpoSeleccionado.equalsIgnoreCase("Torso")) {
            String [] tronco = {"Selecciona..", "Abdomen","Pectoral","Trapecio","Dorsal","Lumbar","Espalda"};
            adapterMusculos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tronco);
        }
        if(parteDelCuerpoSeleccionado.equalsIgnoreCase("Cardio")) {
            String [] cardio = {"Cardio"};
            adapterMusculos = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cardio);
        }
       sp_musculos_anadirEjercicio.setAdapter(adapterMusculos);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void itemChecked(View view) {
        if(checkBox_ejerciciosLocales_crearEjercicio.isChecked()){
            listaEjercicios = new ArrayList<>();
            añadirEjerciciosTabla();
            sp_grupoMuscular_anadirEjercicio.setSelection(0);
            sinConexion();
        }else{
            listaEjercicios = new ArrayList<>();
            añadirEjerciciosTabla();
            rv_ejercicios_anadirEjercicio.removeAllViews();
            sp_grupoMuscular_anadirEjercicio.setSelection(0);
            conConexion();
        }
    }
}