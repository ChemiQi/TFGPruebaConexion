package com.example.tfg2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.tfg2.database.dataBaseOffline.application.EjercicioViewModel;
import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

import java.io.IOException;
import java.util.List;

public class CrearEjercicio extends AppCompatActivity {
    private ImageView img_imagenEjercicio_crearEjercicio;
    private Bitmap imagenSeleccionada;
    private Spinner sp_grupoMuscular_crearEjercicio;
    private Spinner sp_musculos_crearEjercicio;
    private EditText edt_nombreEjercicio_crearEjercicio;
    private EditText edt_descripcionEjercicio_crearEjercicio;

    private String parteDelCuerpoSeleccionado;
    private String musculoSeleccionado;

    private EjercicioViewModel ejercicioViewModel;
    private ProgressBar progresBarAñadirEJercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ejercicio);

        img_imagenEjercicio_crearEjercicio = (ImageView) findViewById(R.id.img_imagenEjercicio_crearEjercicio);
        sp_grupoMuscular_crearEjercicio = (Spinner) findViewById(R.id.sp_grupoMuscular_crearEjercicio);
        sp_musculos_crearEjercicio = (Spinner) findViewById(R.id.sp_musculos_crearEjercicio);
        edt_descripcionEjercicio_crearEjercicio = (EditText) findViewById(R.id.edt_descripcionEjercicio_crearEjercicio);
        edt_nombreEjercicio_crearEjercicio = (EditText)findViewById(R.id.edt_nombreEjercicio_crearEjercicio);
        progresBarAñadirEJercicio = (ProgressBar) findViewById(R.id.progresBarAñadirEJercicio);
        ejercicioViewModel = ViewModelProviders.of(this).get(EjercicioViewModel.class);

        progresBarAñadirEJercicio.setVisibility(View.INVISIBLE);


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
        System.out.println(ejercicioViewModel.obtenerIdEjercicio());
        finish();
    }

    public void crearEjercicioOffline(View view) {
        if(parteDelCuerpoSeleccionado == null || parteDelCuerpoSeleccionado.equalsIgnoreCase("Selecciona..")){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Campos vacios");
            alerta.setMessage("Por favor, seleccione una parte del cuerpo");

            alerta.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alerta.show();
        }else{
            if(musculoSeleccionado.equalsIgnoreCase("Selecciona..")){
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setTitle("Campos vacios");
                alerta.setMessage("Por favor, seleccione un musculo");

                alerta.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alerta.show();
            }else {
                if (String.valueOf(edt_nombreEjercicio_crearEjercicio.getText()).equals("")) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                    alerta.setTitle("Ejercicio sin nombre");
                    alerta.setMessage("Por favor, escriba un nombre");
                    alerta.setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();
                } else {
                    progresBarAñadirEJercicio.setVisibility(View.VISIBLE);
                    hiloCrearEjercicio();
                }
            }
        }

    }

    private void hiloCrearEjercicio(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                EjercicioLocal ejercicioLocal = new EjercicioLocal(musculoSeleccionado, String.valueOf(edt_nombreEjercicio_crearEjercicio.getText()), String.valueOf(edt_descripcionEjercicio_crearEjercicio.getText()),
                        true, ImagenesBlobBitmap.bitmap_to_bytes(imagenSeleccionada));
                if (ejercicioViewModel.obtenerIdEjercicio() < 200) {
                    ejercicioLocal.setIdEjercicio(200);
                }
                ejercicioViewModel.insertarEjercicio(ejercicioLocal);
                finish();
            }
        }).start();
    }
    // METODOS-------------------------------------------------------------------

    private void obtenerPartesDelCuerpo(){
        String [] partesDelCuerpo = {"Selecciona..", "Brazos","Piernas","Torso","Cardio"};
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