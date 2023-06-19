package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;
import com.TpObjetos2.TpGrupo12.repositories.ISensorHumedadRepository;
import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;

@Service("sensorHumedadService")
public class SensorHumedadService implements ISensorHumedadService{

	@Autowired
    @Qualifier("sensorHumedadRepository")
    private ISensorHumedadRepository sensorHumedadRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
	public List<SensorHumedad> getAll() {
		return sensorHumedadRepository.findAll();
	}

	@Override
	public SensorHumedadModel insertOrUpdate(SensorHumedadModel sensorHumedadModel) {
		SensorHumedad sensorHumedad = sensorHumedadRepository.save(modelMapper.map(sensorHumedadModel, SensorHumedad.class));
        return modelMapper.map(sensorHumedad, SensorHumedadModel.class);
	}




	
}
