package com.guis.asistencia.services;

import com.guis.asistencia.entities.Sesion;

public interface SesionService {
	
	public Sesion buscarSesion(int nroSesion);
	
	public Sesion agregarNuevaSesion(Sesion sesion);
	
	public boolean sesionRegistrada(int nroSesion);
	
	public boolean sesionTerminada(int nroSesion);
}
