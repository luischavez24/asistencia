package com.guis.asistencia.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.guis.asistencia.entities.Sesion;
import com.guis.asistencia.repository.SesionRepository;
import com.guis.asistencia.services.SesionService;

@Service("sesionService")
public class SesionServiceImpl implements SesionService {

	@Autowired
	@Qualifier("sesionRepository")
	private SesionRepository sesionRepository;
	
	@Override
	public Sesion buscarSesion(int nroSesion) {
		return sesionRepository.findByNIdSesion(nroSesion);
	}

	@Override
	public Sesion agregarNuevaSesion(Sesion sesion) {
		return sesionRepository.save(sesion);
	}
	
	public boolean sesionRegistrada(int nroSesion) {
		return sesionRepository.existsById(nroSesion);
	}

}
