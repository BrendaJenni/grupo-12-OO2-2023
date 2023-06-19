package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.models.SensorEstacionamientoModel;
import com.TpObjetos2.TpGrupo12.repositories.ISensorEstacionamientoRepository;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;

	@Service("estacionamientoService")
	public class SensorEstacionamientoService implements ISensorEstacionamientoService{
		@Autowired
		@Qualifier("estacionamientoRepository")
		private ISensorEstacionamientoRepository estacionamientoRepository;
		
		private ModelMapper modelMapper = new ModelMapper();
		
		@Override
		public List<SensorEstacionamiento> getAll() {
			return estacionamientoRepository.findAll();
		}

		@Override
		public SensorEstacionamientoModel insertOrUpdate(SensorEstacionamientoModel estacionamientoModel) {
			SensorEstacionamiento estacionamiento = estacionamientoRepository.save(modelMapper.map(estacionamientoModel, SensorEstacionamiento.class));
			return modelMapper.map(estacionamiento, SensorEstacionamientoModel.class);
		}

		@Override
		public boolean remove(int id) {
			 try{
		            estacionamientoRepository.deleteById(id);
		            return true;
		        } catch(Exception e){
		            return false;
		        }
		    }

		@Override
		public SensorEstacionamiento findByid(int id) {
			return estacionamientoRepository.findById(id);
		}

		@Override
		public List<Boolean> getPlazas() {
			List<Boolean> plazas = estacionamientoRepository.findByPlazas(false);
			System.out.println(plazas);
			return plazas;
		}

	}

