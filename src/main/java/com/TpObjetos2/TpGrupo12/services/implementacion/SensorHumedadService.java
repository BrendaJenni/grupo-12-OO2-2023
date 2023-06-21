package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionHumedad;
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
	public SensorHumedadModel insertOrUpdate(SensorHumedad sensorHumedad) {
		SensorHumedad sensor = sensorHumedadRepository.save(sensorHumedad);
        return modelMapper.map(sensor, SensorHumedadModel.class);
	}

	@Override
	public Dispositivo findByid(int id) {
		return sensorHumedadRepository.findById(id);
	}

	@Override
    public DispositivoModel actualizar(Dispositivo dispositivo) {
        if (dispositivo != null) {
            Dispositivo dispositivoExistente = sensorHumedadRepository.findById(dispositivo.getId());
            if (dispositivoExistente != null) {
                // actualizo es status del dispositivo
                dispositivoExistente.setActivo(false);

                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = sensorHumedadRepository.save((SensorHumedad)dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
    }

	@Override
    public DispositivoModel agregarMedicion(Dispositivo dispositivo,LocalDateTime fecha,int humedad, boolean estadoCesped) {
        if (dispositivo != null) {
            Dispositivo dispositivoExistente = sensorHumedadRepository.findById(dispositivo.getId());
            if (dispositivoExistente != null) {
                List<Medicion> mediciones = dispositivoExistente.getMediciones();
                
                MedicionHumedad medicion = new MedicionHumedad();
                
                medicion.setHumedad(humedad);
                medicion.setEstadoCesped(estadoCesped);
                medicion.setFechaRegistro(fecha);
                medicion.setDispositivo(dispositivoExistente);
                
                mediciones.add(medicion);
                
                dispositivoExistente.setMediciones(mediciones);
               
                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = sensorHumedadRepository.save((SensorHumedad)dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
    }
	
	@Override
    public DispositivoModel agregarEventos(Dispositivo dispositivo,Evento evento) {
        if (dispositivo != null) {
            Dispositivo dispositivoExistente = sensorHumedadRepository.findById(dispositivo.getId());
            if (dispositivoExistente != null) {
                List<Evento> eventos = dispositivoExistente.getEventos();
                Evento eventosN = new Evento();

                eventosN.setDescripcion(evento.getDescripcion());
                eventosN.setFechaRegistro(evento.getFechaRegistro());
                eventosN.setDispositivo(dispositivoExistente);


                eventos.add(eventosN);

                dispositivoExistente.setEventos(eventos);;
                
                //genero este cambio para poder variase el encendido del primer mostrar y que se vea mejor en el front
                SensorHumedad setActivo = (SensorHumedad)dispositivoExistente;

                if(evento.getDescripcion().equals("Regar cesped")) {
                	
                	setActivo.setEncendido(true);;
                	
                }else {
                	
                	setActivo.setEncendido(false);;
                	
                }

                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = sensorHumedadRepository.save(setActivo);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
}
        
	
	public MedicionHumedad traerUltimaMedicion() {
		MedicionHumedad medicion; 
		
		List<SensorHumedad> dispositivos = getAll();
        int id = (int) (Math.random()*dispositivos.size());
        
        //asigno el dispositivo a una variable dispositivo para poder consumir las mediciones
        Dispositivo buscar = findByid(dispositivos.get(id).getId());
        List<Medicion> mediciones = buscar.getMediciones();
        
        // si las mediciones estan vacias creamos la primera y devolvemos null lo usamos como flag para cuando llamemos a la funcion
        if(mediciones.size() == 0)  
        {
        	medicion = null;
        	// lo mandamos a generar la primera medicion del dispositivo con la luz apagada en la proxima variable cuando se vea nuevamente la hora y el porcentaje de luz se decidira si prenderla
        	agregarMedicion(buscar, LocalDateTime.now(), 20,false);
        }else {
        	 //dejo este campo solo para agregar 10 mediciones en cada dispositivo y que no rompa
        	 if(mediciones.size() >= 10 || buscar.isActivo() == false) {
        	     //si entra aca ya no debemos agregar mediciones a este dispositivo
        	     medicion= null;
        	 }else {
        	    //en caso de que ya tenga mediciones se va a devolver la ultima generada
        	    medicion = (MedicionHumedad) mediciones.get(mediciones.size()-1);
        	 }

        } 
        return medicion;
    }
}
