package com.TpObjetos2.TpGrupo12.models;

public class DispositivoModel {
    protected int id_dispositivo;
    protected String nombre;
    protected boolean activo;

    public DispositivoModel(int id_dispositivo, String nombre, boolean activo) {
        this.id_dispositivo = id_dispositivo;
        this.nombre = nombre;
        this.activo = activo;
    }

    public DispositivoModel() {}

    public int getIdDispositivo() {
        return id_dispositivo;
    }

    public void setId(int id) {
        this.id_dispositivo = id;
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
        return "DispositivoModel [id=" + id_dispositivo + ", nombre=" + nombre + ", activo=" + activo + "]";
    }
    
}
