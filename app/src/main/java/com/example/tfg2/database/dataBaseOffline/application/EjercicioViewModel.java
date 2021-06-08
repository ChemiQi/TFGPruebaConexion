package com.example.tfg2.database.dataBaseOffline.application;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;

import java.util.List;

public class EjercicioViewModel  extends AndroidViewModel {

    private EjercicioRepository ejercicioRepository;
    private LiveData<List<EjercicioLocal>> listaEjercicio;


    public EjercicioViewModel(@NonNull Application application) {
        super(application);
        this.ejercicioRepository = new EjercicioRepository(application);
        listaEjercicio = ejercicioRepository.getAllEjercicios();
    }

    public boolean insertarEjercicio(EjercicioLocal ejercicioLocal){
        return ejercicioRepository.insertarEjercicio(ejercicioLocal);
    }

    public LiveData<List<EjercicioLocal>> obtenerEjercicios(){
        return ejercicioRepository.getAllEjercicios();
    }

    public int obtenerIdEjercicio(){
        return ejercicioRepository.getId();
    }

    public List<EjercicioLocal> allEjercicios (){
        return ejercicioRepository.getEjercicios();
    }

    public EjercicioLocal obtenerejercicioPorId(int id){
        return ejercicioRepository.getEjercicioPorId(id);
    }
}
