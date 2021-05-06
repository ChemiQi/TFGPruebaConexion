package com.example.tfg2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.tfg2.ejercicios.adapter.ListaEjerciciosAdapter;
import com.example.tfg2.ejercicios.clases.Ejercicio;
import com.example.tfg2.ejercicios.clases.FotoEjercicio;
import com.example.tfg2.musculos.clases.Musculo;
import com.example.tfg2.musculos.controladores.MusculoController;
import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.partesDelCuerpo.controladores.PdcController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnadirEjercicio extends AppCompatActivity {

    Spinner sp_grupoMuscular_anadirEjercicio;
    Spinner sp_musculos_anadirEjercicio;
    RecyclerView rv_ejercicios_anadirEjercicio;

    List<String> listaPartesCuerpo;
    List<PartesDelCuerpo> partes = new ArrayList<>();
    List<Musculo> listaMusculos = MusculoController.obtenerMusculos();
    ArrayList<Ejercicio> listaEjercicios;
    ArrayList<FotoEjercicio> fotoEjercicios;

    PartesDelCuerpo parteDelCuerpo ;

    ListaEjerciciosAdapter eAdapter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_ejercicio);

        sp_musculos_anadirEjercicio =  (Spinner) findViewById(R.id.sp_musculos_anadirEjercicio);
        sp_grupoMuscular_anadirEjercicio = (Spinner) findViewById(R.id.sp_grupoMuscular_anadirEjercicio);
        rv_ejercicios_anadirEjercicio = (RecyclerView) findViewById(R.id.rv_ejercicios_anadirEjercicio);

        obtenerPartesDelCuerpo();

        sp_grupoMuscular_anadirEjercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {  // <--- Para dependiendo que musculo haya seleccionado, salgan los musculos.
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    System.out.println("JEEJ");
                    if(partes != null) {
                        parteDelCuerpo = partes.get(position - 1);
                        datosSpinnerMusculos(parteDelCuerpo.getId());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        eAdapter = new ListaEjerciciosAdapter(this,listaEjercicios,fotoEjercicios);
        rv_ejercicios_anadirEjercicio.setAdapter(eAdapter);
        rv_ejercicios_anadirEjercicio.setLayoutManager(new LinearLayoutManager(this));

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void datosSpinnerMusculos(int idParteDelCuerpo){
        ArrayAdapter<CharSequence> adapterMusculos = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                listaMusculos.stream().filter(a-> a.getIdMusculo() == idParteDelCuerpo).collect(Collectors.toList())
        );
        sp_musculos_anadirEjercicio.setAdapter(adapterMusculos);

    }
}