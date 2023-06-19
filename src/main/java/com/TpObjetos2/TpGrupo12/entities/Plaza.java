package com.TpObjetos2.TpGrupo12.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
@Entity
@Getter @Setter  @NoArgsConstructor
@Table(name="plaza")
public class Plaza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ocupado")
	private boolean ocupado;
	
	@Column(name="nroPlaza")
	private int nroPlaza;
	
	@ManyToOne
	@JoinColumn(name="sensor_id")
	private SensorEstacionamiento sensor;
	
	public Plaza(int id, boolean ocupado, int nroPlaza) {
		this.id = id;
		this.ocupado = ocupado;
		this.nroPlaza = nroPlaza;
	}

	public Plaza(boolean ocupado, int nroPlaza) {
		this.ocupado = ocupado;
		this.nroPlaza = nroPlaza;
	}
	public Plaza() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public int getNroPlaza() {
		return nroPlaza;
	}

	public void setNroPlaza(int nroPlaza) {
		this.nroPlaza = nroPlaza;
	}

	public SensorEstacionamiento getSensor() {
		return sensor;
	}

	public void setSensor(SensorEstacionamiento sensor) {
		this.sensor = sensor;
	}
	
}*/
