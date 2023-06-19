package com.TpObjetos2.TpGrupo12.entities;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "Dispositivo")
@Inheritance(strategy = InheritanceType.JOINED)
public class Dispositivo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;  

    @Column(name="nombre")
    private String nombre;

    @Column(name="activo")

    protected boolean activo;
    
    @OneToMany(mappedBy="dispositivo")
    private List<Medicion> mediciones = new ArrayList<Medicion>();
    
    @OneToMany(mappedBy="dispositivo")
    private List<Evento> eventos = new ArrayList<Evento>();

    protected void setIdDispositivo(int id) {
        this.id = id;    
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

	public List<Medicion> getMediciones() {
		return mediciones;
	}

	public void setMediciones(List<Medicion> mediciones) {
		this.mediciones = mediciones;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Dispositivo(int id, String nombre, boolean activo) {
		this.mediciones = new ArrayList<Medicion>();
		this.eventos = new ArrayList<Evento>();
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	public Dispositivo(String nombre, boolean activo) {
		this.mediciones = new ArrayList<Medicion>();
		this.eventos = new ArrayList<Evento>();
		this.nombre = nombre;
		this.activo = activo;
	}

	public Dispositivo() {
	}
}

