package com.TpObjetos2.TpGrupo12.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SensorHumedadModel extends DispositivoModel{
	private boolean encendido;
	
	public SensorHumedadModel() {}
	
	public SensorHumedadModel (int id,String nombre,boolean activo,boolean encendido) {
		super(id,nombre,activo);
		this.encendido=encendido;
	}
	
	

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

	@Override
	public String toString() {
		return "SensorHumedadModel [id=" + id + ", nombre=" + nombre + ", activo=" + activo + ", encendido=" + encendido
				+ "]";
	}
	
	
}
