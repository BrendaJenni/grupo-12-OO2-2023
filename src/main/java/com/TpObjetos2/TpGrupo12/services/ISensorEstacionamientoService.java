package com.TpObjetos2.TpGrupo12.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.MedicionEstacionamiento;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorEstacionamientoModel;

public interface ISensorEstacionamientoService {
	public List<SensorEstacionamiento> getAll();

    public SensorEstacionamientoModel insertOrUpdate(SensorEstacionamientoModel estacionamientoModel);
    
    public SensorEstacionamiento insertOrUpdate(SensorEstacionamiento estacionamientoModel);
    
    public boolean remove(int id);
    
    public SensorEstacionamiento findByid(int id);
    
    @Query("SELECT p.estadoLibre FROM Plazas p")
    public List<Boolean> getPlazas();

	//DispositivoModel agregarMedicion(Dispositivo dispositivoModel, LocalDateTime fecha, boolean estadoLibre);

	DispositivoModel insertOrUpdateEst(Dispositivo dispositivoModel, List<Boolean> plazas);
	
	public List<SensorEstacionamiento> traerstacionamientosActivos();
	
	public SensorEstacionamiento crearEstacionamientoConPlazas();

	DispositivoModel agregarEventos(Dispositivo dispositivoModel, Evento evento);
	
	public DispositivoModel agregarEventos(Dispositivo dispositivoModel,String descripcion, LocalDateTime fecha);

	DispositivoModel insertOrUpdateEst(Dispositivo dispositivoModel);

	//SensorEstacionamientoModel insertOrUpdateEst(SensorEstacionamientoModel dispositivoModel, List<Boolean> plazas);
	
	public List<Boolean> actualizarPlazas(SensorEstacionamientoModel estacionamiento);

	SensorEstacionamientoModel insertOrUpdateEst(SensorEstacionamientoModel dispositivoModel);

	DispositivoModel agregarMedicion(SensorEstacionamiento dispositivoModel, LocalDateTime fecha, boolean estadoLibre);

	DispositivoModel agregarEvento(SensorEstacionamiento dispositivoModel, LocalDateTime fecha, String descripcion);
	
	public DispositivoModel agregarEventoAutomatico(SensorEstacionamiento estacionamiento);
	
	public MedicionEstacionamiento traerUltimaMedicion();

	DispositivoModel agregarMedicion(SensorEstacionamientoModel dispositivoModel, LocalDateTime fecha,
		boolean estadoLibre);


}
