package com.TpObjetos2.TpGrupo12.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class MedicionRecolectorModel extends DispositivoModel {
	boolean estaLlenoAhora;
	
	public MedicionRecolectorModel() {}
	
	public MedicionRecolectorModel (int id,String nombre,boolean activo, boolean estaLlenoAhora) {
		super(id,nombre,activo);
		this.estaLlenoAhora = estaLlenoAhora;
	}
}
