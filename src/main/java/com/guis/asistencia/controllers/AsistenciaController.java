package com.guis.asistencia.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.servlet.ModelAndView;

import com.guis.asistencia.customviews.AsistenciaExcelView;
import com.guis.asistencia.entities.Alumno;
import com.guis.asistencia.entities.Sesion;
import com.guis.asistencia.models.AsistenciaModel;
import com.guis.asistencia.services.AsistenciaService;
import com.guis.asistencia.services.SesionService;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

	private static final Log LOG = LogFactory.getLog(AsistenciaController.class);
	
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
		// Comprueba si la sesion ya fue iniciada
		if(!sesionService.sesionRegistrada(sesion.getNIdSesion().intValue())) {
			// Si es que no fue registrada
			sesion.setTHoraInicio(LocalTime.now());
			sesion.setDFecha(LocalDate.now());
			sesion.setTTolerancia(sesion.getTHoraInicio().plus(30, ChronoUnit.MINUTES));
			if(sesion.getTHoraInicio().compareTo(sesion.getTHoraFin()) < 0){
				
				sesionService.agregarNuevaSesion(sesion);
				LOG.info("[iniciarClase] - Clase iniciada");
				return "redirect:/asistencia/ingresarAsistencia";
			} else {
				LOG.info("[iniciarClase] - Clase no se puede iniciar, hora de fin menor o igual a la de inicio");
				return "redirect:/asistencia?msj=2";
			}

		} else {
			// Si es que esta registrada comprueba que no haya terminado
			if(sesionService.sesionTerminada(sesion.getNIdSesion())){
				LOG.info("[iniciarClase] - Clase terminada");
				return "redirect:/asistencia?msj=1";
			} else {
				LOG.info("[iniciarClase] - Clase ya iniciada, puede continuar");
				return "redirect:/asistencia/ingresarAsistencia?msj=2";
			}
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
		
		if(!sesionService.sesionTerminada(nroSesion)) {
			
			if(!asistenciaService.alumnoRegistrado(asistencia.getCodigo())) {
				return "redirect:/asistencia/ingresarAsistencia?msj=4";
			}
			
			if(!asistenciaService.asistenciaRegistrada(nroSesion, asistencia.getCodigo())) {
				asistencia.setHoraDeLlegada(LocalTime.now());
				asistencia.setFecha(LocalDate.now());
				asistencia.setNroSesion(nroSesion);
				
				asistenciaService.agregarNuevaAsitencia(asistencia);
				
				Alumno alumno = asistenciaService.buscarAlumno(asistencia.getCodigo());
				
				model.addAttribute("alumno", alumno);
				
				LOG.info("Asistencia marcada " + asistencia + " " + sesion);
				
				asistenciaService.listarAsistenciaSesion(nroSesion);
				
				return "redirect:/asistencia/ingresarAsistencia?msj=1";
			} else {
				return "redirect:/asistencia/ingresarAsistencia?msj=0";
			}
		} else {
			return "redirect:/asistencia?msj=1";
		}
	}	
	
	
	@GetMapping("/listar")
	public String listarAsistenciasPorSesion(
			Model model,
			@RequestParam("nroSesion") int nroSesion) {
		
		List<AsistenciaModel> asistentes = asistenciaService.listarAsistenciaSesion(nroSesion);
		LOG.info( "[ListarAsistenciasPorSesion] " + nroSesion);
		
		LOG.info(asistentes);
		
		model.addAttribute("asistentes", asistentes);
		
		return "/asistencia/listadoAsistencia";
	}
	
	
	@GetMapping(value="/descargarExcel", produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public ModelAndView downloadExcel(@RequestParam("nroSesion") int nroSesion) {
		
		Map<String, Object> model = new HashMap<>();
		Sesion sesion = sesionService.buscarSesion(nroSesion);
		
		if(sesion != null) {
			LOG.info("Generando excel para descargar");
			String nombreExcel = String.format("Asistencia N°%d - %s.xlsx", sesion.getNIdSesion(), sesion.getDFecha().toString());
			model.put("sheetname", nombreExcel);
			model.put("asistentes", asistenciaService.listarAsistenciaSesion(nroSesion));
			LOG.info("Excel generado!");
		}
		
		return new ModelAndView(new AsistenciaExcelView(), model);
		
	}
	
}
