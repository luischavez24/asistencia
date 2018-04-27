package com.guis.asistencia.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.guis.asistencia.converter.AsistenciaConverter;
import com.guis.asistencia.entities.Alumno;
import com.guis.asistencia.entities.Asistencia;
import com.guis.asistencia.entities.AsistenciaPK;
import com.guis.asistencia.models.AsistenciaModel;
import com.guis.asistencia.repository.AlumnosRepository;
import com.guis.asistencia.repository.AsistenciaRepository;
import com.guis.asistencia.services.AsistenciaService;

@Service("asistenciaService")

public class AsistenciaServiceImpl implements AsistenciaService{
	
	private static final Log LOG = LogFactory.getLog(AsistenciaService.class);
	
	@Autowired
	@Qualifier("asistenciaRepository")
	private AsistenciaRepository asistenciaRepository;
	
	@Autowired
	@Qualifier("alumnosRepository")
	private AlumnosRepository alumnosRepository;
	
	@Autowired
	@Qualifier("asistenciaConverter")
	private AsistenciaConverter asistenciaConverter;
	
	@Override
	public List<AsistenciaModel> listarAsistenciaSesion(int nroSesion) {
		
		//List<Asistencia> listAsistencia = asistenciaRepository.findBySesionNIdSesion(nroSesion);
		
		List<Asistencia> listAsistencia = asistenciaRepository.listarAsistenciaSesion(nroSesion);
		
		//List<Alumno> alumnosFataron = alumnosRepository.findByAsistenciaListSesionNIdSesionIsNull(nroSesion);
		
		LOG.info("[Asistencia Sesion " + nroSesion + "] => " + listAsistencia);
		
		// LOG.info("[Alumnos faltaron] => " + alumnosFataron);
		
		
		List<AsistenciaModel> listModel = new ArrayList<>();
		listAsistencia.forEach(asistencia -> {
			listModel.add(asistenciaConverter.toModel(asistencia));
		});
		
		return listModel;
	}

	@Override
	public AsistenciaModel agregarNuevaAsitencia(AsistenciaModel asistencia) {
		Asistencia entity = asistenciaRepository.save(asistenciaConverter.toEntity(asistencia));
		return asistenciaConverter.toModel(entity);
	}

	@Override
	public boolean asistenciaRegistrada(int nroSesion, String codigoAlu) {
		return asistenciaRepository.existsById(new AsistenciaPK(nroSesion, codigoAlu));
	}

	@Override
	public boolean alumnoRegistrado(String codAlumno) {
		
		return alumnosRepository.existsById(codAlumno);
	}

	@Override
	public Alumno buscarAlumno(String codAlumno) {
		
		return alumnosRepository.getOne(codAlumno);
	}
}
