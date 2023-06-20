package com.TpObjetos2.TpGrupo12.models;

public class SensorRecolectorModel extends DispositivoModel {
	String ubicacion;
	
	public SensorRecolectorModel() {}
	
	public SensorRecolectorModel (String nombre, boolean activo, String ubicacion) {
		super(nombre,activo);
		this.ubicacion = ubicacion;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}