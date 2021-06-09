package com.example.tfg2.database.dataBaseOffline.infraestructure.tarea.tablas;

import com.example.tfg2.database.dataBaseOffline.infraestructure.repository.TablaEjercicioRelacionRepository;

import java.util.concurrent.Callable;

public class TareaComprobarEjercicioEnUso implements Callable {
    private int idEjercicio;
    public TareaComprobarEjercicioEnUso(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    @Override
    public Object call() throws Exception {
        try{
            if(TablaEjercicioRelacionRepository.mEjercicioTablaDao.buscarEjercicioEnUso(idEjercicio).isEmpty()){
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            return false;
        }

    }
}
