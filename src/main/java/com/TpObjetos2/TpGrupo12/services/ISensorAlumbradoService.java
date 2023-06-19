package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;

public interface ISensorAlumbradoService {
	
	public List<SensorAlumbrado> getAll();

    public SensorAlumbradoModel insertOrUpdate(DispositivoModel dispositivoModel);

	public SensorAlumbradoModel insertOrUpdate(SensorAlumbradoModel sensorAlumbradoModel);
	
	 public DispositivoModel insertOrUpdatealum(Dispositivo dispositivoModel);
	 
	 public Dispositivo findByid(int id);

}
