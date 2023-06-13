package com.TpObjetos2.TpGrupo12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="dispositivo")
public class Dispositivo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	@Column(name="nombre")
	protected String nombre;
	
	@Column(name="activo")
	protected boolean activo;
	
}
