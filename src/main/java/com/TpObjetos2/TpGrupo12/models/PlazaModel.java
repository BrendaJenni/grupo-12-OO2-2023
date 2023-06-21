package com.TpObjetos2.TpGrupo12.models;

public class PlazaModel {
	private int id;
	private int nroPlaza;
	private boolean ocupado;
	
	public PlazaModel(int id, int nroPlaza, boolean ocupado) {
		this.id = id;
		this.nroPlaza = nroPlaza;
		this.ocupado = ocupado;
	}
	public PlazaModel() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNroPlaza() {
		return nroPlaza;
	}
	public void setNroPlaza(int nroPlaza) {
		this.nroPlaza = nroPlaza;
	}
	public boolean isOcupado() {
		return ocupado;
	}
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	@Override
	public String toString() {
		return "Plaza [id=" + id + ", nroPlaza=" + nroPlaza + ", ocupado=" + ocupado + "]";
	}
	
}
