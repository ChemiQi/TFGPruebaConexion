package com.example.tfg2.database.dataBaseOffline.application;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;
import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacionInfo;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionInfoRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionRepository;

import java.util.List;

public class TablaEjercicoRelacionInfoViewModel extends AndroidViewModel {
    private TablaEjercicioRelacionInfoRepository tablaEjercicioRelacionInfoRepository;
    private LiveData<List<TablaEjercicioRelacionInfo>> listaEjercicioTablaInfo;
    public TablaEjercicoRelacionInfoViewModel(@NonNull Application application) {
        super(application);
        this.tablaEjercicioRelacionInfoRepository = new TablaEjercicioRelacionInfoRepository(application);
        this.listaEjercicioTablaInfo = tablaEjercicioRelacionInfoRepository.getLiveDataAllEjercicioInfo();
    }

}
