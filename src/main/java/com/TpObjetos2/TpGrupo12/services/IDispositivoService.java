package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;

public interface IDispositivoService {
	public List<Dispositivo> getAll();
	
	public Dispositivo insertOrUpdate(Dispositivo dispo);
	
	public boolean remove(int id);
	
	/*public Dispositivo findById(int id);
	
	public Dispositivo findByName(String name);*/
}
