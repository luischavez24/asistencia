package com.guis.asistencia.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guis.asistencia.entities.Sesion;

@Repository("sesionRepository")
public interface SesionRepository extends JpaRepository<Sesion, Serializable>{
	
	public Sesion findByNIdSesion(Integer sesion);
	
}
