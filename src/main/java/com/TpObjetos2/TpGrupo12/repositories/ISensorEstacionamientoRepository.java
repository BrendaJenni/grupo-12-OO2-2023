package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;

@Repository("estacionamientoRepository")
public interface ISensorEstacionamientoRepository extends JpaRepository<SensorEstacionamiento, Serializable>{
	public abstract SensorEstacionamiento findById(int id);
    public abstract List<Boolean> findByPlazas(boolean ocupado);
    public abstract List<SensorEstacionamiento> findAll();
}
