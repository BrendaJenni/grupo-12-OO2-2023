package com.TpObjetos2.TpGrupo12.models;

import java.time.LocalDateTime;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;


public class MedicionHumedadModel extends MedicionModel{

	private double humedad;
	private boolean estadoCesped;
	
	public MedicionHumedadModel() {}

	public MedicionHumedadModel(int idMedicion, LocalDateTime fechaRegistro, Dispositivo dispositivo,double humedad, boolean estadoCesped) {
		super( idMedicion, fechaRegistro, dispositivo);
		this.humedad = humedad;
		this.estadoCesped = estadoCesped;
	}

	public double getHumedad() {
		return humedad;
	}

	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}

	public boolean isEstadoCesped() {
		return estadoCesped;
	}

	public void setEstadoCesped(boolean estadoCesped) {
		this.estadoCesped = estadoCesped;
	}
	
}
