package com.example.tfg2;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.FotoEjercicio;
import com.example.tfg2.ejercicios.controladores.EjercicioController;
import com.example.tfg2.ejercicios.viewHolder.EjercicioViewHolder;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.musculos.controladores.MusculoController;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.partesDelCuerpo.controladores.PdcController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrearEjercicio extends AppCompatActivity {
    private ImageView img_imagenEjercicio_crearEjercicio;
    private Bitmap imagenSeleccionada;
    private Spinner sp_grupoMuscular_crearEjercicio;
    private Spinner sp_musculos_crearEjercicio;
    private EditText edt_nombreEjercicio_crearEjercicio;
    private EditText edt_descripcionEjercicio_crearEjercicio;

    private String parteDelCuerpoSeleccionado;
    String musculoSeleccionado;

    EjercicioViewModel ejercicioViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ejercicio);

        img_imagenEjercicio_crearEjercicio = (ImageView) findViewById(R.id.img_imagenEjercicio_crearEjercicio);
        sp_grupoMuscular_crearEjercicio = (Spinner) findViewById(R.id.sp_grupoMuscular_crearEjercicio);
        sp_musculos_crearEjercicio = (Spinner) findViewById(R.id.sp_musculos_crearEjercicio);
        edt_descripcionEjercicio_crearEjercicio = (EditText) findViewById(R.id.edt_descripcionEjercicio_crearEjercicio);
        edt_nombreEjercicio_crearEjercicio = (EditText)findViewById(R.id.edt_nombreEjercicio_crearEjercicio);

        ejercicioViewModel = ViewModelProviders.of(this).get(EjercicioViewModel.class);

        LiveData<List<EjercicioLocal>> ejerciciosLive  = ejercicioViewModel.obtenerEjercicios();
       if(ejerciciosLive != null){
           ejerciciosLive.observe(this, new Observer<List<EjercicioLocal>>() {
               @Override
               public void onChanged(List<EjercicioLocal> ejercicioLocals) {
                   for(EjercicioLocal e : ejercicioLocals){
                       e.mostrar();
                   }
               }
           });
       }

        obtenerPartesDelCuerpo();

        sp_grupoMuscular_crearEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    parteDelCuerpoSeleccionado = (String) sp_grupoMuscular_crearEjercicio.getSelectedItem();
                    if(!parteDelCuerpoSeleccionado.equalsIgnoreCase("Selecciona..")){
                        datosSpinnerMusculos();
                        sp_musculos_crearEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                musculoSeleccionado = (String)sp_musculos_crearEjercicio.getSelectedItem();
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

    // METODOS BOTONES -----------------------------------------------------------------------------------------------------------
    public void cargarImagenEjercicio(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Selecciona la Aplicación"), 10);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path = data.getData();
            img_imagenEjercicio_crearEjercicio.setImageURI(path);
            try {
                imagenSeleccionada = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancelarCrearEjercicio(View view) {
    if(imagenSeleccionada != null){
        if(EjercicioController.ponerFotoEjercicio(imagenSeleccionada,Integer.valueOf(String.valueOf(edt_nombreEjercicio_crearEjercicio.getText())))){
            System.out.println("IMAGEN AÑADIDA CORRECTAMENTE");
        }
    }

       // finish();
    }

    public void crearEjercicioOffline(View view) {
       /* this.nombreMusculo = nombreMusculo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenEjercicio = imagenEjercicio;
        */

        //TODO AÑADIR A LA BASE DE DATOS LOCAL EL EJERCICIO , AÑADIR IMAGEN
        EjercicioLocal ejercicioLocal = new EjercicioLocal(musculoSeleccionado,String.valueOf(edt_nombreEjercicio_crearEjercicio.getText()),String.valueOf(edt_descripcionEjercicio_crearEjercicio.getText()),null);
        ejercicioLocal = new EjercicioLocal("a","a","a",null);
        if(ejercicioViewModel.insertarEjercicio(ejercicioLocal)){
            System.out.println("INSERTADO CORRECTAMENTE");
        }
    }


    // METODOS-------------------------------------------------------------------

    private void obtenerPartesDelCuerpo(){
        String [] partesDelCuerpo = {"Selecciona..", "Brazos","Piernas","Torso","Cardio"};
        //ArrayAdapter<CharSequence> adapterPartes = new ArrayAdapter(this, android.R.layout.simple_spinner_item, R.array.partesCuerpoOffline);
        ArrayAdapter<CharSequence> adapterPartes = new ArrayAdapter(this, android.R.layout.simple_spinner_item, partesDelCuerpo);
        sp_grupoMuscular_crearEjercicio.setAdapter(adapterPartes);
    }


    private void datosSpinnerMusculos(){
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
        sp_musculos_crearEjercicio.setAdapter(adapterMusculos);

    }


}