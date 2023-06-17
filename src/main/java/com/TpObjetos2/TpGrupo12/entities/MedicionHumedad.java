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
	private double humedad;
	
	@Column(name="estadoCesped")
	private boolean estadoCesped;
}