package com.guis.asistencia.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit; 

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.guis.asistencia.entities.Sesion;
import com.guis.asistencia.models.AsistenciaModel;
import com.guis.asistencia.services.AsistenciaService;
import com.guis.asistencia.services.SesionService;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {
	
	
	@Autowired
	@Qualifier("asistenciaService")
	private AsistenciaService asistenciaService;
	
	
	@Autowired
	@Qualifier("sesionService")
	private SesionService sesionService;
	
	
	@GetMapping("")
	public String index(Model model,
			@RequestParam(name="msj", required=false) String msj) {
		
		model.addAttribute("msj", msj);
		
		Sesion sesion = new Sesion();
		
		model.addAttribute("sesion", sesion);
		
		return "/asistencia/index";
	}
	
	@PostMapping("/iniciarClase")
	public String iniciarClase(Model model, 
			HttpServletResponse response, 
			@ModelAttribute("sesion") Sesion sesion) {
		
		response.addCookie(new Cookie("sesion", String.valueOf(sesion.getNIdSesion())));
		
		if(!sesionService.sesionRegistrada(sesion.getNIdSesion().intValue())) {
			sesion.setTHoraInicio(LocalTime.now());
			sesion.setDFecha(LocalDate.now());
			sesion.setTTolerancia(sesion.getTHoraFin().plus(30, ChronoUnit.MINUTES));
			
			
			sesionService.agregarNuevaSesion(sesion);
			
			return "redirect:/asistencia/ingresarAsistencia";
		} else {
			
			return "redirect:/asistencia/ingresarAsistencia?msj=2";
		}
		

	}
	
	
	@GetMapping("/ingresarAsistencia")
	public String ingresarAsistencia(Model model, @RequestParam(name="msj", required=false) String msj) {
		
		AsistenciaModel asistencia = new AsistenciaModel();
		
		model.addAttribute("msj", msj);
		model.addAttribute("asistencia", asistencia);
		
		return "/asistencia/registrar";
	}
	
	@PostMapping("/registrar")
	public String registrar(Model model, 
			@ModelAttribute("asistencia") AsistenciaModel asistencia,
			@CookieValue("sesion") String sesion) {
	
		int nroSesion = Integer.parseInt(sesion);
		
		if(!asistenciaService.asistenciaRegistrada(nroSesion, asistencia.getCodigo())) {
			asistencia.setHoraDeLlegada(LocalTime.now());
			asistencia.setFecha(LocalDate.now());
			asistencia.setNroSesion(nroSesion);
			
			asistenciaService.agregarNuevaAsitencia(asistencia);
			
			System.out.println("Asistencia marcada " + asistencia + " " + sesion);
			
			return "redirect:/asistencia/ingresarAsistencia?msj=1";
		}  else {
			return "redirect:/asistencia?msj=0";
		}
		
	}
}
