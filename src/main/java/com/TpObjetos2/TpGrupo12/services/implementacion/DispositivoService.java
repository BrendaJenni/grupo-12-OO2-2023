package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;

//ESTO ES EL ABM :D
@Service("dispositivoService")
public class DispositivoService implements IDispositivoService{
    @Autowired
    @Qualifier("dispositivoRepository")
    private IDispositivoRepository dispositivoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Dispositivo> getAll() {
        return dispositivoRepository.findAll();
    }

    @Override
    public DispositivoModel insertOrUpdate(DispositivoModel dispositivoModel) {
        Dispositivo dispositivo = dispositivoRepository.save(modelMapper.map(dispositivoModel, Dispositivo.class));
        return modelMapper.map(dispositivo, DispositivoModel.class);
    }
   
    @Override
    public Dispositivo getById(int id) {
        Dispositivo dispositivoOptional = dispositivoRepository.findById(id);
        return dispositivoOptional;
    }
    @Override
    public boolean remove(int id) {
        try{
            dispositivoRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    @Override
	public Dispositivo findByid(int id) {
		return dispositivoRepository.findById(id);
	}
    
    
   

}
/*
	@Override
	public Dispositivo findById(int id) {
		return dispositivoRepository.findById(id);
	}

	@Override
	public Dispositivo findByName(String name) {
		return modelMapper.map(dispositivoRepository.findByName(name), Dispositivo.class);
	}*/
