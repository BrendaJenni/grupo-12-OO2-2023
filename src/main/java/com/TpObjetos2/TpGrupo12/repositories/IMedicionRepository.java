package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TpObjetos2.TpGrupo12.entities.Medicion;

//ESTO ES EL DAO :D
@Repository("medicionRepository")
public interface IMedicionRepository extends JpaRepository<Medicion, Serializable> {

	// aca se agrega lo que queres buscar y con su nombre en el findBy SI NO ROMPE
    public abstract Medicion findByidMedicion(int idMedicion);
	
}
