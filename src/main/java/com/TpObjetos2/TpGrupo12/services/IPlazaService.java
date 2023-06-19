package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.Plaza;
import com.TpObjetos2.TpGrupo12.models.PlazaModel;

public interface IPlazaService{
	public List<Plaza> getAll();
	
	public PlazaModel insertOrUpdate(PlazaModel plazaModel);
	
	public boolean remove(int id);
	
	public Plaza findById(int id);
}
