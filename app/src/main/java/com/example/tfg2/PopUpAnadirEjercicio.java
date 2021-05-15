package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.viewHolder.EjercicioViewHolder;

public class PopUpAnadirEjercicio extends AppCompatActivity {
    TextView txt_nombreEjercicio_popUpAnadirEjercicio;
    ImageView poip_imagenEjercicio_popipAnadirEjercicio;
    TextView txt_descripcionEjercicio_popUpAnadirEjercicio;
    EditText number_numeroSeries_popUpAnadirEjercicio;
    EditText number_numeroRepeticiones_popUpAnadirEjercicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_anadir_ejercicio);
        txt_nombreEjercicio_popUpAnadirEjercicio= (TextView) findViewById(R.id.txt_nombreEjercicio_popUpAnadirEjercicio);
        poip_imagenEjercicio_popipAnadirEjercicio= (ImageView) findViewById(R.id.poip_imagenEjercicio_popipAnadirEjercicio);
        txt_descripcionEjercicio_popUpAnadirEjercicio= (TextView) findViewById(R.id.txt_descripcionEjercicio_popUpAnadirEjercicio);
        number_numeroSeries_popUpAnadirEjercicio= (EditText) findViewById(R.id.number_numeroSeries_popUpAnadirEjercicio);
        number_numeroRepeticiones_popUpAnadirEjercicio= (EditText) findViewById(R.id.number_numeroRepeticiones_popUpAnadirEjercicio);


        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto*0.75));

        Intent intent = getIntent();
        Ejercicio ejercicio = (Ejercicio)intent.getSerializableExtra(EjercicioViewHolder.EXTRA_OBJETO_EJERCICIO);

        //------------------------------------------PONEMOS DATOS------------------------------------------------
        if (ejercicio.getNombreEjercicio().length() > 23)
            txt_nombreEjercicio_popUpAnadirEjercicio.setTextSize(18);

        txt_nombreEjercicio_popUpAnadirEjercicio.setText(ejercicio.getNombreEjercicio());
        txt_descripcionEjercicio_popUpAnadirEjercicio.setText(ejercicio.getDescripcionEjercicio());

    }
}