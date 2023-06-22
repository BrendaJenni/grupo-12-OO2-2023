package com.TpObjetos2.TpGrupo12.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionRecolectorModel;
import com.TpObjetos2.TpGrupo12.models.SensorRecolectorModel;

public interface IRecolectorInteligenteService {
	public List<RecolectorInteligente> getAll();

    public SensorRecolectorModel insertOrUpdate(DispositivoModel dispositivoModel);

	public DispositivoModel agregarMedicion(Dispositivo dispositivoModel, LocalDateTime fecha, boolean estaLlenoAhora);
	
	public DispositivoModel agregarMedicion(MedicionRecolector medicion);

	public Dispositivo findByid(int id);

	public DispositivoModel agregarEventos(Dispositivo dispositivoModel, Evento evento);

	public DispositivoModel insertOrUpdateReco(Dispositivo sensorRecolectorModel);
}
