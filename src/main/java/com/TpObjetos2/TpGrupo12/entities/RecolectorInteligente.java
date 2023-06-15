package com.TpObjetos2.TpGrupo12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id_dispositivo")
@Getter @Setter @NoArgsConstructor
@Table(name="RecolectorInteligente")
public class RecolectorInteligente extends Dispositivo{
	@Column(name="estadoLlenoAhora")
	private boolean estadoLlenoAhora;
	
	@Column(name="ubicacion")
	private String ubicacion;
}
