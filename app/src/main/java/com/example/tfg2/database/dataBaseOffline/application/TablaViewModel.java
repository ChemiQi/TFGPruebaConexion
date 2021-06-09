package com.example.tfg2.database.dataBaseOffline.application;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tfg2.database.dataBaseOffline.domain.EjercicioLocal;
import com.example.tfg2.database.dataBaseOffline.domain.Tabla.TablaLocal;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.EjercicioRepository;
import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaRepository;

import java.util.List;

public class TablaViewModel extends AndroidViewModel {
    private TablaRepository tablaRepository;
    private LiveData<List<TablaLocal>> listaTabla;

    public TablaViewModel(@NonNull Application application) {
        super(application);
        this.tablaRepository = new TablaRepository(application);
        listaTabla = tablaRepository.getAllTablas();
    }

    public boolean nombreTablaDisponible(String nombre){
        if(tablaRepository.comprobarTablaPorNombre(nombre).isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public boolean addTablaLocal(TablaLocal tablaLocal){
        return tablaRepository.insertarTabla(tablaLocal);
    }

    public boolean updateTablaLocal(TablaLocal tablaLocal){
        return tablaRepository.updateTablaLocal(tablaLocal);
    }

    public LiveData<List<TablaLocal>> obtenerTablas(){
        return tablaRepository.getAllTablas();
    }

    public TablaLocal obtenerUltimaTabla(){
        return tablaRepository.getLastTable();
    }

    public boolean borrarTabla(TablaLocal tablaLocal) {
        return tablaRepository.deleteTable(tablaLocal);
    }

    public TablaLocal obtenerTablaPorId(int id){
        return tablaRepository.obtenerTablaPorId(id);
    }

    public int comprobarIdTablaMax() {
        return tablaRepository.comprobarIdTabla();
    }
}
