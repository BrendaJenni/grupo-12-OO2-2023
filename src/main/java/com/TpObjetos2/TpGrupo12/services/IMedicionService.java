package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionEstacionamiento;
import com.TpObjetos2.TpGrupo12.models.EventoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionEstacionamientoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionModel;

public interface IMedicionService {

	public List<Medicion> getAll();

    public MedicionModel insertOrUpdate(MedicionModel medicionModel);
    
    public boolean remove(int idMedicion);

	Medicion findById(int id);
	
	public List<MedicionEstacionamiento> traer();

	MedicionEstacionamientoModel insertOrUpdate(MedicionEstacionamientoModel medicionModel);
	
	
}
