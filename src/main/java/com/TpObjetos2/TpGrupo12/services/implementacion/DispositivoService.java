package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
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

   /* @Override
    public DispositivoModel insertOrUpdate(DispositivoModel dispositivoModel) {
        Dispositivo dispositivo = dispositivoRepository.save(modelMapper.map(dispositivoModel, Dispositivo.class));
        return modelMapper.map(dispositivo, DispositivoModel.class);
    }*/
    
    @Override
    public DispositivoModel insertOrUpdate(DispositivoModel dispositivoModel) {
        if (dispositivoModel.getId() != null) {
            Dispositivo dispositivoExistente = dispositivoRepository.findById(dispositivoModel.getId()).orElse(null);

            if (dispositivoExistente != null) {
                // Realizar la actualización del dispositivoExistente con los datos de dispositivoModel
                dispositivoExistente.setCampo1(dispositivoModel.getCampo1);
                dispositivoExistente.setCampo2(dispositivoModel.getCampo2);
                // ... Actualizar los demás campos según corresponda

                Dispositivo dispositivoActualizado = dispositivoRepository.save(dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }

        // Si no se encontró el dispositivo existente o no se proporcionó un ID válido,
        // puedes implementar el código para manejar ese caso según tus necesidades.

        return null; // O lanzar una excepción, mostrar un mensaje de error, etc.
    }
    
    @Override
    public DispositivoModel bajaLogica(DispositivoModel dispositivoModel) {
        dispositivoModel.setActivo(false); // Establecer el campo de dispositivo como false
        Dispositivo dispositivo = dispositivoRepository.save(modelMapper.map(dispositivoModel, Dispositivo.class));
        return modelMapper.map(dispositivo, DispositivoModel.class);
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
