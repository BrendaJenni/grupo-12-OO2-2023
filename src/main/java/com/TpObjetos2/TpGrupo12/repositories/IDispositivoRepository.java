package com.TpObjetos2.TpGrupo12.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;

import java.util.List; 

//ESTO ES EL DAO :D
@Repository("dispositivoRepository")
public interface IDispositivoRepository extends JpaRepository<Dispositivo, Serializable>{
	public abstract Dispositivo findById(int id);
    public abstract Dispositivo findByNombre(String nombre);
    public abstract List<Dispositivo> findAll();
	Dispositivo getById(int id);
      
}
