package com.guis.asistencia.models;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class SesionModel {

	private int nroSesion;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime horaInicio;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime horaFin;
	
	public SesionModel() {
		horaFin = LocalDateTime.now();
		horaInicio = LocalDateTime.now();
	}

	public int getNroSesion() {
		return nroSesion;
	}

	public void setNroSesion(int nroSesion) {
		this.nroSesion = nroSesion;
	}

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalDateTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalDateTime horaFin) {
		this.horaFin = horaFin;
	}
}
