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

    public boolean buscarDatos(int idEjercicio, int idTabla) {
        return tablaEjercicioRelacionInfoRepository.comprobarDatosExistentes(idEjercicio,idTabla);
    }

    public boolean insertarDatos(List<TablaEjercicioRelacionInfo> tablaEjercicioRelacionInfos) {
        return tablaEjercicioRelacionInfoRepository.addListaInfoEjercicio(tablaEjercicioRelacionInfos);
    }

    public List<TablaEjercicioRelacionInfo> getDatosPorIdEjercicioEIdTabla(int idEjercicio, int idTabla) {
        return tablaEjercicioRelacionInfoRepository.getDatosPorIdEjercicioTabla(idEjercicio,idTabla);
    }

    public boolean actualizarDatos(TablaEjercicioRelacionInfo tablaEjercicioRelacionInfo) {
         return tablaEjercicioRelacionInfoRepository.actualizarDatos(tablaEjercicioRelacionInfo);
    }

    public TablaEjercicioRelacionInfo getMaxDataPorIdEjercicioYTabla(int idTabla, int idEjercicio) {
        return tablaEjercicioRelacionInfoRepository.getMaxData(idTabla,idEjercicio);
    }

    public void borrarDatosPorTabla(int idTabla) {
        tablaEjercicioRelacionInfoRepository.borrarDatosPorTabla(idTabla);
    }

    public List<TablaEjercicioRelacionInfo> obtenerDatos() {
        return tablaEjercicioRelacionInfoRepository.getAllData();
    }
}
