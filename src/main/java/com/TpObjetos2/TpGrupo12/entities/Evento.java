package com.TpObjetos2.TpGrupo12.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="Evento")

public class Evento {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idEvento;
	
	@Column(name="descripcion")
    private String descripcion;
	
	@Column(name="fechaRegistro")
    private LocalDateTime fechaRegistro;
	
	//@ManyToOne(fetch = FetchType.LAZY)
    // normalmente nullable es igual a false, en este caso se deja como true porque el ejemplo es simple
	@ManyToOne
    //@JoinColumn(name="idDispositivo", nullable=true)
    private Dispositivo dispositivo;
	

	public Evento() {}
	
	public Evento(String descripcion, LocalDateTime fechaRegistro, Dispositivo dispositivo) {
		this.descripcion = descripcion;
		this.fechaRegistro = fechaRegistro;
		this.dispositivo = dispositivo;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

}