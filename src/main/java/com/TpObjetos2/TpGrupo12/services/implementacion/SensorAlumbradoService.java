package com.TpObjetos2.TpGrupo12.services.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.repositories.ISensorAlumbradoRepository;
import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;

@Service("sensorAlumbradoService")
public class SensorAlumbradoService implements ISensorAlumbradoService {


    @Autowired
    @Qualifier("sensorAlumbradoRepository")
    private ISensorAlumbradoRepository sensorAlumbradoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<SensorAlumbrado> getAll() {
        return sensorAlumbradoRepository.findAll();
    }

    @Override
    public SensorAlumbradoModel insertOrUpdate(SensorAlumbradoModel sensorAlumbradoModel) {
        SensorAlumbrado sensorAlumbrado = new SensorAlumbrado();
        sensorAlumbrado.setId(sensorAlumbradoModel.getId());
        sensorAlumbrado.setNombre(sensorAlumbradoModel.getNombre());
        sensorAlumbrado.setActivo(sensorAlumbradoModel.isActivo());
        sensorAlumbrado.setEstacion(sensorAlumbradoModel.getEstacion());
        sensorAlumbrado.setEncendido(sensorAlumbradoModel.isEncendido());
        sensorAlumbrado.setObscuridadPor(sensorAlumbradoModel.getObscuridadPor());

        sensorAlumbrado = sensorAlumbradoRepository.save(sensorAlumbrado);
        return modelMapper.map(sensorAlumbrado, SensorAlumbradoModel.class);
    }

    @Override
    public SensorAlumbradoModel insertOrUpdate(DispositivoModel dispositivoModel) {
        // TODO Auto-generated method stub
        return null;
    }
}