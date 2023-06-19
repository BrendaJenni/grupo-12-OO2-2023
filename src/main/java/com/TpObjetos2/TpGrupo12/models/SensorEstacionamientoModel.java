package com.TpObjetos2.TpGrupo12.models;

import java.util.List;
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import java.util.ArrayList; // import the ArrayList class

public class SensorEstacionamientoModel extends Dispositivo{
	List<Boolean> plazas=new ArrayList<Boolean>();
	private int tam;

	public SensorEstacionamientoModel(int id, String nombre, boolean activo, List<Boolean> plazas, int tam) {
		super(id, nombre, activo);
		this.plazas = plazas;
		this.tam=tam;
	}
	
	public SensorEstacionamientoModel(int id, String nombre, boolean activo) {
		super(id, nombre, activo);
	}

	public SensorEstacionamientoModel() {
		super();
	}

	public List<Boolean> getPlazas() {
		return plazas;
	}

	public void setPlazas(List<Boolean> plazas) {
		this.plazas = plazas;
	}
	

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}
	
	public void inicializarPlazas() {
		for(int i=0;i<tam;i++) {
			plazas.add(false);
		}
	}

	@Override
	public String toString() {
		return "SensorEstacionamientoModel [plazas=" + plazas + ", tam=" + tam + "]";
	}
	
}
