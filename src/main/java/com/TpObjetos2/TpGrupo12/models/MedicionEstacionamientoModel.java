package com.TpObjetos2.TpGrupo12.models;

import java.time.LocalDateTime;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Medicion;

public class MedicionEstacionamientoModel extends Medicion{
	private boolean estadoLibre;

	public MedicionEstacionamientoModel(int idMedicion, Dispositivo dispositivo, LocalDateTime fechaRegistro,
			boolean estadoLibre) {
		super(idMedicion, dispositivo, fechaRegistro);
		this.estadoLibre = estadoLibre;
	}
	
	

	public MedicionEstacionamientoModel(Dispositivo dispositivo, LocalDateTime fechaRegistro, boolean estadoLibre) {
		super(dispositivo, fechaRegistro);
		this.estadoLibre = estadoLibre;
	}

	public MedicionEstacionamientoModel() {}



	public boolean isEstadoLibre() {
		return estadoLibre;
	}

	public void setEstadoLibre(boolean estadoLibre) {
		this.estadoLibre = estadoLibre;
	}
	
}
