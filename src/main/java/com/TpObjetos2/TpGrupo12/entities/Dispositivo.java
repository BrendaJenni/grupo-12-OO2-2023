package com.TpObjetos2.TpGrupo12.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor

@Table(name="Dispositivo")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id;

    @Column(name="nombre")
    protected String nombre;

    @Column(name="activo")
    protected boolean activo;
    /*
    public Dispositivo(String nombre, boolean activo){
        this.nombre=nombre;
        this.activo=activo;
    }*/
    
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
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

    
    /*
    public Dispositivo(String nombre, boolean activo){
        this.nombre=nombre;
        this.activo=activo;
    }
    *//*
	public Dispositivo() {
		// TODO Auto-generated constructor stub
	}
*/
	
}
