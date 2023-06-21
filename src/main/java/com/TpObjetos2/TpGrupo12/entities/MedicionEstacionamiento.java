package com.TpObjetos2.TpGrupo12.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@PrimaryKeyJoinColumn(name="id_medicion")
@Table(name="medicionEstacionamiento")
public class MedicionEstacionamiento extends Medicion{

    @Column(name="estadoLibre")
    private boolean estadoLibre;

    public MedicionEstacionamiento(boolean estadoLibre) {
        this.estadoLibre = estadoLibre;
    }

    public MedicionEstacionamiento() {}

    public MedicionEstacionamiento(Dispositivo dispositivo, LocalDateTime fechaRegistro, boolean estadoLibre) {
        super(dispositivo, fechaRegistro);
        this.estadoLibre = estadoLibre;
    }

    public boolean isEstadoLibre() {
        return estadoLibre;
    }

    public void setEstadoLibre(boolean estadoLibre) {
        this.estadoLibre = estadoLibre;
    }
}
