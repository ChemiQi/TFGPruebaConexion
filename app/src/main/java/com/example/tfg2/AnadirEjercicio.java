package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.partesDelCuerpo.controladores.PdcController;

import java.util.ArrayList;
import java.util.List;

public class AnadirEjercicio extends AppCompatActivity {
    List<PartesDelCuerpo> partes = new ArrayList<>();
    Spinner sp_grupoMuscular_anadirEjercicio;
    List<String> listaPartesCuerpo;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ejercicio);

        sp_grupoMuscular_anadirEjercicio = (Spinner) findViewById(R.id.sp_grupoMuscular_anadirEjercicio);

        obtenerPartesDelCuerpo();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void obtenerPartesDelCuerpo(){
        partes = PdcController.obtenerPartes();
        this.listaPartesCuerpo = new ArrayList<String>();
        listaPartesCuerpo.add("Seleccione..");
            partes.forEach(a -> listaPartesCuerpo.add(a.getNombre()));
        ArrayAdapter<CharSequence> adapterPartes = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaPartesCuerpo);
        sp_grupoMuscular_anadirEjercicio.setAdapter(adapterPartes);
    }
}