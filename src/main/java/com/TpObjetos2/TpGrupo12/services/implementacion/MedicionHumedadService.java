package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.TpObjetos2.TpGrupo12.entities.MedicionHumedad;
import com.TpObjetos2.TpGrupo12.models.MedicionHumedadModel;
import com.TpObjetos2.TpGrupo12.repositories.IMedicionHumedadRepository;
import com.TpObjetos2.TpGrupo12.services.IMedicionHumedadService;
@Service("medicionHumedadService")
public class MedicionHumedadService implements IMedicionHumedadService{

	@Autowired
    @Qualifier("medicionHumedadRepository")
    private IMedicionHumedadRepository medicionHumedadRepository;

    private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<MedicionHumedad> getAll() {
		return medicionHumedadRepository.findAll();
	}

	@Override
	public MedicionHumedadModel insertOrUpdate(MedicionHumedadModel medicionHumedadModel) {
		MedicionHumedad medicion = medicionHumedadRepository.save(modelMapper.map(medicionHumedadModel, MedicionHumedad.class));
        return modelMapper.map(medicion, MedicionHumedadModel.class);
	}

	@Override
	public boolean remove(int idMedicion) {
		try{
            medicionHumedadRepository.deleteById(idMedicion);
            return true;
        } catch(Exception e){
            return false;
        }
	}

	
}
