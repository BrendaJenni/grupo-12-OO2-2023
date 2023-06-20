package com.TpObjetos2.TpGrupo12.models;

import java.time.LocalDateTime;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;

public class EventoModel {
	
	private int idEvento;
	private String descripcion;
	private LocalDateTime fechaRegistro;
	private Dispositivo dispositivo;
	
	
	public EventoModel() {}
	
	public EventoModel(String descripcion, LocalDateTime fechaRegistro, Dispositivo dispositivo) {
		super();
		this.descripcion = descripcion;
		this.fechaRegistro = fechaRegistro;
		this.dispositivo = dispositivo;
	}

	public int getIdEvento() {
		return idEvento;
	}


	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		return "EventoModel [idEvento=" + idEvento + ", descripcion=" + descripcion + ", fechaRegistro=" + fechaRegistro
				+ ", dispositivo=" + dispositivo + "]";
	}
	

}
