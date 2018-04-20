package com.guis.gestorservicios.controllers;

import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guis.gestorservicios.models.AsistenciaModel;
import com.guis.gestorservicios.models.SesionModel;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {
	
	@GetMapping("")
	public String index(Model model) {
		SesionModel sesion = new SesionModel();
		
		model.addAttribute("sesion", sesion);
		
		return "/asistencia/index";
	}
	
	@PostMapping("/iniciarClase")
	public String iniciarClase(Model model, 
			HttpServletResponse response, 
			@ModelAttribute("sesion") SesionModel sesion) {

		sesion.setHoraInicio(LocalDateTime.now());
		
		response.addCookie(new Cookie("sesion", String.valueOf(sesion.getNroSesion())));
		
		System.out.println("Clase " + sesion.getNroSesion() + " iniciada a las " + sesion.getHoraInicio());
		
		return "redirect:/asistencia/ingresarAsistencia";
	}
	
	
	@GetMapping("/ingresarAsistencia")
	public String ingresarAsistencia(Model model) {
		
		AsistenciaModel asistencia = new AsistenciaModel();
		
		model.addAttribute("asistencia", asistencia);
		
		return "/asistencia/registrar";
	}
	
	
	@PostMapping("/registrar")
	public String registrar(Model model, 
			@ModelAttribute("asistencia") AsistenciaModel asistencia,
			@CookieValue("sesion") String sesion) {

		asistencia.setHoraDeLlegada(new Date());
		
		System.out.println("Asistencia marcada " + asistencia + " " + sesion);
		
		return "redirect:/asistencia/ingresarAsistencia";
	}
}
