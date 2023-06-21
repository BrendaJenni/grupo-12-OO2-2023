package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.TpObjetos2.TpGrupo12.entities.Plaza;
import com.TpObjetos2.TpGrupo12.models.PlazaModel;
import com.TpObjetos2.TpGrupo12.repositories.IPlazaRepository;
import com.TpObjetos2.TpGrupo12.services.IPlazaService;

@Service("plazaService")
public class PlazaService implements IPlazaService{
	@Autowired
	@Qualifier("plazaRepository")
	private IPlazaRepository plazaRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<Plaza> getAll() {
		return plazaRepository.findAll();
	}

	public PlazaModel insertOrUpdate(PlazaModel plazaModel) {
		Plaza plaza = plazaRepository.save(modelMapper.map(plazaModel, Plaza.class));
		return modelMapper.map(plaza, PlazaModel.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			plazaRepository.deleteById(id);
			return true;
		} catch(Exception e) {
		return false;
		}
	}

	@Override
	public Plaza findById(int id) {
		return plazaRepository.findById(id);
	}
	
}
