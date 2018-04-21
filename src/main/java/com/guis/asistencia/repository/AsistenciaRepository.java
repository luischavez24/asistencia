package com.guis.asistencia.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guis.asistencia.entities.Asistencia;

@Repository("asistenciaRepository")
public interface AsistenciaRepository extends JpaRepository<Asistencia, Serializable>{
	
	public List<Asistencia> findBySesionNIdSesion(Integer sesionnIdSesion);
	
	public Asistencia findBySesionNIdSesionAndAlumnoVCodigoA(Integer nroSesion , String codigoAlu);
	
}
