package com.TpObjetos2.TpGrupo12.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="Dispositivo")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id_dispositivo;

    @Column(name="nombre")
    protected String nombre;

    @Column(name="activo")
    protected boolean activo;

	public int getId_dispositivo() {
		return id_dispositivo;
	}

	protected void setId_dispositivo(int id_dispositivo) {
		this.id_dispositivo = id_dispositivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
    
}
