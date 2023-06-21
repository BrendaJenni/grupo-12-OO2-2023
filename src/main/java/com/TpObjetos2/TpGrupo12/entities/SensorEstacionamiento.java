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
import lombok.NoArgsConstructor;
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
		this.plazas=plazas;
	}

	public SensorEstacionamiento() {
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

	public int getLibres() {
		return libres;
	}

	public void setLibres(int libres) {
		this.libres = libres;
	}

	@Override
	public String toString() {
		return "SensorEstacionamiento [plazas=" + plazas + ", tam=" + tam + ", libres=" + libres + "]";
	}
	
}
