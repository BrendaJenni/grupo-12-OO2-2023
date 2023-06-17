package com.TpObjetos2.TpGrupo12.entities;

import java.time.LocalDateTime;

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
@Table(name="MedicionAlumbrado")
public class MedicionAlumbrado extends Medicion{
	@Column(name="estadoActual")
	private boolean estadoActual;
	
	@Column(name="oscuridadActualPor")
	private double oscuridadActualPor;
	
}

