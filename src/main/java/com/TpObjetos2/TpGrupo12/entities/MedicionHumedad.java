package com.TpObjetos2.TpGrupo12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id_medicion")
@Getter @Setter @NoArgsConstructor
@Table(name="MedicionHumedad")
public class MedicionHumedad extends Medicion{
	@Column(name="humedad")
	private int humedad;
	
	@Column(name="estadoCesped")
	private boolean estadoCesped;
	
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