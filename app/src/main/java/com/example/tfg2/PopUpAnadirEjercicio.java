package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PopUpAnadirEjercicio extends AppCompatActivity {
    TextView txt_nombreEjercicio_popUpAnadirEjercicio;
    ImageView poip_imagenEjercicio_popipAnadirEjercicio;
    TextView txt_descripcionEjercicio_popUpAnadirEjercicio;
    TextView number_numeroSeries_popUpAnadirEjercicio;
    TextView number_numeroRepeticiones_popUpAnadirEjercicio;
    Button btn_anadirEjercicio_popUpAnadirEjercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_anadir_ejercicio);

        txt_nombreEjercicio_popUpAnadirEjercicio = (TextView)findViewById(R.id.txt_nombreEjercicio_popUpAnadirEjercicio);
        poip_imagenEjercicio_popipAnadirEjercicio = (ImageView) findViewById(R.id.poip_imagenEjercicio_popipAnadirEjercicio);
        txt_descripcionEjercicio_popUpAnadirEjercicio = (TextView)findViewById(R.id.txt_descripcionEjercicio_popUpAnadirEjercicio);
        number_numeroSeries_popUpAnadirEjercicio = (TextView)findViewById(R.id.number_numeroSeries_popUpAnadirEjercicio);
        number_numeroRepeticiones_popUpAnadirEjercicio = (TextView)findViewById(R.id.number_numeroRepeticiones_popUpAnadirEjercicio);
        btn_anadirEjercicio_popUpAnadirEjercicio = (Button) findViewById(R.id.btn_anadirEjercicio_popUpAnadirEjercicio);

        String textoPrueba = "HOOLA ME adasdasdsad asdasdasdsadasdasdsadasdsadasd";
        for(int i = 0 ;  i < 5; i++){
            textoPrueba += textoPrueba;
        }

        txt_descripcionEjercicio_popUpAnadirEjercicio.setText(textoPrueba);
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.80),(int)(alto * 0.75));
    }
}