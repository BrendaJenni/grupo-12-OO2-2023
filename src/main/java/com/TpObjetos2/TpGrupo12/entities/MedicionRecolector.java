package com.TpObjetos2.TpGrupo12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id_recolector")
@Getter @Setter @NoArgsConstructor
@Table(name="MedicionRecolector")
public class MedicionRecolector extends Medicion{
	@Column(name="estadoLlenoAhora")
	private boolean estadoLlenoAhora;
	
	@Column(name="oscuridadActualPor")
	private double oscuridadActualPor;
	
}