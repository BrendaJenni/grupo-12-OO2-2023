package com.TpObjetos2.TpGrupo12.models;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;

public class MedicionModel {
	
	private int idMedicion;
	private Dispositivo dispositivo;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime fechaRegistro;
	
	public MedicionModel(int idMedicion, LocalDateTime fechaRegistro, Dispositivo dispositivo) {
		super();
		this.idMedicion = idMedicion;
		this.fechaRegistro = fechaRegistro;
		this.dispositivo = dispositivo;
	}

	public MedicionModel() {}
	public int getIdMedicion() {
		return idMedicion;
	}

	public void setIdMedicion(int idMedicion) {
		this.idMedicion = idMedicion;
	}


	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	public Dispositivo getDispositivo() {
		return dispositivo;
	}


	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}


	@Override
	public String toString() {
		return "MedicionModel [idMedicion=" + idMedicion + ", fechaRegistro="
				+ fechaRegistro + ", dispositivo=" + dispositivo + "]";
	}
	
	
	
	

}
