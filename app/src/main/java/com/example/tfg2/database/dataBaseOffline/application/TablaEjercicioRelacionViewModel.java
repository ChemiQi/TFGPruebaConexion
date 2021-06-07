package com.example.tfg2.database.dataBaseOffline.application;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;
import com.example.tfg2.ejercicios.clases.EjercicioInfo;

import java.util.ArrayList;
import java.util.List;

public class TablaEjercicioRelacionViewModel extends AndroidViewModel {
    private TablaEjercicioRelacionRepository tablaEjercicioRelacionRepository;
    private LiveData<List<TablaEjercicioRelacion>> listaEjercicioTabla;

    public TablaEjercicioRelacionViewModel(@NonNull Application application) {
        super(application);
        this.tablaEjercicioRelacionRepository = new TablaEjercicioRelacionRepository(application);
        listaEjercicioTabla = tablaEjercicioRelacionRepository.getAllTablasEjercicios();
    }

    public boolean guardarDatosTablaEjercicio(List<TablaEjercicioRelacion> tablaEjercicioRelacion) {
        return tablaEjercicioRelacionRepository.saveDataTablaEjercicio(tablaEjercicioRelacion);
    }

    public boolean guardarDatoTablaEjercicio(TablaEjercicioRelacion tablaEjercicioRelacion){
        return tablaEjercicioRelacionRepository.saveDataTablaEjercicio(tablaEjercicioRelacion);
    }

    public LiveData<List<TablaEjercicioRelacion>> obtenerTablas(){
        return tablaEjercicioRelacionRepository.getAllInfo();
    }
}
