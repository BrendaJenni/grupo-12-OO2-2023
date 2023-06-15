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
    
}
