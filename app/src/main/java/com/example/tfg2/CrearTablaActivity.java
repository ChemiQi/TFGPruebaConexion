package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CrearTablaActivity extends AppCompatActivity {
    LinearLayout ly_contenedorFilas_crearTabla;
    Spinner sp_diasEntreno_crearTabla;
    List<LinearLayout> listaDeFilas = new ArrayList<LinearLayout>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tabla);

        ly_contenedorFilas_crearTabla = findViewById(R.id.ly_contenedorFilas_crearTabla);
        sp_diasEntreno_crearTabla = findViewById(R.id.sp_diasEntreno_crearTabla);

//------------- Seleccion de dias //-----------------------------------------------
        sp_diasEntreno_crearTabla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int numero = position+1;
              putColumnsNumber((numero));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//------------------------------------------------------------------------//
    }

    private void putColumnsNumber(int numeroFilas){
        ly_contenedorFilas_crearTabla.removeAllViewsInLayout();
        for(int i = 0; i<numeroFilas; i++){
            HorizontalScrollView hsv = new HorizontalScrollView(getApplicationContext()); // cada linea
                LinearLayout ly = new LinearLayout(getApplicationContext()); // recipiente por linea
                ly.setOrientation(LinearLayout.HORIZONTAL);
                listaDeFilas.add(ly);
                Button btn = new Button(getApplicationContext());
                btn.setHeight(400);
                btn.setWidth(200);

                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        System.out.println("JEEJ");
                    }
                });

                ly.addView(btn);
            hsv.addView(ly);
            this.ly_contenedorFilas_crearTabla.addView(hsv);
        }
    }
}
