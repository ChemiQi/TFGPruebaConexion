package com.example.tfg2.partesDelCuerpo.tareas;

import com.example.tfg2.partesDelCuerpo.clases.PartesDelCuerpo;
import com.example.tfg2.partesDelCuerpo.modelos.PdcDB;

import java.util.List;
import java.util.concurrent.Callable;

public class TaskGetBody implements Callable<List<PartesDelCuerpo>> {
    @Override
    public List<PartesDelCuerpo> call() throws Exception {
        List<PartesDelCuerpo> partes = PdcDB.obtenerPartes();
        return partes;
    }
}
