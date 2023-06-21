package com.TpObjetos2.TpGrupo12.services;

import java.time.LocalDateTime;
import java.util.List;


import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.models.EventoModel;

public interface IEventoService {

		public List<Evento> getAll();
		public List<Evento> getByFecha(LocalDateTime fecha);
		public List<Evento> getByEstado(boolean estado);
		public List<Evento> getByIdDispositivo(int id);
		
	    public EventoModel insertOrUpdate(Evento evento);
	    
	    public boolean remove(int idEvento);

		Evento findById(int id);

}
