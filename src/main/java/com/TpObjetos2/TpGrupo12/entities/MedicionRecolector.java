package com.TpObjetos2.TpGrupo12.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id_medicion")
@Getter @Setter
@Table(name="MedicionRecolector")
public class MedicionRecolector extends Medicion{
	@Column(name="estaLlenoAhora")
	private boolean estaLlenoAhora;

	public boolean isEstaLlenoAhora() {
		return estaLlenoAhora;
	}

	public void setEstaLlenoAhora(boolean estaLlenoAhora) {
		this.estaLlenoAhora = estaLlenoAhora;
	}

	public MedicionRecolector(Dispositivo dispositivo, LocalDateTime fechaRegistro, boolean estaLlenoAhora) {
		super(dispositivo, fechaRegistro);
		this.estaLlenoAhora = estaLlenoAhora;
	}
	
	public MedicionRecolector() {}
}