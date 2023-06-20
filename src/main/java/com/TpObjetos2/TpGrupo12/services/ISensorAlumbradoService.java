package com.TpObjetos2.TpGrupo12.services;

import java.time.LocalDateTime;
import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;

public interface ISensorAlumbradoService {
	
	public List<SensorAlumbrado> getAll();

	public SensorAlumbradoModel insertOrUpdate(SensorAlumbradoModel sensorAlumbradoModel);
	
	 public DispositivoModel insertOrUpdatealum(Dispositivo dispositivoModel);
	 
	 public Dispositivo findByid(int id);

	public DispositivoModel agregarMedicion(Dispositivo dispositivoModel, LocalDateTime fecha, boolean estadoActual,
			double obscuridadActualPor);

	public DispositivoModel agregarEventos(Dispositivo dispositivoModel, Evento evento);
	

}
