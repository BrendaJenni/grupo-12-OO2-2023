package com.TpObjetos2.TpGrupo12.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MedicionRecolectorModel extends DispositivoModel {
	public MedicionRecolectorModel() {}
	
	public MedicionRecolectorModel (int id,String nombre,boolean activo) {
		super(id,nombre,activo);
	
	}
}
