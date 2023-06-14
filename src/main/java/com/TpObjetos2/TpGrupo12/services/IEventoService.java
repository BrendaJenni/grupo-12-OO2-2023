package com.TpObjetos2.TpGrupo12.services;

import java.util.List;


import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.models.EventoModel;

public interface IEventoService {

		public List<Evento> getAll();

	    public EventoModel insertOrUpdate(EventoModel eventoModel);
	    
	    public boolean remove(int idEvento);

		Evento findById(int id);
	
}
