package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.models.EventoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionModel;
import com.TpObjetos2.TpGrupo12.repositories.IEventoRepository;
import com.TpObjetos2.TpGrupo12.repositories.IMedicionRepository;
import com.TpObjetos2.TpGrupo12.services.IEventoService;
import com.TpObjetos2.TpGrupo12.services.IMedicionService;

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

}
