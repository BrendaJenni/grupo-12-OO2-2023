package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;

@Service("personService")
public class DispositivoService implements IDispositivoService{
	
	@Autowired
	@Qualifier("personRepository")
	private IDispositivoRepository dispositivoRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<Dispositivo> getAll() {
		return dispositivoRepository.findAll();
	}
	
	@Override
	public Dispositivo insertOrUpdate(Dispositivo dispo) {
		Dispositivo dispoNew = dispositivoRepository.save(dispo);
		return modelMapper.map(dispoNew, Dispositivo.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			dispositivoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
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
}
