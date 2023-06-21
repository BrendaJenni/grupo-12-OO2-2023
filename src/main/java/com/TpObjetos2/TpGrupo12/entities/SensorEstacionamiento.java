package com.TpObjetos2.TpGrupo12.entities;
import java.util.List;
import java.util.ArrayList; // 

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
@PrimaryKeyJoinColumn(name = "id")
@Table(name="estacionamiento")
public class SensorEstacionamiento extends Dispositivo{

	@ElementCollection(targetClass = Boolean.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "plazas", joinColumns = @JoinColumn(name = "dispositivo_id"))
	@Column(name = "booleano")
	private List<Boolean> plazas = new ArrayList<Boolean>();
	
	@Column (name="tam")
	private int tam;
	
	@Column(name="libres")
	private int libres;
	
	public SensorEstacionamiento(int id, String nombre, boolean activo, List<Boolean> plazas,
			int tam, int libres) {
		super(id, nombre, activo);
		this.plazas = plazas;
		this.tam = tam;
		this.libres=libres;
	}
	
	public SensorEstacionamiento(String nombre, boolean activo, int libres, int tam, List<Boolean> plazas) {
		super(nombre, activo);
		this.libres=libres;
		this.tam=tam;
		setPlazas(plazas);
	}

	public SensorEstacionamiento() {
	}

	public List<Boolean> getPlazas() {
		return plazas;
	}

	public void setPlazas(List<Boolean> plazas) {
		this.plazas=inicializarPlazas();
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public List<Boolean> inicializarPlazas() {
		for(int i=0;i<this.tam;i++) {
			this.plazas.add(false);
		}
		return this.plazas;
	}

	public int getLibres() {
		return libres;
	}

	public void setLibres(int libres) {
		int cont = 0;
		for(int i=0;i<this.plazas.size();i++){
			if(this.plazas.get(i)) {
				cont = cont+1;
			}
		}
		this.libres=cont;
	}
	
}

