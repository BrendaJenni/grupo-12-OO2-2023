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
@Table(name="MedicionAlumbrado")
public class MedicionAlumbrado extends Medicion{
	@Column(name="estadoActual")
	private boolean estadoActual;
	
	@Column(name="oscuridadActualPor")
	private double oscuridadActualPor;

	public boolean isEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(boolean estadoActual) {
		this.estadoActual = estadoActual;
	}

	public double getOscuridadActualPor() {
		return oscuridadActualPor;
	}

	public void setOscuridadActualPor(double oscuridadActualPor) {
		this.oscuridadActualPor = oscuridadActualPor;
	}

	public MedicionAlumbrado(int idMedicion, Dispositivo dispositivo, LocalDateTime fechaRegistro, boolean estadoActual,
			double oscuridadActualPor) {
		super(idMedicion, dispositivo, fechaRegistro);
		this.estadoActual = estadoActual;
		this.oscuridadActualPor = oscuridadActualPor;
	}

	public MedicionAlumbrado(Dispositivo dispositivo, LocalDateTime fechaRegistro, boolean estadoActual,
			double oscuridadActualPor) {
		super(dispositivo, fechaRegistro);
		this.estadoActual = estadoActual;
		this.oscuridadActualPor = oscuridadActualPor;
	}

	public MedicionAlumbrado() {
	}
	
}

