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
    
    //baja logica , se fija que el dispositivo no existe antes en la base de datos si ya existe, debemos cambiar el campo isactivo a false para que se deje demostrar en el front
    @Override
    public DispositivoModel insertOrUpdatealum(Dispositivo dispositivoModel) {
        if (dispositivoModel != null) {
            Dispositivo dispositivoExistente = dispositivoRepository.findById(dispositivoModel.getId());
            if (dispositivoExistente != null) {
                // Realizar la actualización del dispositivoExistente con los datos de dispositivoModel
                dispositivoExistente.setActivo(false);
               
                Dispositivo dispositivoActualizado = dispositivoRepository.save(dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
        
        // Si no se encontró el dispositivo existente o no se proporcionó un ID válido,
        // puedes implementar el código para manejar ese caso según tus necesidades.
        
        return null; // O lanzar una excepción, mostrar un mensaje de error, etc.
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

