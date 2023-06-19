package com.TpObjetos2.TpGrupo12.models;

public class SensorRecolectorModel extends DispositivoModel {
	boolean estaLleno;
	
	public SensorRecolectorModel() {}
	public SensorRecolectorModel (int id,String nombre, boolean activo, boolean estaLleno) {
		super(id,nombre,activo);
		
		this.estaLleno = estaLleno;
	}
	public boolean isEstaLleno() {
		return estaLleno;
	}
	public void setEstaLleno(boolean estaLleno) {
		this.estaLleno = estaLleno;
	}
}