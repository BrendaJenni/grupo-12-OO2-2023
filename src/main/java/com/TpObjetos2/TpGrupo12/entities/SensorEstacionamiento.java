package com.TpObjetos2.TpGrupo12.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
//@PrimaryKeyJoinColumn(name = "id")
@Getter @Setter
//@Table(name="SensorEstacionamiento")
public class SensorEstacionamiento extends Dispositivo{
	@OneToMany(mappedBy="sensor")
	private Set<Plaza> plazas = new HashSet<>();
	
	public SensorEstacionamiento(int id, String nombre, boolean activo) {
		super(id, nombre, activo);
		this.plazas = plazas;
		Set<Plaza> plazas = new HashSet <>();
		//this.encendido=encendido;
	}

	public SensorEstacionamiento() {}
	
	//@Column(name="encendido")
	//private boolean encendido;

	public Set<Plaza> getPlazas() {
		return plazas;
	}

	public void setPlazas(Set<Plaza> plazas) {
		this.plazas = plazas;
	}
	/*
	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}*/

}
