package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionEstacionamiento;
import com.TpObjetos2.TpGrupo12.models.MedicionEstacionamientoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionModel;
import com.TpObjetos2.TpGrupo12.repositories.IMedicionRepository;
import com.TpObjetos2.TpGrupo12.services.IMedicionService;

//ESTO ES EL ABM :D
@Service("medicionService")
public class MedicionService implements IMedicionService {
	
	@Autowired
    @Qualifier("medicionRepository")
    private IMedicionRepository medicionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Medicion> getAll() {
        return medicionRepository.findAll();
    }

    @Override
    public MedicionModel insertOrUpdate(MedicionModel medicionModel) {
        Medicion medicion = medicionRepository.save(modelMapper.map(medicionModel, Medicion.class));
        return modelMapper.map(medicion, MedicionModel.class);
    }
    
    @Override
    public MedicionEstacionamientoModel insertOrUpdate(MedicionEstacionamientoModel medicionModel) {
        MedicionEstacionamiento medicion = medicionRepository.save(modelMapper.map(medicionModel, MedicionEstacionamiento.class));
        return modelMapper.map(medicion, MedicionEstacionamientoModel.class);
    }

    @Override
    public boolean remove(int id) {
        try{
            medicionRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    // esto sirve pa buscar cosas podemos agragerlo a lo que querramos
    @Override
	public Medicion findById(int id) {
		return medicionRepository.findByidMedicion(id);
	}

	public List<MedicionEstacionamiento> traer() {
    	List<Medicion> mediciones = medicionRepository.findAll();
    	List<MedicionEstacionamiento> estacionamientos = new ArrayList<>();
    	for(int i=0;i<mediciones.size();i++) {
    		if(mediciones.get(i) instanceof MedicionEstacionamiento) {
    			estacionamientos.add((MedicionEstacionamiento) mediciones.get(i));
    		}
    	}
    	return estacionamientos;
    }
}
