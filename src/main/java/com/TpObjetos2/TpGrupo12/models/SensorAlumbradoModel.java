package com.TpObjetos2.TpGrupo12.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SensorAlumbradoModel extends DispositivoModel {
	private String estacion;
	boolean encendido;
	double obscuridadPor;
	
	public SensorAlumbradoModel() {}
	public SensorAlumbradoModel (int id,String nombre, boolean activo, String estacion, boolean encendido,double obscuridadPor) {
		super(id,nombre,activo);
		
		this.estacion = estacion;
		this.encendido = encendido;
		this.obscuridadPor = obscuridadPor;
	}
	public String getEstacion() {
		return estacion;
	}
	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}
	public boolean isEncendido() {
		return encendido;
	}
	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}
	public double getObscuridadPor() {
		return obscuridadPor;
	}
	public void setObscuridadPor(double obscuridadPor) {
		this.obscuridadPor = obscuridadPor;
	}

}
