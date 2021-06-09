package com.example.tfg2.database.dataBaseOffline.infraestructure.repository;

import com.example.tfg2.database.dataBaseOffline.domain.TablaEjercicioRelacion;

import java.util.concurrent.Callable;

public class TareaBorrarDatosTablaPorId implements Callable {
    private int idTabla;
    public TareaBorrarDatosTablaPorId(int idTabla) {
        this.idTabla = idTabla;
    }

    @Override
    public Object call() throws Exception {
         try{
             TablaEjercicioRelacionRepository.mEjercicioTablaDao.deletePorIdTabla(idTabla);
            return true;
         }
         catch (Exception e){
             return false;
         }
    }
}
