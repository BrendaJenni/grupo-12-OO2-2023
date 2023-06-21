package com.TpObjetos2.TpGrupo12.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class DispositivoModel {
    protected int id;
    protected String nombre;
    protected boolean activo;
    protected boolean encendido;


    public DispositivoModel(int id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }
    
    public DispositivoModel(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    public DispositivoModel() {}

    public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

    public int getId() {
		return id;
	}

	public void setId(int id) {
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
    
    

    @Override
    public String toString() {
        return "DispositivoModel [id=" + id + ", nombre=" + nombre + ", activo=" + activo + "]";
    }

}
