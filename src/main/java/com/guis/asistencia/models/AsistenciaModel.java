package com.guis.asistencia.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class AsistenciaModel {
	
	private int nroSesion;
	
	private String codigo;
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fecha;
	
	@DateTimeFormat(iso=ISO.TIME)
	private LocalTime horaDeLlegada;
	
	public AsistenciaModel() {
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalTime getHoraDeLlegada() {
		return horaDeLlegada;
	}

	public void setHoraDeLlegada(LocalTime horaDeLlegada) {
		this.horaDeLlegada = horaDeLlegada;
	}
	
	public int getNroSesion() {
		return nroSesion;
	}

	public void setNroSesion(int nroSesion) {
		this.nroSesion = nroSesion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return "AsistenciaModel [codigo=" + codigo + ", horaDeLlegada=" + horaDeLlegada + "]";
	}
	
	
}
