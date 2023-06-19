package com.TpObjetos2.TpGrupo12.services;

import java.util.List;

import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionRecolectorModel;

public interface IRecolectorInteligenteService {
	public List<RecolectorInteligente> getAll();

    public MedicionRecolectorModel insertOrUpdate(DispositivoModel dispositivoModel);
}
