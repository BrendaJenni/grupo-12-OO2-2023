package com.TpObjetos2.TpGrupo12.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SensorHumedadModel extends DispositivoModel{


	public SensorHumedadModel() {}
	public SensorHumedadModel (int id,String nombre,boolean activo,boolean encendido) {
		super(id,nombre,activo, encendido);
	
	}
	
}
