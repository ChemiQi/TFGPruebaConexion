package com.example.tfg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

public class PopUpAnadirTabla extends AppCompatActivity {

    public static final String EXTRA_NOMBRETABLA_POPUPNOMBRETALBA = "chema.martinez/nombreTabla";
    private EditText txt_nombreEjercicio_popUpAnadirTabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_anadir_tabla);
        txt_nombreEjercicio_popUpAnadirTabla =(EditText) findViewById(R.id.txt_nombreEjercicio_popUpAnadirTabla);
       // PONER MEDIDAS ------------------------------------------------------------

        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);

        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;

        getWindow().setLayout((int)(ancho * 0.85),(int)(alto*0.30));
        //----------------------------------------------
    }

    public void guardarNombreTabla(View view) {
        Intent intent = new Intent(this,CrearTablaActivity.class);
        intent.putExtra(EXTRA_NOMBRETABLA_POPUPNOMBRETALBA,String.valueOf(txt_nombreEjercicio_popUpAnadirTabla.getText()));
        setResult(RESULT_OK,intent);
        finish();
    }
}