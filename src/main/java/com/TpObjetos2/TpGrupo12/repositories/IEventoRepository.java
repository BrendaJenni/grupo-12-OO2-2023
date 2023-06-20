package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TpObjetos2.TpGrupo12.entities.Evento;

@Repository("eventoRepository")
public interface IEventoRepository extends JpaRepository<Evento, Serializable>{
	    public abstract Evento findByDescripcion(String descripcion);
	    public abstract Evento findByidEvento(int idEvento);
	    
	    public abstract List<Evento> findByFechaRegistro(LocalDateTime fecha);
	    @Query("SELECT e FROM Evento e JOIN FETCH e.dispositivo d WHERE d.activo = :estado")
		public abstract List<Evento> findByEstado(boolean estado);
	    @Query("SELECT e FROM Evento e JOIN FETCH e.dispositivo d WHERE d.id = :id")
		public abstract List<Evento> findByIdDispositivo(int id);
}
