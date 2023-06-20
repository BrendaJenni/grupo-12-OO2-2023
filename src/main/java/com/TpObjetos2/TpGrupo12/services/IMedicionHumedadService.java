package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.MedicionHumedad;
import com.TpObjetos2.TpGrupo12.models.MedicionHumedadModel;

public interface IMedicionHumedadService {

	public List<MedicionHumedad> getAll();

    public MedicionHumedadModel insertOrUpdate(MedicionHumedadModel medicionHumedadModel);
    
    public boolean remove(int idMedicion);

}
