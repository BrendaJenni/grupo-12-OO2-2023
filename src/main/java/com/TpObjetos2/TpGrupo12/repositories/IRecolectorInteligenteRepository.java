package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;

@Repository("recolectorInteligenteRepository")
public interface IRecolectorInteligenteRepository extends JpaRepository<RecolectorInteligente, Serializable>{

	//public abstract MedicionRecolector findById(int id);
    public abstract RecolectorInteligente findByUbicacion(String ubicacion);
    public abstract List<RecolectorInteligente> findAll();
}
