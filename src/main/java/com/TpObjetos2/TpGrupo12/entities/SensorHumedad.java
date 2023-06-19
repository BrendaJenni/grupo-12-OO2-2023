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
@Table(name="SensorHumedad")
public class SensorHumedad extends Dispositivo{
	@Column(name="encedido")
	private boolean encendido;
	
	public SensorHumedad() {}
	
	public SensorHumedad(int id,String nombre, boolean activo, boolean encendido) {
		super(id,nombre,activo);
		this.encendido=encendido;
	}
	
	public boolean isEncendido() {
		return encendido;
	}
	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

}
