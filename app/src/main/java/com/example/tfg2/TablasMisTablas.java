package com.example.tfg2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablasMisTablas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablasMisTablas extends Fragment {
    Button btn_anadirTabla_MisTablas;
    View vista;
    private static final int PETICION1 = 1;

    public TablasMisTablas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_first, container, false);
        btn_anadirTabla_MisTablas = vista.findViewById(R.id.btn_anadirTabla_MisTablas);

        btn_anadirTabla_MisTablas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vista.getContext(),CrearTablaActivity.class);
                startActivityForResult(intent,PETICION1);
            }
        });

        return vista;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PETICION1){
            // LO QUE QUIERO; ACTUALIZAR
            if(resultCode == -1){ //OK

            }else if(resultCode == 0){ // CANCELADO

            }
        }
    }
}