package com.guis.asistencia.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guis.asistencia.entities.Asistencia;

@Repository("asistenciaRepository")
public interface AsistenciaRepository extends JpaRepository<Asistencia, Serializable>{
	
	public List<Asistencia> findBySesionNIdSesion(int sesionnIdSesion);
	
	public Asistencia findBySesionNIdSesionAndAlumnoVCodigoA(int nroSesion , String codigoAlu);
	
	@Query(nativeQuery=true, value="call sp_listar_asistencia_por_sesion(:nro_sesion)")
	public List<Asistencia> listarAsistenciaSesion(@Param("nro_sesion") int nroSesion);
	
}
