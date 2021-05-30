package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;
import com.example.tfg2.ejercicios.viewHolder.EjercicioInfoVIewHolder;
import com.example.tfg2.ejercicios.viewHolder.EjercicioViewHolder;
import com.example.tfg2.utilidades.ImagenesBlobBitmap;

public class PopUpAnadirEjercicio extends AppCompatActivity {
    private TextView txt_nombreEjercicio_popUpAnadirEjercicio;
    private ImageView poip_imagenEjercicio_popipAnadirEjercicio;
    private TextView txt_descripcionEjercicio_popUpAnadirEjercicio;
    private EditText number_numeroSeries_popUpAnadirEjercicio;
    private EditText number_numeroRepeticiones_popUpAnadirEjercicio;
    private Button btn_anadirEjercicio_popUpAnadirEjercicio;

    private Ejercicio ejercicio;
    private Ejercicio ejercicioRecibido;
    private EjercicioInfo ejercicioInfoRecibido;

    private byte[] imagenByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_anadir_ejercicio);
        txt_nombreEjercicio_popUpAnadirEjercicio= (TextView) findViewById(R.id.txt_nombreEjercicio_popUpAnadirEjercicio);
        poip_imagenEjercicio_popipAnadirEjercicio= (ImageView) findViewById(R.id.poip_imagenEjercicio_popipAnadirEjercicio);
        txt_descripcionEjercicio_popUpAnadirEjercicio= (TextView) findViewById(R.id.txt_descripcionEjercicio_popUpAnadirEjercicio);
        number_numeroSeries_popUpAnadirEjercicio= (EditText) findViewById(R.id.number_numeroSeries_popUpAnadirEjercicio);
        number_numeroRepeticiones_popUpAnadirEjercicio= (EditText) findViewById(R.id.number_numeroRepeticiones_popUpAnadirEjercicio);
        btn_anadirEjercicio_popUpAnadirEjercicio = (Button) findViewById(R.id.btn_anadirEjercicio_popUpAnadirEjercicio);

    /*
    PONER MEDIDAS ------------------------------------------------------------
     */
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto*0.75));

        // RECIBIR DATOS -----------------------------------------------------

        Intent intent = getIntent();
        try {
             ejercicioRecibido = (Ejercicio) intent.getSerializableExtra(EjercicioViewHolder.EXTRA_OBJETO_EJERCICIO);
        }catch (Exception e){ }
         if(ejercicioRecibido != null){
             this.ejercicio = ejercicioRecibido;
         }
        try {
             ejercicioInfoRecibido = (EjercicioInfo) intent.getSerializableExtra(EjercicioInfoVIewHolder.EXTRA_OBJETO_EJERCICIOINFO);
        }catch (Exception e){

        }
        try{
            imagenByte = intent.getByteArrayExtra(EjercicioViewHolder.EXTRA_IMAGEN_EJERCICIO);
            poip_imagenEjercicio_popipAnadirEjercicio.setImageBitmap(ImagenesBlobBitmap.bytes_to_bitmap(imagenByte));
        }catch (Exception e){
        }


         if(ejercicioInfoRecibido != null){
             ejercicio = ejercicioInfoRecibido.getEjercicio();
             btn_anadirEjercicio_popUpAnadirEjercicio.setText("Salir");
             number_numeroSeries_popUpAnadirEjercicio.setText(String.valueOf(ejercicioInfoRecibido.getSeries()));
             number_numeroRepeticiones_popUpAnadirEjercicio.setText(String.valueOf(ejercicioInfoRecibido.getRepeticiones()));
             number_numeroRepeticiones_popUpAnadirEjercicio.setEnabled(false);
             number_numeroSeries_popUpAnadirEjercicio.setEnabled(false);
         }

        //------------------------------------------PONEMOS DATOS------------------------------------------------
        if (ejercicio.getNombreEjercicio().length() > 23)
            txt_nombreEjercicio_popUpAnadirEjercicio.setTextSize(18);

        txt_nombreEjercicio_popUpAnadirEjercicio.setText(ejercicio.getNombreEjercicio());
        txt_descripcionEjercicio_popUpAnadirEjercicio.setText(ejercicio.getDescripcionEjercicio());

    }

    public void anadirEjercicioAListaEjercicio(View view) {
        if(ejercicioInfoRecibido ==null) {
            AnadirEjercicio.addEjercicioLista(new EjercicioInfo(ejercicio, Integer.valueOf(number_numeroSeries_popUpAnadirEjercicio.getText().toString()), Integer.valueOf(number_numeroRepeticiones_popUpAnadirEjercicio.getText().toString())));
            finish();
        }
        if(ejercicioInfoRecibido != null){

            finish();
        }
    }
}