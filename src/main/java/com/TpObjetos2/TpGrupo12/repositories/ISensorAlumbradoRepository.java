package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;


@Repository("sensorAlumbradoRepository")
public interface ISensorAlumbradoRepository extends JpaRepository<SensorAlumbrado, Serializable> {

	
	public abstract SensorAlumbrado findById(int id);
    public abstract SensorAlumbrado findByNombre(String nombre);
    public abstract List<SensorAlumbrado> findAll();
	

	
}
