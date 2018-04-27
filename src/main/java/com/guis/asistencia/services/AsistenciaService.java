package com.guis.asistencia.services;

import java.util.List;

import com.guis.asistencia.entities.Alumno;
import com.guis.asistencia.models.AsistenciaModel;

public interface AsistenciaService {
	
	public List<AsistenciaModel> listarAsistenciaSesion(int nroSesion);
	
	public AsistenciaModel agregarNuevaAsitencia(AsistenciaModel asistencia);
	
	public boolean asistenciaRegistrada(int nroSesion, String codigoAlu);
	
	public boolean alumnoRegistrado(String codAlumno);
	
	public Alumno buscarAlumno(String codAlumno);

}
