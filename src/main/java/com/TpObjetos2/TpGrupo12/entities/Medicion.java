package com.TpObjetos2.TpGrupo12.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="Medicion")

public class Medicion {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idMedicion;
	
	@ManyToOne(fetch = FetchType.LAZY)
    // normalmente nullable es igual a false, en este caso se deja como true porque el ejemplo es simple
    @JoinColumn(name="idDispositivo", nullable=true)
    private Dispositivo dispositivo;
	
	@Column(name="fechaRegistro")
    private LocalDateTime fechaRegistro;

	public Medicion(LocalDateTime fechaRegistro) {
		super();
		this.fechaRegistro = fechaRegistro;
	}

	public Medicion() {
		super();
	}

	public int getIdMedicion() {
		return idMedicion;
	}

	protected void setIdMedicion(int idMedicion) {
		this.idMedicion = idMedicion;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	

}
