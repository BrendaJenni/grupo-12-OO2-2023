package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TpObjetos2.TpGrupo12.entities.MedicionHumedad;

@Repository("medicionHumedadRepository")
public interface IMedicionHumedadRepository extends JpaRepository<MedicionHumedad, Serializable> {
	
    public abstract List<MedicionHumedad> findAll();
}
