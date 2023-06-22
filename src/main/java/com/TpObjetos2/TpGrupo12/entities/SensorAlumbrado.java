package com.TpObjetos2.TpGrupo12.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Getter @Setter
@Table(name="SensorAlumbrado")
public class SensorAlumbrado extends Dispositivo{
	@Column(name="estacion")
	private String estacion;
	
	@Column(name="encendido")
	private boolean encendido;
	
	@Column(name="obscuridadPor")
	private double obscuridadPor;

	public String getEstacion() {
		return estacion;
	}

	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

	public double getObscuridadPor() {
		return obscuridadPor;
	}

	public void setObscuridadPor(double obscuridadPor) {
		this.obscuridadPor = obscuridadPor;
	}
	public SensorAlumbrado(int id, String nombre, boolean activo, String estacion, boolean encendido,
			double obscuridadPor) {
		super(id, nombre, activo);
		this.estacion = estacion;
		this.encendido = encendido;
		this.obscuridadPor = obscuridadPor;
	}

	public SensorAlumbrado(String nombre, boolean activo, String estacion, boolean encendido, double obscuridadPor) {
		super(nombre, activo);
		this.estacion = estacion;
		this.encendido = encendido;
		this.obscuridadPor = obscuridadPor;
	}

	public SensorAlumbrado() {}
}

