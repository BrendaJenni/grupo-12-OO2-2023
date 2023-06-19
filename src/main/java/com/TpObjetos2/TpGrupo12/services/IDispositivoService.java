package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;

public interface IDispositivoService {
    public List<Dispositivo> getAll();

    public DispositivoModel insertOrUpdate(DispositivoModel dispositivoModel);
    
    public boolean remove(int id);
    
    public Dispositivo findByid(int id);

	DispositivoModel bajaLogica(DispositivoModel dispositivoModel);
    
}
