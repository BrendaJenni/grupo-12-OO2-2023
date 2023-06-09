package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;

@Repository("sensorHumedadRepository")
public interface ISensorHumedadRepository extends JpaRepository<SensorHumedad, Serializable>{

	public abstract SensorHumedad findById(int id);
    public abstract SensorHumedad findByNombre(String nombre);
    //@Query("SELECT sh FROM Sensor_Humedad sh")
    public abstract List<SensorHumedad> findAll();
    
}
