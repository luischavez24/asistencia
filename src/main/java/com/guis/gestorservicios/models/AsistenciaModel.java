package com.guis.gestorservicios.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AsistenciaModel {
	
	public int nroSesion;
	
	public String codigo;
	
	public Date horaDeLlegada;
	
	public AsistenciaModel() {
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getHoraDeLlegada() {
		return horaDeLlegada;
	}

	public void setHoraDeLlegada(Date horaDeLlegada) {
		this.horaDeLlegada = horaDeLlegada;
	}

	@Override
	public String toString() {
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return "AsistenciaModel [codigo=" + codigo + ", horaDeLlegada=" + format.format(horaDeLlegada) + "]";
	}
	
	
}
