package com.guis.asistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guis.asistencia.entities.Alumno;

@Repository("alumnosRepository")
public interface AlumnosRepository extends JpaRepository<Alumno, String>{
	
}
