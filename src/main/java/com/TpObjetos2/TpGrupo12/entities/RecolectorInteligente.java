package com.TpObjetos2.TpGrupo12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Getter @Setter @NoArgsConstructor
@Table(name="RecolectorInteligente")
public class RecolectorInteligente extends Dispositivo{
	@Column(name="ubicacion")
	private String ubicacion;

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public RecolectorInteligente(String nombre, boolean activo, String ubicacion) {
		super(nombre, activo);
		this.ubicacion = ubicacion;
	}
}
