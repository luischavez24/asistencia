package com.guis.asistencia.converter;

import org.springframework.stereotype.Component;

import com.guis.asistencia.entities.Asistencia;
import com.guis.asistencia.entities.AsistenciaPK;
import com.guis.asistencia.models.AsistenciaModel;

@Component("asistenciaConverter")
public class AsistenciaConverter {

	public Asistencia toEntity(AsistenciaModel model) {
		Asistencia entity = new Asistencia();
		
		AsistenciaPK pk = new AsistenciaPK(model.getNroSesion(), model.getCodigo());
		entity.setAsistenciaPK(pk);
		entity.setTHora(model.getHoraDeLlegada());
		
		return entity;
	}
	
	public AsistenciaModel toModel(Asistencia entity) {
		AsistenciaModel model = new AsistenciaModel();
		model.setCodigo(entity.getAsistenciaPK().getAlumnovCodigoA());
		model.setNroSesion(entity.getAsistenciaPK().getSesionnIdSesion());
		model.setHoraDeLlegada(entity.getTHora());
		model.setAlumno(entity.getAlumno());
		return model;
	}
}
