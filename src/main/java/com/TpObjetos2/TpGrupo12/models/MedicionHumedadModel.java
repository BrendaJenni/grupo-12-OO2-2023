package com.TpObjetos2.TpGrupo12.models;

import java.time.LocalDateTime;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;


public class MedicionHumedadModel extends MedicionModel{

	private int humedad;
	private boolean estadoCesped;
	
	public MedicionHumedadModel() {}

	public MedicionHumedadModel(int idMedicion, LocalDateTime fechaRegistro, Dispositivo dispositivo,int humedad, boolean estadoCesped) {
		super( idMedicion, fechaRegistro, dispositivo);
		this.humedad = humedad;
		this.estadoCesped = estadoCesped;
	}

	public int getHumedad() {
		return humedad;
	}

	public void setHumedad(int humedad) {
		this.humedad = humedad;
	}

	public boolean isEstadoCesped() {
		return estadoCesped;
	}

	public void setEstadoCesped(boolean estadoCesped) {
		this.estadoCesped = estadoCesped;
	}
	
}
