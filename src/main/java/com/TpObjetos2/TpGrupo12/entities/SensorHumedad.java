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
@Table(name="SensorHumedad")
public class SensorHumedad extends Dispositivo{
	@Column(name="encedido")
	private boolean encedido;

	public boolean isEncedido() {
		return encedido;
	}

	public void setEncedido(boolean encedido) {
		this.encedido = encedido;
	}
}
