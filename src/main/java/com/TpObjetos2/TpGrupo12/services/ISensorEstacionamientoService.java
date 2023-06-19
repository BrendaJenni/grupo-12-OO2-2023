package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.models.SensorEstacionamientoModel;

public interface ISensorEstacionamientoService {
	public List<SensorEstacionamiento> getAll();

    public SensorEstacionamientoModel insertOrUpdate(SensorEstacionamientoModel estacionamientoModel);
    
    public boolean remove(int id);
    
    public SensorEstacionamiento findByid(int id);
    
    @Query("SELECT from Plazas")
    public List<Boolean> getPlazas();

}
