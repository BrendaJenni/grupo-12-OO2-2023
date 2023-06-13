package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;

@Repository("dispositivoRepository")
public interface IDispositivoRepository extends JpaRepository<Dispositivo, Serializable>{
	
}
