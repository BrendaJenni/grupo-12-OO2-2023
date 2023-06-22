package com.TpObjetos2.TpGrupo12.models;

import java.util.List;
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import java.util.ArrayList; // import the ArrayList class

public class SensorEstacionamientoModel extends Dispositivo{
	List<Boolean> plazas=new ArrayList<Boolean>();
	private int tam;
	private int libres;

	public SensorEstacionamientoModel(int id, String nombre, boolean activo, List<Boolean> plazas, int tam, int libres) {
		super(id, nombre, activo);
		this.plazas = plazas;
		this.tam=tam;
		this.libres=libres;
	}
	
	public SensorEstacionamientoModel(String nombre, boolean activo, List<Boolean> plazas, int tam, int libres) {
		super(nombre, activo);
		this.plazas=plazas;
		this.tam=tam;
		this.libres=libres;
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
	
	public int getLibres() {
		return libres;
	}

	public void setLibres(int libres) {
		this.libres = libres;
	}
	
	public int calcularPlazasLibres() {
		int cont = 0;
		for(int i=0;i<plazas.size();i++) {
			if(plazas.get(i)) {
				cont = cont+1;
			}
		}
		return cont;
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
