package com.TpObjetos2.TpGrupo12.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="Medicion")

public class Medicion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idMedicion;

    //@ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    // normalmente nullable es igual a false, en este caso se deja como true porque el ejemplo es simple
    //@JoinColumn(name="idDispositivo", nullable=false)
    private Dispositivo dispositivo;

    @Column(name="fechaRegistro")
    private LocalDateTime fechaRegistro;

}