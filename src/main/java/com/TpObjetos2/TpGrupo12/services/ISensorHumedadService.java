package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;

public interface ISensorHumedadService {
	public List<SensorHumedad> getAll();

    public SensorHumedadModel insertOrUpdate(SensorHumedadModel sensorHumedadModel);
}
