package com.TpObjetos2.TpGrupo12.entities;

import java.time.LocalDate;
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
@Table(name="Evento")

public class Evento {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idEvento;
	
	@Column(name="descripcion")
    private String descripcion;
	
	@Column(name="fechaRegistro")
    private LocalDateTime fechaRegistro;
	
	@ManyToOne(fetch = FetchType.LAZY)
    // normalmente nullable es igual a false, en este caso se deja como true porque el ejemplo es simple
    @JoinColumn(name="idDispositivo", nullable=true)
    private Dispositivo dispositivo;

}

