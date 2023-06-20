package com.TpObjetos2.TpGrupo12.services;

import java.time.LocalDateTime;
import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;

public interface ISensorHumedadService {
	public List<SensorHumedad> getAll();
	public DispositivoModel agregarMedicion(Dispositivo dispositivoModel,LocalDateTime fecha,double humedad,boolean estadoCesped);
	public Dispositivo findByid(int id);
    public SensorHumedadModel insertOrUpdate(SensorHumedad sensorHumedad);
    public DispositivoModel actualizar(Dispositivo dispositivo);
    public DispositivoModel agregarEventos(Dispositivo dispositivoModel,Evento evento);
}
